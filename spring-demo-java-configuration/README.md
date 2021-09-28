
# FAQ: How @Bean works behind the scenes

## Question:

### During All Java Configuration, how does the @Bean annotation work in the background?

## Answer

This is an advanced concept. But I'll walk through the code line-by-line.

For this code:

```java

@Bean 
public Coach swimCoach() {   
SwimCoach mySwimCoach = new SwimCoach();   
return mySwimCoach; 
}

```


At a high-level, Spring creates a bean component manually. By default the scope 
is singleton. So any request for a "swimCoach" bean, will get the same instance 
of the bean since singleton is the default scope.

However, let's break it down line-by-line

```java

@Bean 
```

The @Bean annotation tells Spring that we are creating a bean component 
manually. We didn't specify a scope so the default scope is singleton.

public Coach swimCoach(){
This specifies that the bean will bean id of "swimCoach". The method name 
determines the bean id. The return type is the Coach interface. This is 
useful for dependency injection. This can help Spring find any dependencies 
that implement the Coach interface.

The @Bean annotation will intercept any requests for "swimCoach" bean. 
Since we didn't specify a scope, the bean scope is singleton. As a result, 
it will give the same instance of the bean for any requests.

```java

SwimCoach mySwimCoach = new SwimCoach();

```

This code will create a new instance of the SwimCoach.

```java

return mySwimCoach;

```

This code returns an instance of the swimCoach.

Now let's step back and look at the method in it's entirety.

```java	
@Bean 
public Coach swimCoach() {   
SwimCoach mySwimCoach = new SwimCoach();   
return mySwimCoach; 
}
```

It is important to note that this method has the @Bean annotation. The annotation 
will intercept ALL calls to the method "swimCoach()". Since no scope is 
specified the @Bean annotation uses singleton scope. Behind the scenes, 
during the @Bean interception, it will check in memory of the Spring container 
(applicationContext) and see if this given bean has already been created.

If this is the first time the bean has been created then it will execute the 
method as normal. It will also register the bean in the application context. 
So that is knows that the bean has already been created before. Effectively 
setting a flag.

The next time this method is called, the @Bean annotation will check in memory 
of the Spring container (applicationContext) and see if this given bean has 
already been created. Since the bean has already been created (previous paragraph) 
then it will immediately return the instance from memory. It will not execute the 
code inside of the method. Hence this is a singleton bean.

The code for

```java	
SwimCoach mySwimCoach = new SwimCoach(); 
return mySwimCoach;

```

is not executed for subsequent requests to the method public Coach swimCoach() . 
This code is only executed once during the initial bean creation since it is 
singleton scope.


That explains how @Bean annotation works for the swimCoach example.

Now let's take it one step further.

## Here's your other question

# Please explain in detail whats happening behind the scene for this statement.

```java
return new SwimCoach(sadFortuneService())
```

The code for this question is slightly different. It is injecting a dependency.

In this example, we are creating a SwimCoach and injecting the sadFortuneService().

```java	
// define bean for our sad fortune service
@Bean
public FortuneService sadFortuneService() {
return new SadFortuneService();
}

// define bean for our swim coach AND inject dependency
@Bean
public Coach swimCoach() {
SwimCoach mySwimCoach = new SwimCoach(sadFortuneService());

return mySwimCoach;
}

```

Using the same information presented earlier

## The code

```java	        
// define bean for our sad fortune service
@Bean
public FortuneService sadFortuneService() {
return new SadFortuneService();
}
```

In the code above, we define a bean for the sad fortune service. Since the bean
scope is not specified, it defaults to singleton.

Any calls for sadFortuneService, the @Bean annotation intercepts the call 
and checks to see if an instance has been created. First time through, no 
instance is created so the code executes as desired. For subsequent calls, 
the singleton has been created so @Bean will immediately return with the 
singleton instance.


# Now to the main code based on your question.

```java
return new SwimCoach(sadFortuneService())
```

This code creates an instance of SwimCoach. Note the call to the method 
sadFortuneService(). We are calling the annotated method above. The @Bean will 
intercept and return a singleton instance of sadFortuneService. 
The sadFortuneService is then injected into the swim coach instance.

## This is effectively dependency injection. It is accomplished using all Java configuration (no xml).

This concludes the line-by-line discussion of the source code. All of the
behind the scenes work.
---
 
# FAQ: What is a real-time use case for @Bean?

## Here is a real-time use case of using @Bean: You can use @Bean to make an existing third-party class available to your Spring framework application context.

 For example, I was recently working on a global real-time project using Amazon Web Services.
 The project made use of the Amazon Simple Storage Service (AWS S3). This is remote service that
 provides object storage in the cloud. You can think of AWS S3 at a high-level as a remote
 file server for storing files (pdfs, pngs etc).

 Our Spring application needed to integrate with AWS S3 and store pdf documents. Amazon
 provides an AWS SDK for integrating with AWS S3. Their API provides a class, S3Client.
 This is a regular Java class that provides a client interface to the AWS S3 service.
 We needed to share the S3Client object in various services in our Spring application.
 However, the S3Client does not have the @Component annotation. The S3Client does
 not use Spring.

 Since the S3Client is part of the AWS framework, we can't modify the source code for
 the S3Client directly. We can't simply add the @Component annotation to the 
 S3Client source code. As a result, we need an alternative solution.

 But no problem, by using the @Bean annotation, I can wrap this third-party class,
 S3Client, as a Spring bean. And then once it is wrapped using @Bean, it is as a 
 singleton object and available in our Spring framework application context. 
 I can now easily share this bean in my app using dependency injection and @Autowired. 
 So think of the @Bean annotation was a wrapper / adapter for third-party classes. 
 You want to make the third-party classes available to your Spring framework
 application context.

## Here's a real-time example

 Here is a snippet from our @Configuration class. We create an instance of the 
 S3Client and wrap it as a Spring bean. The default scope is singleton. It is now 
 available in our application context and we can inject it to other parts of our 
 Spring application using @Autowired.
 
```java 
  @Bean
    public S3Client remoteClient() {
        
            // Create an S3 client to connect to AWS S3
            S3Client s3Client = S3Client.builder().region(Region.of(region))
                    .credentialsProvider(StaticCredentialsProvider.create(awsCreds)).build();
     
            return s3Client;    
    }
    
 ```
 
 In the code below, this is a Spring service that uses the S3Client. The service 
 @Service annotation is a subclass of @Component. This code uses @Autowired to 
 inject the bean named "remoteClient". This bean was created in the configuration 
 code above using @Bean.

 Once the bean is injected, then our method can use this to interact with the Amazon 
 S3 service. In this real-time project, we were processing insurance claims. 
 We store the PDF invoices in the cloud using the AWS S3 service.
 
 ```java
 
 @Service
    public class InsuranceClaimsServiceImpl implements ClaimsService {
        
        @Autowired
        private S3Client remoteClient;
     
        ...
     
        public void processClaim(Claim theClaim) {
     
     
            // read claim data
            FileData fileData = theClaim.getFileData("payerInvoice");
            String fileName = theClaim.getSubmittedFileName();    
     
            // get the input stream and file size
                InputStream fileInputStream = fileData.getInputStream();
                    long contentLength = fileData.getSize();
     
                //
            // store claim data in AWS S3 
            //
     
            // Create a put request for the object
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                                .bucket(bucketName)
                                .key(subDirectory + "/" + fileName)
                                .acl(ObjectCannedACL.BUCKET_OWNER_FULL_CONTROL).build();
            
            // perform the putObject operation to AWS S3 ... using our autowired bean
            remoteClient.putObject(putObjectRequest, RequestBody.fromInputStream(fileInputStream, contentLength))
        }
    }
    
 ```
 
 As you can see, I was able to wrap a third-party class as a Spring bean. The 
 AWS S3Client object was not originally annotated with @Component. The S3Client 
 is not aware of Spring. But I could manually wrap it using @Bean. By doing this, 
 the object is now available in our Spring application context. We can now 
 share/reuse this bean in other areas of our Spring app by using dependency 
 injection and @Autowired.

 For other services in our application, if they need access to the S3client 
 (singleton) then they can simply inject it using @Autowired. No need for each 
 service to create a new instance of the S3Client every time. This keeps the 
 application efficient in terms of memory and performance.

---

### In summary: You can use @Bean to make an existing third-party class available to your Spring framework application context.


 Note: You can use both two approaches of @Bean AND component scanning 
 depending on your application requirements. You can use @Bean for third-party 
 beans. For your existing beans in your class you can use component scanning 
 with @Component (also @Controller, @Service, @Repository ...).
 ---

