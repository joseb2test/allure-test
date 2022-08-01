Feature: Scoring Configuration

    This feature file contains all the scenarios related to ICP Creation, Updation and Deletion

    Scenario Outline: Create an ICP with all the available attributes
        Given Tom is logged into B2
        When he navigates to "Score Configuration"
        And he clicks "ICP"    
        And he enters ICP name as "Test"
        And he selects the attributes "<attributes>"     
        And he selects attribute weightage "<weights>"
        And he saves the ICP
        Then ICP should be created successfully  
       	Examples:
        | attributes | weights | 
				| country,accountClassification, industry, geography,companyType, companyHierarchy | Very High, High, Medium, Low, Very Low, Low|

    #Scenario: Deletes an ICP
        #Given Tom is logged into B2
        #When he navigates to "Settings"
        #Then he clicks "ICP" button    
        #And he enters ICP name as "Test"
        #And he selects the attributes <attributes>     
        #And he selects attribute weightages <weights>
        #And he saves the ICP
        #And the ICP should be created successfully
        #Then he deletes the ICP
        #And the ICP should be deleted successfully
       #	Examples:
            #| attributes      | weights | 
            #| country,accountClassification, industry, geography,companyType, companyHierarchy | Very High, High, Medium, Low, Very Low, Low|
#
    #Scenario: Edit an ICP
        #Given Tom is logged into B2
        #When he navigates to "Settings"
        #Then he clicks "ICP" button    
        #And he enters ICP name as "Test"
        #And he selects the attributes <attributes>     
        #And he selects attribute weightages <weights>
        #And he saves the ICP
        #And the ICP should be created successfully
        #And he edits the ICP
        #Then all the attributes and weightages should be present 
       #	Examples:
            #| attributes      | weights | 
            #| country,accountClassification, industry, geography,companyType, companyHierarchy | Very High, High, Medium, Low, Very Low, Low|
#
            