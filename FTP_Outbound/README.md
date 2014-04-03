Dynamic FTP Sample
==================

This example demonstrates one technique for sending files to dynamic destinations.

See **DynamicFtpChannelResolver**. The **resolve()** method maps a customer to a channel, where the channel is defined in a separate application context for each customer. The application context property placeholders are configured using the **Spring 3.1 Environment** management by supplying a custom property source.

A real implementation would provide these properties based on the customer; this sample simply uses the customer as part of the host name. The application context is loaded from the file in **src/main/resources/...**.

**DynamicFtpChannelResolverTests** is a simple JUnit test case that verifies the same channel is used for the same customer each time, and that a different channel is used for a different customer.

**FtpOutboundChannelAdapterSample** shows how messages sent with a different customer header are routed to a different ftp adapter. It simply verifies that the correct *UnknownHostException* is returned.

Notice in the config file, how an **expression-based router** is used to invoke the **DynamicFtpChannelResolver** to obtain a reference to the appropriate channel for the customer. Notice that the channel resolver is a simple POJO, invoked using SpEL...

	<int:router input-channel="toDynRouter"
		expression="@channelResolver.resolve(headers['customer'])"/>
		

