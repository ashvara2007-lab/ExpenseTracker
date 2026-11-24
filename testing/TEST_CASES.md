# Expense Tracker API – Test Cases

| ID | Test scenario | Expected behaviour | Result |
|----|---------------|--------------------|--------|
| TC-01 | Create an expense with valid details | Expense is saved and its details, including generated ID, are returned. | Pass* |
| TC-02 | Retrieve all expenses | The API returns the saved expenses. | Pass* |
| TC-03 | Retrieve an expense by ID | The requested expense is returned. | Pass* |
| TC-04 | Update an existing expense | The expense details are updated. | Pass* |
| TC-05 | Delete an expense | The selected expense is deleted. | Pass* |
| TC-06 | Retrieve expenses by category | Expenses matching the requested category are returned. | Pass* |
| TC-07 | Retrieve category using different letter case | Category lookup works without case sensitivity. | Pass* |
| TC-08 | Calculate total expenses | The API returns the combined amount of stored expenses. | Pass* |
| TC-09 | Retrieve an ID that does not exist | The API responds with a not-found result. | Pass* |
| TC-10 | Check deleted expense | Deleted expense is no longer available. | Pass* |


