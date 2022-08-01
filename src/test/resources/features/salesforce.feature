Feature: Limited account ingestion flag False for Creation Flow
    Limited account ingestion flag when False, doesn't check for a rule, instead it ingests all accounts from 
    CRM to B2 system.

    Scenario: Check whether new account created in Salesforce is synced to B2
        Given "Mike" is logged into B2
        When he creates a new "account" in salesforce
        Then the "account" should be present in Account Hub
    
    Scenario: Check whether account updated in Salesforce is updating the account in B2 as well
        Given "Mike" is logged into B2
        When he updates an "account" in salesforce
        Then the "account" should be updated successfully
        
    Scenario: Check whether account deleted in Salesforce is removed from B2
        Given "Mike" is logged into B2
        When he deletes a "account" in salesforce
		Then the "account" should not be present in Account Hub
       
