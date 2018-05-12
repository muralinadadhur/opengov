Feature: Create Feature Flags

Scenario: Verify Flags API allows user to create a new feature flag
  When I create a feature flag with any name
  And status code is 201
  Then I get flags listing
  And status code is 200
  And flags listing contains the featue flag that was just added

Scenario: Verify Flags API allows user to read feature flags
  Then I get flags listing
  And status code is 200

Scenario: Verify Flags API allows user to update a feature
  When I update the feature flag status 5 to a new value
  Then I get flags listing
  And status code is 200
  And flags listing contains the featue flag that was just updated
