# py_demo_zio

**Demo: PhoneBook implemented in python and ZIO**

Basic PhoneBook CRUD logic for demo purpose.

## Python

Requires Python 3.7 ot later

### Running

```bash
  python3 phonebook.py
```

### Workflow example

```python
Enter action:
	c(reate) name number
	r(ead) [name]
	u(pdate) name number
	d(delete) name

cre Vasia +1-123-5678-9876
Record created - Vasia: +1-123-5678-9876
Enter action:
	c(reate) name number
	r(ead) [name]
	u(pdate) name number
	d(delete) name

cre Petia +380-68-123-4567
Record created - Petia: +380-68-123-4567
Enter action:
	c(reate) name number
	r(ead) [name]
	u(pdate) name number
	d(delete) name

read
{'Vasia': '+1-123-5678-9876', 'Petia': '+380-68-123-4567'}
Enter action:
	c(reate) name number
	r(ead) [name]
	u(pdate) name number
	d(delete) name

create Vasia XXX
Action failed: Record for 'Vasia' already exists!
Enter action:
	c(reate) name number
	r(ead) [name]
	u(pdate) name number
	d(delete) name

del Vasia
Record 'Vasia' deleted
Enter action:
	c(reate) name number
	r(ead) [name]
	u(pdate) name number
	d(delete) name

```
