Acceptance Criteria
===================

As a user, I can add line items to invoices
-------------------------------------------

### Details

Every invoice has a list of items. Each one is unique and contains a
description, quantity and fee. Item fees come in 2 different types: Flat
fee and Rate based fee.

**Rate based fees:**

-   have a rate
-   have a quantity
-   can calculate the total fee

**Flat fees:**

-   have an amount
-   can calculate the total fee

### Acceptance Tests

-   Items are unique
-   Items have 3 properties: description, quantity and the total fees
-   There are 2 types of fees: flat fees and rate-based fees
-   Fee is the total cost of each flat fee item or total cost of rate based items.

As a user, I can add a company so that I can bill them for my services
----------------------------------------------------------------------

### Details

Every Company should be unique and have invoice number(unique), name,
address, contact(name, title, phone number)

**Company:**

-   have company name
-   have company address
-   have contact name
-   have contact title ("Accounts Payable")
-   have contact phone number

### Acceptance Tests {#acceptance-tests-2}

-   Company is unique
-   Company has 5 properties: name, address, contact name, contact
    title, contact phone number

As a user, I can update company information
-------------------------------------------

### Details

A user should be able to update Company any or all property(s) with
current information without loosing its relationship to invoices

-   update company name
-   update company address
-   update contact name
-   update contact title ("Accounts Payable")
-   update contact phone number

### Acceptance Tests {#acceptance-tests-3}

-   update all fields without loosing relationship to invoices
-   update company name without loosing relationship to invoices
-   update company address without loosing relationship to invoices
-   update contact name without loosing relationship to invoices
-   update contact title ("Accounts Payable") without loosing
    relationship to invoices
-   update contact phone number without loosing relationship to invoices

As a user, I can view a list of unpaid invoices by company
----------------------------------------------------------

### Details

An invoice is an itemized billable list of products and services that a
company has purchased.

### Acceptance Tests {#acceptance-tests-4}

-   Must have a list view which contains a creation date, total and paid
    status for the company.
-   Should be sorted in ascending order of creation date.
-   Must have a list view which contains all inventory details including
    line item details.
-   Invoice lists must be paginated with a limit of 10 items per
    request.

As a user I would like to find an invoice by searching with the invoice number so that I can help customers
-----------------------------------------------------------------------------------------------------------

### Details

The invoice number is used by the customer to call and ask the user
about a particular invoice

### Acceptance Tests

-   Must be able to retrieve invoices by invoice number

As a user, I can create invoices
--------------------------------

### Details

An invoice is a billable itemized list of products and services that a
company has purchased and has a unique invoice number for the customer
to reference

### Acceptance Tests

-   Invoices are unique
-   Invoices have unique invoice number visible to customer as a
    reference number
-   Invoices must have one or more line items.
-   Must be associated with a company.
-   Must provide the total cost of all line items.
-   The date the invoice was created should be tracked.
-   The date the invoice was modified should be tracked.

As a user, I can modify invoices
--------------------------------

### Details

An invoice is an itemized list of products and services with a unique
invoice number as a reference.

### Acceptance Tests

-   As long as the status of the invoice is unpaid, it
    can be modified.
-   The date it was last modified should be recorded.
-   paid invoices cannot be changed.

As a user, I can delete invoices so that I can focus only on current and recent work.
-------------------------------------------------------------------------------------

### Details

An invoice is a unique billable itemized list of products and services.

### Acceptance Tests

-   Invoices older > 1 year and are marked "paid" can be removed, with
    all related items.
-   Invoices marked "unpaid" cannot be deleted

As a user, I can view a list of invoices
----------------------------------------

### Details

An invoice is a unique billable itemized list of products and services
that a company has purchased.

### Acceptance Tests

-   Must have a list view which contains a creation date, total and paid
    status.
-   Should be sorted in ascending order of creation date.
-   Must have a list view which contains all inventory details including
    line item details.
-   Invoice lists must be paginated with a limit of 10 items per
    request.

As a user, I can view a list of companies that I work with.
-----------------------------------------------------------

### Details

User should be able to retrieve a list of all the companies

### Acceptance Tests

-   Must have a list view which contains all company details including
    addresses.
-   Must have a simple view that displays a list of company names and
    cities/states.
