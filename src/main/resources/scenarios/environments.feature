Feature: Create an environment

Scenario Outline: Create an environment with a new name
  When I create an environment with name as "<envName>"
  And status code is 201
  And I get all environments
  And Environment name should match with "<envName>"

  Examples:
    |envName |
    |qa      |
    |e2e     |
    |staging |
    |prod    |

Scenario:Create an environment without a name
  When I create an environment with name as "junk"
  And status code is 400

Scenario Outline: Read an environment with name
  When I get an environment with name as "<envName>"
  And status code is 200
  And Environment name should match with "<envName>"

  Examples:
    |envName      |expected|
    |qa           |qa      |
    |e2e          |e2e     |
    |staging      |staging |
    |prod         |prod    |

Scenario: Environment details are not showing up with a non-existing environment
  When I get an environment with name as "junk"
  And status code is 404

Scenario Outline: Read all flags for an environment
  When I get all flags for environment "<envName>"
  And status code is 200
  And Flags should match with the expected "<flags>"
  Examples:
    |envName      |flags                                      |
    |qa           |enableDimension.table, enableDimension.flag|
    |e2e          |enableDimension.table, enableDimension.tool|
    |staging      |enableDimension.heirarchies                |
    |prod         |enableDimension.network                    |

Scenario Outline:  Read a flag for an environment
  When get a flag with name "enableIngest.dimension" from "<envName>" environment
  And status code is 200
  And I should see all details of flag "enableIngest.dimension" from that environment

  Examples:
  |envName      |
  |qa           |
  |e2e          |
  |staging      |
  |prod         |

