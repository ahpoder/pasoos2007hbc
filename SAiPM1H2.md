Page for Hand-in 2 of the Software Architecture in Practice course at AU.

# Introduction #

Brainstorming, decentralized rantings in general.

# Details #

  * General progress
  * Step by step advancements
  * Takes care of "meetings" in our decentralized setting.

# Stakeholders and interests #

  * User (homeowner)
    * Easy to use and configure
    * Always available
    * Safe (will not make an error and burn down the house)
    * Secure (other people cannot control my home)
  * Producer
    * Cheap
    * Production line (modifiability for other uses)
  * Technical installer
    * Easily deployable (installable)
  * Developer
    * Easily adjustable to new requirements
    * Scalable
    * Remote update (upgrading online)
    * Remote maintenance (debugging online)
    * Automatically testable (regression test)
    * Developing in parallel
  * Insurance company
    * Safe with respect to fires (radiators) and break ins (door), ...

# Scenario Brainstorming #

  1. A sensor stops responding.
  1. Someone leaves a window open in an ice storm, and the sensors makes the actuators overheat.
  1. Someone accidentally puts their stiletto through the Ethernet switch, disabling networking parts of the system.
  1. After an average user has used the system for less than one hour e or she has mastered the basic functionality
  1. The user adds a new actuator and the system configures to use the new hardware automatically within 1 minute.
  1. The user logs on externally through the gateway and has access to the home control within 5 second of verifying 98,9% of the time (30 seconds 1% of the time, more than 30 seconds (unscheduled down-time) less than 0,1%) is connected and has access
  1. The user attempts to turn radiator above normal safety-level from outside the house and receive an error back explaining the infraction. The system stores a log of the episode and refuse to execute the command.
  1. An attacker attempts to log on using an invalid password and is thrown off. The next attempt will take longer (progressive log-on time). The system stores a log of the failed attempt.
  1. Developer updates and tests the code base with a new device type in 1 week.
  1. A technical installer installs a system at a customer in no more than 4 hours.
  1. Developer updates the code base to a new requirement in less than 30 man-hours
  1. Configuration manager installs an upgrade remotely at a customer in no more than 30min
  1. Tester/debugger extract debugging information directly from running system at customer.
  1. The system handles 50 sensors and 50 actuators.
  1. The system handles up to 50 rooms/groups with one or more sensors and actuators in each group.
  1. The user changes the preferred temperature from a web page and the system adapts to it.
  1. The customer requests that the system runs on other hardware (e.g. less power consuming hardware).
  1. The customer requests that the system interfaces with other sensors/actuators?
  1. The developer modifies the sensor poll interval at build time.
  1. The system turns off all actuators when sensors cannot be probed.
  1. The end user adds a sensor/actuator at runtime which registers automatically.

## Identical scenarios ##

Due to the distributed location of the participants in the brainstorming multiple "similar" scenarios was created. In order to avoid having the same scenario represented twice with a slightly different wording, the below list show the scenarios which will be considered identical, meaning that their votes will be accumulated.

  * 2. and 7.
  * 6. and 16.
  * 10. and 17.
  * 9., 11. and 18.
  * 1. and 20.
  * 5. and 21.

# The vote #

## Round 1 ##

| **Person** |  **Role**  | **Vote 1** | **Vote 2** | **Vote 3** | **Vote 4** | **Vote 5** | **Vote 6** |
|:-----------|:-----------|:-----------|:-----------|:-----------|:-----------|:-----------|:-----------|
| Jesper   |  User    |     4    |    5     |   6/16   |    20    |    21    |   2/7    |
| Lars     | Developer|    19    |   19     |   5/21   | 9/11/18  | 9/11/18  |         13    |
| Anders   | Producer |   2/7    | 9/11/18  |  9/11/18 |    12    |    10/17    |  10/17 |

## Round 2 ##

| **Person** |  **Role**  | **Vote 1** | **Vote 2** | **Vote 3** | **Vote 4** | **Vote 5** | **Vote 6** |
|:-----------|:-----------|:-----------|:-----------|:-----------|:-----------|:-----------|:-----------|
| Jesper   | Developer | 9/11/18 |   19    |  9/11/18   | 9/11/18   |    13      |      6/16   |
| Lars     | User      |   1/20   |    4     |   6/16   |    15    |     4    |   5/21   |
| Anders   | Producer | 9/11/18  |   6/16   |  5/21  |    12    |  5/21   | 10/17  |

## Vote overview ##

This is the result of the vote in a prioritized order.

  1. 9/11/18  (8 votes)
  1. 5/21  (6 votes)
  1. 6/16  (4 votes)
  1. 19    (3 votes)
  1. 4     (3 votes)
  1. 10/17 (3 votes)
  1. 13    (2 votes)
  1. 1/20  (2 votes)
  1. 2/7   (2 votes)
  1. 12    (2 votes)
  1. 15    (1 votes)
  1. 3     (0 votes)
  1. 8     (0 votes)
  1. 14    (0 votes)