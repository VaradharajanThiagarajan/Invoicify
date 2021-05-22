### STEP 1: 
### As a user, I can add a company so that I can bill them for my services
```
API (POST)
http://localhost:8080/company 
or 
http://invoicifygroot.herokuapp.com/company
REQUEST  				
{"name":"CTS","address":"Address1","city":"city1","state":"state1","zip":"91367","contactName":"Mike","contactTitle":"CEO",
"contactPhoneNumber":"800-800-800"}
```

### STEP 2: 
#### As a user, I can view a list of companies that I work with.
```
API (GET)
http://localhost:8080/company
 or 
http://invoicifygroot.herokuapp.com/company
```

### STEP 3: 
#### As a user, I can update company information
```
API (PATCH)
http://localhost:8080/company/{companyId}
or
http://invoicifygroot.herokuapp.com/company/{companyId}
REQUEST
{"name":"DTS","address":"Updated Address1"}
```

### STEP 4: 
#### As a user, I can view a list of companies that I work with.
```
API (GET)
http://localhost:8080/company 
or
http://invoicifygroot.herokuapp.com/company
```

### STEP 5: 
#### As a user, I can create invoices
```
API (POST)
http://localhost:8080/invoice
or
http://invoicifygroot.herokuapp.com/invoice

REQUEST
{"companyName":"DTS","author":"test","paid":false,
"items":[{"description":"Description","rateHourBilled":10,"ratePrice":14.5,"flatPrice":20.5,"state":"New"}]}

```

### STEP 6: 
#### As a user, I can view a list of invoices (include Pagination)

```
API (GET)
http://localhost:8080/invoice
or
http://invoicifygroot.herokuapp.com/invoice
or
https://invoicifygroot.herokuapp.com/invoice?pageNo=0

```

### STEP 7:
#### As a user I would like to find an invoice by searching with the invoice number so that I can help customers

```
API (GET)
http://localhost:8080/invoice/id/{id}
or
http://invoicifygroot.herokuapp.com/invoice/id/{id}


```
### STEP 8:
#### As a user, I can view a list of unpaid invoices by company ( includes Pagination)

```
API (GET)
http://localhost:8080/invoice/unpaid/{companyname}
or
http://invoicifygroot.herokuapp.com/invoice/unpaid/{companyname}
or
http://invoicifygroot.herokuapp.com/invoice/unpaid/{companyname}?pageNo=0


```

### STEP 9:
#### As a user, I can add line items to invoices
```
API (POST)
http://localhost:8080/invoice/additem/{invoiceId}
or
http://invoicifygroot.herokuapp.com/invoice/additem/{invoiceId}

REQUEST
[{"description":"itemdescription4","rateHourBilled":10,"ratePrice":14.5,"flatPrice":60.0,"state":"New"},
{"description":"itemdescription5","rateHourBilled":10,"ratePrice":14.5,"flatPrice":30.0,"state":"New"},
{"description":"itemdescription6","rateHourBilled":10,"ratePrice":14.5,"flatPrice":0.0,"state":"New"}]

```


### STEP 10:
#### As a user, I can view a list of invoices (include Pagination)

```
API (GET)
http://localhost:8080/invoice
or
http://invoicifygroot.herokuapp.com/invoice
or
https://invoicifygroot.herokuapp.com/invoice?pageNo=0

```

### STEP 11:
#### As a user I would like to find an invoice by searching with the invoice number so that I can help customers
```
API (GET)
http://localhost:8080/invoice/id/{id}
or
http://invoicifygroot.herokuapp.com/invoice/id/{id}
```
### STEP 12:
#### As a user, I can view a list of unpaid invoices by company ( includes Pagination)

```
API (GET)
http://localhost:8080/invoice/unpaid/{companyname}
or
http://invoicifygroot.herokuapp.com/invoice/unpaid/{companyname}
or
http://invoicifygroot.herokuapp.com/invoice/unpaid/{companyname}?pageNo=0

```

### STEP 13: 
#### As a user, I can modify invoices
```
API (PUT)
http://localhost:8080/invoice?invoiceId={invoiceId}
or
http://invoicifygroot.herokuapp.com/invoice?invoiceId={invoiceId}

REQUEST

{"companyName":"Cognizant2","author":"test","paid":false,"items":[
{"itemId":4,"description":"Description4 modified","rateHourBilled":10,"ratePrice":14.5,"flatPrice":70.0,"state":"Modified"},
{"description":"eleventh item","rateHourBilled":12,"ratePrice":14.5,"flatPrice":80.0,"state":"New"}]}

```

### STEP 14:
#### As a user, I can view a list of invoices (include Pagination)

```
API (GET)
http://localhost:8080/invoice
or
http://invoicifygroot.herokuapp.com/invoice
or
https://invoicifygroot.herokuapp.com/invoice?pageNo=0

```

### STEP 15:
#### As a user I would like to find an invoice by searching with the invoice number so that I can help customers

```
API (GET)
http://localhost:8080/invoice/id/{id}
or
http://invoicifygroot.herokuapp.com/invoice/id/{id}


```
### STEP 16:
#### As a user, I can view a list of unpaid invoices by company ( includes Pagination)

```
API (GET)
http://localhost:8080/invoice/unpaid/{companyname}
or
http://invoicifygroot.herokuapp.com/invoice/unpaid/{companyname}
or
http://invoicifygroot.herokuapp.com/invoice/unpaid/{companyname}?pageNo=0

```

### STEP 17: 
#### As a user, I can delete invoices so that I can focus only on current and recent work.

```
API (DELETE)
http://localhost:8080/invoice/deletepaidandolderinvoices
or
http://invoicifygroot.herokuapp.com/invoice/deletepaidandolderinvoices

```

### STEP 18:
#### As a user, I can view a list of invoices (include Pagination)

```
API (GET)
http://localhost:8080/invoice
or
http://invoicifygroot.herokuapp.com/invoice
or
https://invoicifygroot.herokuapp.com/invoice?pageNo=0

```

### STEP 19:
#### As a user I would like to find an invoice by searching with the invoice number so that I can help customers

```
API (GET)
http://localhost:8080/invoice/id/{id}
or
http://invoicifygroot.herokuapp.com/invoice/id/{id}


```
### STEP 20:
#### As a user, I can view a list of unpaid invoices by company ( includes Pagination)

```
API (GET)
http://localhost:8080/invoice/unpaid/{companyname}
or
http://invoicifygroot.herokuapp.com/invoice/unpaid/{companyname}
or
http://invoicifygroot.herokuapp.com/invoice/unpaid/{companyname}?pageNo=0

```
