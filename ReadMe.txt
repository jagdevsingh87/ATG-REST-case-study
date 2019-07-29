=========================================================================================================================
                                                Customer Review Case Study
=========================================================================================================================	

Code Base :
===========
											
This directory contains two subdirectories.

1. CaseStudy : This is ATG module for customer review functionality. Assemble this module in your application EAR to use this functionality. This can be directly imported into eclipse as well.
2. DBCR : This directory contain Database related changes for this functionality. This contains both rollforward and rollback scripts. Follow the instructions mentioned in ReadMe.txt file available in this folder.


REST API End Points :
=====================

1. getProductReviewsForProduct : http://localhost:8080/rest/model/neo/productReviews/actors/ProductReviewActor/getProductReviewsForProduct

2. getProductReviewsByCustomer : http://localhost:8080/rest/model/neo/productReviews/actors/ProductReviewActor/getProductReviewsByCustomer

3. addProductReview            : http://localhost:8080/rest/model/neo/productReviews/actors/ProductReviewActor/addProductReview


Below are the files description in this custom module :
=====================================================

1. /CaseStudy/src/com/neo/review/constant/ProductReviewConstants.java              -- Contains contants used in this module.
2. /CaseStudy/src/com/neo/review/tools/ProductReviewTool.java                      -- Implements the core functionality [get/add reviews].
3. /CaseStudy/META-INF/MANIFEST.MF                                                 -- Module Manifest file.
4. /CaseStudy/config/atg/commerce/catalog/custom/customCatalog.xml                 -- Extended the product item-descriptor to include reviews.
5. /CaseStudy/config/atg/dynamo/service/filter/bean/beanFilteringConfiguration.xml -- REST filter to control out put of review.
6. /CaseStudy/config/atg/rest/registry/ActorChainRestRegistry.properties           -- Added custom chains in the Actor Chain registry [Login/logout and create user chains added for  demonstration purpose only].
7. /CaseStudy/config/atg/userprofiling/userProfile.xml                             -- Extended the user item-descriptor to include reviews.
8. /CaseStudy/config/com/neo/review/tools/ProductReviewTool.properties             -- Component of /CaseStudy/src/com/neo/review/tools/ProductReviewTool.java. This is used in actor chains.
9. /CaseStudy/config/com/neo/review/CustomerReviewRepository.properties            -- New repository on core/production schema contains user/product review data.
10./CaseStudy/config/com/neo/review/customerReviewRepository.xml                   -- definition file for customer review repository [mentioned above].
11./CaseStudy/config/neo/productReviews/actors/ProductReviewActor.properties       -- Product review Actor component. 
12./CaseStudy/config/neo/productReviews/actors/productReviewActor.xml             -- Defines actor chains for above component.

Note : Only scenarios mentioned in the case study document are implemented. 

Key Points :

1. Product reviews are stored in separate repository [Customer Review Repository]. This can be enhanced to include rating and question/answers.
2. user and product item descriptor are having one to many relationship with this reviews item.
3. One user can review a product once only. 