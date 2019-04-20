# Facade-Design-patterns

## Motivation for Facade pattern

The facade pattern provides a unified interface to a set of interfaces in a subsystem. While hiding the complexity of that subsystem, the full power of the subsystem is therefore available by a simple and easy to use facade. This pattern is commonly implemented to provide simple and unified access to a legacy back-end system. To offer course-grained access to a range of available services, these services are combined to provide higher level service and to reduce remote network calls. We can imagine that the facade might be a rest end point, calling various services within the application

In this application, we have implemented a simple scenario based around the banking domain. Let's imagine that a customer of the bank wants to borrow some money to purchase a car. She makes an appointment with the bank and the bank manager enters her details into the computer such as income, loan amount, repayment terms, and the computer does some calculations and either approves or declines the loan application.
Now the bank managers job is made very easy. All He has to do is to enter three figures into the computer and wait for the response. However, what happens behind the scenes is far from simple. We can imagine the processing that needs to be done to determine if customer can have the loan or not.
Firstly, the back-end system must check her credit rating. This requires access to an external system. Then if this passes, then the monthly loan repayment amount must be calculated, and measured against her monthly income, to ensure customer can afford the repayments. Then her eligibility for such a loan must be measured against the bank's own policy requirements.
If customer passes all three, the money must then transferred to her current account and monthly payments set up. So at least five calls to five different subsystems are required to complete one simple transaction. And all is done via a facade, by calling just one method. Of course, this is an oversimplification of what would happen in a real banking system. But it serves as a very useful example.

So in this application I have created five subsystems:

- [CreditRatingService](https://github.com/patrickHub/Software-Design-patterns-JavaEE/blob/master/facade-design-pattern/src/main/java/com/patrickHub/facade/subsystems/CreditRatingService.java)
- [RepaymentPayabilityService](https://github.com/patrickHub/Software-Design-patterns-JavaEE/blob/master/facade-design-pattern/src/main/java/com/patrickHub/facade/subsystems/RepaymentPayabilityService.java)
- [InternalBankPolicyService](https://github.com/patrickHub/Software-Design-patterns-JavaEE/blob/master/facade-design-pattern/src/main/java/com/patrickHub/facade/subsystems/InternalBankPolicyService.java)
- [TransferService](https://github.com/patrickHub/Software-Design-patterns-JavaEE/blob/master/facade-design-pattern/src/main/java/com/patrickHub/facade/subsystems/TransferService.java)
- [RepaymentService](https://github.com/patrickHub/Software-Design-patterns-JavaEE/blob/master/facade-design-pattern/src/main/java/com/patrickHub/facade/subsystems/RepaymentService.java)
  And then [BankSystem](https://github.com/patrickHub/Software-Design-patterns-JavaEE/blob/master/facade-design-pattern/src/main/java/com/patrickHub/facade/bankSystem/BankSystem.java) which will going to be our facade and contains one method [processLoanApplication](https://github.com/patrickHub/Software-Design-patterns-JavaEE/blob/master/facade-design-pattern/src/main/java/com/patrickHub/facade/bankSystem/BankSystem.java) call to process the application. So as you can, I have call each subsystem in this method one after another and is only when all requirement are satisfied that I proceed with the transfer of the mone. Well, because the banking system is a Java EE application, the subsystem services are a special type of bean called an Enterprise JavaBean (EJB).

## Authors

- **[PatrickHub](https://github.com/patrickHub)**
