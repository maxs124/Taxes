# Taxes By Zip Code

This is an App where you can find the amount of money you payed to each government agency in a certain zip code.

---

Download the repo:
```
$ git clone https://github.com/maxshmelev124/Taxes.git
```
Alternatively, you can just click the download Zip button on the Clone or Download dropdown.

To check out the code, use any notepad, and take a look. If you want to start the Application, it is slightly 
more complicated.

+ You will need to install gradle you can find info on how to do this [Here](https://gradle.org/install/).

+ Open a terminal/cmd window in the project directory.

+ Use the command:
```
$ gradle build
```
+ This command should take a minute to finish

+ Then finally, use the command:
```
$ gradle bootrun
```
+ You'll know it is done starting when you see:
```
Started ApplicationKt in X seconds (JVM running for X)
```
+ This shouldn't take to long, but once it is done navigate to [http://localhost:8080/](http://localhost:8080/) this should take you to the correct page. 

On the page you can enter a zip code and it should give you the average amount of individual tax paid for each person in that zip code.


---
This application was built in Kotlin/JVM and uses data collected by [Wikipedia](https://en.wikipedia.org/wiki/2018_United_States_federal_budget), [The Balance](https://www.thebalance.com/current-u-s-federal-government-tax-revenue-3305762), [Center on Budget and Policy Priorities](https://www.cbpp.org/research/federal-tax/policy-basics-where-do-federal-tax-revenues-come-from), [Tax Foundation](https://taxfoundation.org/summary-federal-income-tax-data-2017/), and the [IRS](irs.gov).

The application was built on [Spring](https://spring.io/) for the backend and [Vaadin](https://vaadin.com/) for the UI.

---

Lastly, if you have any issues running the application feel free to email me maximilian.shmelev@student.tamdistrict.org
