-- DBCR For Customer Review Functionality

Execution instructions :
========================
1. Login to the database using corresponding  schema [For Core connect to core schema, For switching connect to switch a and b ].

2. Follow the below sequence to execute the DBCR (DDL Changes).

               a) \RollForward\Core.sql                      For Core Schema
			   b) \RollForward\SwitchingDataSource.sql       For Switch A and Switch B schema
   

Rollback Steps : Execute below steps only in the case you need to rollback above database changes.
================

1. Login to the database using corresponding  schema [For Core connect to core schema, For switching connect to switch a and b ].

2. Follow the below sequence to execute the DBCR (DDL Rollback Changes).

               a) \RollBack\SwtchingDataSource_Rollback.sql      For Switch A and Switch B schema                
			   b) \RollBack\Core_Rollback.sql                    For Core Schema