# java-test-shilpa
Implement a system for processing batches of booking requests.

#input

0900 1730
2020-01-18 10:17:06 EMP001
2020-01-21 09:00 2
2020-01-18 12:34:56 EMP002
2020-01-21 09:00 2
2020-01-18 09:28:23 EMP003
2020-01-22 14:00 2
2020-01-18 11:23:45 EMP004
2020-01-22 16:00 1
2020-01-15 17:29:12 EMP005
2020-01-21 16:00 3
2020-01-18 11:00:45 EMP006
2020-01-23 16:00 1
2020-01-15 11:00:45 EMP007
2020-01-23 15:00 2


#output

[
{
"data": "2020-01-21",
"bookings": [
{
"emp_id": "EMP001",
"start_time": "09:00",
"end_time": "11:00"
}
]
},
{
"data": "2020-01-22",
"bookings": [
{
"emp_id": "EMP003",
"start_time": "14:00",
"end_time": "16:00"
},
{
"emp_id": "EMP004",
"start_time": "16:00",
"end_time": "17:00"
}
]
},
{
"data": "2020-01-23",
"bookings": [
{
"emp_id": "EMP006",
"start_time": "16:00",
"end_time": "17:00"
}
]
}
]