# Graph Recommender system based on sequences of purchases
The goal of this project is to create a functioning recommendation algorithm, that would suggest items for users based on sequences of choices they have made in past. The experiments have been done by applying two different approaches, one serial and one parallel, using Spark technology. 
<br>
<br>
<strong>The main idea</strong> here is the fact that the higher number of users bought item A after item B, the higher the probability that this was not a random purchase and the items are logically related. And if thousands of such co-purchases occurred, it is almost certain that it was not just a coincidence, in accordance with the law of large numbers.
<br>
<br>
This repository contains two main source files:
<br>
<ul>
<li><strong>AdvancedGraph2</strong> - Serial version of the algorithm</li>
<li><strong>ParallelGraph2</strong> - Distributed version of the algorithm</li>
</ul> 
<br>
In folder sampleData there is a sample of data used in the process
<br>
<ul>
<li><strong>sampleData</strong> - The data used for RS belongs among project SNAP datasets. It is based on Customers Who Bought This Item Also Bought feature of the Amazon website and was collected on March 02 2003. 
The data-set was subsequently processed to comply with required form (IDproductA, IDproductB, nOfPurchases).</li>

