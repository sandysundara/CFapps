# CFapps



Delpoying application in SAP Cloud Foundry

1)Open CLI tool and login

cf api #apiendpoint
cf login //provide email id and password


2)Create HANA service instance 

In the CLI tool execute

"cf create-service hanatrial shared-hdi  i076165-hdb"


3) Create XSUAA instance
"cf create-service xsuaa application  i076165-xsuaa"


4)Deploy application
In CLI ,change directory to the directory containing the "manifesy.yml" file  and execute
cf push


