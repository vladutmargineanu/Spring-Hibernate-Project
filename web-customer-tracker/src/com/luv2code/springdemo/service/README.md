# FAQ: Why So Many Layers? Service Layer etc ...
## Question - Why do we have to create Service layer what has the same functions as DAO layer? Is it necessary to create all this layers?

### Answer

Agreed, there are a lot of layers. However this is the architecture that you will see on real world, complex Spring projects.

In our example, it is fairly simple. We simply delegate the calls to the DAO. So I agree, you could remove the service layer in this simple example and have controller call dao directly.

However, we added the service layer to leverage the Service Layer design pattern. On a much more complex project, we could use the service layer to integrate multiple data sources (daos) and perform transaction management between the two. So, for a simple project that we have here ... this probably overkill. However, I wanted to show you design patterns that you will encounter on real projects.

Here are the benefits / use cases for use the service layer on a much larger project. In the video, I discuss benefits / use cases for the service layer

Video: Refactor - Add a Service Layer

![image](https://user-images.githubusercontent.com/48476504/136172469-0e51dabb-99c2-4951-8177-4553c14975dc.png)

![image](https://user-images.githubusercontent.com/48476504/136172481-84513aa9-91d5-4536-b602-4e2123793f49.png)



====

And here is a another scenario where you would like to perform transaction management at the service layer.

You can use @Transactional at the service layer if you want DAO methods to run in the same transaction.

Say for example we have

BankDAO

- deposit(...)

- withdraw(...)

If we are transferring funds, we want that to run in the same transaction. By making use of @Transactional at service layer, then we can have this transactional support and both methods will run in the same transaction. This would call deposit() and withdraw(). If either of those methods failed then we'd want to roll the transaction back.

However, if we had @Transactional at DAO level instead of service level, then the methods deposit() and withdraw() would run in separate transactions. If one of them failed, then we would not be able to rollback the other method ... because it is in a separate transaction.

So that's one real-time project use case for applying @Transactional at the Service layer.

===

Of course, in your personal project, there is no strict requirement to use layers. In fact, there is no requirement to use DAO. You could add all of your code to one controller class. But from an architectural point of view, that would result in a poor design.

---

Anyways, I hope this helps. Let me know if you need anything else.

---
