package com.neo.review.tools;

import java.util.Set;

import atg.core.util.StringUtils;
import atg.nucleus.GenericService;
import atg.repository.MutableRepository;
import atg.repository.MutableRepositoryItem;
import atg.repository.RepositoryException;
import atg.repository.RepositoryItem;

import com.neo.review.constant.ProductReviewConstants;

/**
 * Implements the customer product review functionality
 * 
 * 1. getProductReviewsByCustomer -- display review for logged in user
 * 2. getProductReviewsForProduct -- display review for product
 * 3. addProductReview -- New review
 *
 */
public class ProductReviewTool extends GenericService {

	/**
	 * Reference to profile repository
	 */
	private MutableRepository profileRepository;
	
	/**
	 * Reference to customer review repository
	 */
	private MutableRepository customerReviewRepository;
	
	/**
	 * Reference to catalog repository
	 */
	private MutableRepository productCatalogRepository;

	/**
	 * Creates product review from user in request
	 * 
	 * @param productId -- product id of product to be reviwed
	 * @param profileId -- profile id of customer who wrote review (review author)
	 * @param comments -- review author
	 */
	@SuppressWarnings("unchecked")
	public void addProductReview(String productId, String profileId, String comments) {
         vlogDebug("Enter :: addProductReview productId = {0}, profileId = {1}, comments = {2}", productId, profileId, comments);
         
         if (StringUtils.isEmpty(profileId) || StringUtils.isEmpty(productId) || StringUtils.isEmpty(comments)) {
        	 return;
         }
         
         try {
        	 
        	//create customer review
			MutableRepositoryItem customerReviewItem = customerReviewRepository.createItem(ProductReviewConstants.REVIEW_DETAIL_ITEM_DESCRIPTOR);
				customerReviewItem.setPropertyValue(ProductReviewConstants.CUSTOMER_ID_PROP, profileId);
				customerReviewItem.setPropertyValue(ProductReviewConstants.PRODUCT_ID_PROP, productId);
				customerReviewItem.setPropertyValue(ProductReviewConstants.COMMENT_PROP, comments);
			customerReviewRepository.addItem(customerReviewItem);
			
			vlogDebug("Review created {0}", customerReviewItem);
			//Add this review to user reviews
			MutableRepositoryItem userItem = profileRepository.getItemForUpdate(profileId, ProductReviewConstants.USER_ITEM_DESCRIPTOR);
			  if(userItem != null) {
				Set customerReviews = (Set) userItem.getPropertyValue(ProductReviewConstants.REVIEWS_PROP);
				customerReviews.add(customerReviewItem);
				vlogDebug("Customer review updated for customer {0} and reviews are {1}", userItem, customerReviews);
			  }
			  
			//Add this review to product reviews
			MutableRepositoryItem productItem = productCatalogRepository.getItemForUpdate(productId, ProductReviewConstants.PRODUCT_ITEM_DESCRIPTOR);
			  if(productItem != null) {
				Set productReviews = (Set) productItem.getPropertyValue(ProductReviewConstants.REVIEWS_PROP);
				productReviews.add(customerReviewItem);
				vlogDebug("Product review updated for product {0} and reviews are {1}", productItem, productReviews);
			  }
			  
		} catch (RepositoryException repositoryException) {
			vlogError("RepositoryException while creating customer review {0}", repositoryException);
		}
         vlogDebug("Exit :: addProductReview ");
	}
	
	/**
	 * Fetch product reviews for given product
	 * 
	 * @param productId Product id for review detail
	 * @return reviews for product
	 */
	@SuppressWarnings("unchecked")
	public Set<RepositoryItem> getProductReviewsForProduct(String productId){
		vlogDebug("Enter :: getProductReviewsForProduct product id is {0}", productId);
		
		Set productReviews = null;
		
		if(StringUtils.isEmpty(productId)) {
			return productReviews;
		}
		
		try {
			
			RepositoryItem productRepositoryItem = productCatalogRepository.getItem(productId, ProductReviewConstants.PRODUCT_ITEM_DESCRIPTOR);
			
			if(productRepositoryItem != null) {
		        productReviews = (Set) productRepositoryItem.getPropertyValue(ProductReviewConstants.REVIEWS_PROP);
			}
			
		} catch (RepositoryException repositoryException) {
			vlogError("RepositoryException while fetching product review {0}", repositoryException);
		}
		
		vlogDebug("Exit :: getProductReviewsForProduct reviews are = {0} ", productReviews);
		return productReviews;
	}

	/**
	 * @return profileRepository
	 */
	public MutableRepository getProfileRepository() {
		return profileRepository;
	}

	/**
	 * Setter for profileRepository
	 * @param profileRepository
	 */
	public void setProfileRepository(MutableRepository profileRepository) {
		this.profileRepository = profileRepository;
	}

	/**
	 * @return customerReviewRepository
	 */
	public MutableRepository getCustomerReviewRepository() {
		return customerReviewRepository;
	}

	/**
	 * Setter for customerReviewRepository
	 * @param customerReviewRepository
	 */
	public void setCustomerReviewRepository(MutableRepository customerReviewRepository) {
		this.customerReviewRepository = customerReviewRepository;
	}

	/**
	 * @return productCatalogRepository
	 */
	public MutableRepository getProductCatalogRepository() {
		return productCatalogRepository;
	}

	/**
	 * Setter for productCatalogRepository
	 * @param productCatalogRepository
	 */
	public void setProductCatalogRepository(MutableRepository productCatalogRepository) {
		this.productCatalogRepository = productCatalogRepository;
	}
}
