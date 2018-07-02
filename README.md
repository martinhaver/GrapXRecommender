# Graph Recommender system based on sequences of purchases
The goal of this exploratory project was to apply the knowledge learned in class on a real world problem, in this case, creating a functioning recommendation algorithm, that would suggest items for users based on sequences of choices they have made in past. I also provide a summary of important related work that has been done in the field, with brief comparison. In later chapters I describe the problem in formal way and also the experiments I have performed with the recommendation engine on real dataset obtained from SNAP project. The experiments have been done by applying two different approaches, one serial and one parallel, using Spark technology. These approaches are then compared, the key interest being put on the gain the parallel approach is offering.
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
In folder sampleData there are 2 types of data files used in the process
<br>
<ul>
<li><strong>sampleData</strong> - The data used for RS belongs among project SNAP datasets. It is based on Customers Who Bought This Item Also Bought feature of the Amazon website and was collected on March 02 2003. 
The data-set was subsequently processed to comply with required form (IDproductA, IDproductB, nOfPurchases).</li>
<li><strong>sampleRatings</strong> - file contains data in form (productID, salesRank, avgRating). 
This file is used to further refine the output of the algorithm but is not essential to the basic idea of this project.</li>
</ul> 
