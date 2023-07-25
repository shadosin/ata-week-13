# ATA-Week-13

# Iterators and DynamoDB Delete

## Task Tracker
#### Table Setup
```bash
aws cloudformation create-stack --stack-name Week13-IteratorsAndDelete-TaskTracker --template-body file://IteratorsAndDynamoDBDelete/TaskTracker/tasktracker_table.yaml 
aws cloudformation wait stack-create-complete --stack-name Week13-IteratorsAndDelete-TaskTracker
aws dynamodb batch-write-item --request-items file://IteratorsAndDynamoDBDelete/TaskTracker/tasktracker_seeddata.json
```

#### Clean-up
```bash
aws cloudformation delete-stack --region us-east-1 --stack-name Week13-IteratorsAndDelete-TaskTracker
```

## Clothing Items
#### Table Setup
```bash
aws cloudformation create-stack --stack-name Week13-IteratorsAndDelete-ClothingItems --template-body file://IteratorsAndDynamoDBDelete/Clothing/clothingitems_table.yaml 
aws cloudformation wait stack-create-complete --stack-name Week13-IteratorsAndDelete-ClothingItems
aws dynamodb batch-write-item --request-items file://IteratorsAndDynamoDBDelete/Clothing/clothingitems_seeddata.json
```

#### Clean-up
```bash
aws cloudformation delete-stack --region us-east-1 --stack-name Week13-IteratorsAndDelete-ClothingItems
```


## Kenzie Social Calendar
#### Table Setup
```bash
aws cloudformation create-stack --stack-name Week13-IteratorsAndDelete-SocialCalendar --template-body file://IteratorsAndDynamoDBDelete/SocialCalendar/socialcalendar_tables.yaml
aws cloudformation wait stack-create-complete --stack-name Week13-IteratorsAndDelete-SocialCalendar 
aws dynamodb batch-write-item --request-items file://IteratorsAndDynamoDBDelete/SocialCalendar/events_seeddata.json
aws dynamodb batch-write-item --request-items file://IteratorsAndDynamoDBDelete/SocialCalendar/invites_seeddata.json
aws dynamodb batch-write-item --request-items file://IteratorsAndDynamoDBDelete/SocialCalendar/members_seeddata.json
```

#### Clean-up
```bash
aws cloudformation delete-stack --region us-east-1 --stack-name Week13-IteratorsAndDelete-SocialCalendar
```


# DynamoDB Query

## Books
#### Table Setup
```bash
aws cloudformation create-stack --stack-name Week13-DynamoDBQuery-Books --template-body file://DynamoDBQuery/Books/booksread_table.yaml 
aws cloudformation wait stack-create-complete --stack-name Week13-DynamoDBQuery-Books 
aws dynamodb batch-write-item --request-items file://DynamoDBQuery/Books/booksread_seeddata.json
```

#### Clean-up
```bash
aws cloudformation delete-stack --region us-east-1 --stack-name Week13-DynamoDBQuery-Books
```

## Log Entries
#### Table Setup
```bash
aws cloudformation create-stack --stack-name Week13-DynamoDBQuery-LogEntries --template-body file://DynamoDBQuery/LogEntries/logentries_table.yaml 
aws cloudformation wait stack-create-complete --stack-name Week13-DynamoDBQuery-LogEntries
aws dynamodb batch-write-item --request-items file://DynamoDBQuery/LogEntries/logentries_seeddata.json
```

#### Clean-up
```bash
aws cloudformation delete-stack --region us-east-1 --stack-name Week13-DynamoDBQuery-LogEntries
```

## Kenzie Social Calendar: Search
#### Table Setup
```bash
aws cloudformation create-stack --stack-name Week13-DynamoDBQuery-SocialCalendar --template-body file://DynamoDBQuery/SocialCalendar/socialcalendar_tables.yaml 
aws cloudformation wait stack-create-complete --stack-name Week13-DynamoDBQuery-SocialCalendar
aws dynamodb batch-write-item --request-items file://DynamoDBQuery/SocialCalendar/eventannouncements_seeddata.json
aws dynamodb batch-write-item --request-items file://DynamoDBQuery/SocialCalendar/events_seeddata.json
aws dynamodb batch-write-item --request-items file://DynamoDBQuery/SocialCalendar/invites_seeddata.json
aws dynamodb batch-write-item --request-items file://DynamoDBQuery/SocialCalendar/members_seeddata.json
```

#### Clean-up
```bash
aws cloudformation delete-stack --region us-east-1 --stack-name Week13-DynamoDBQuery-SocialCalendar
```

# Metrics
## Reservations
#### Table Setup
```bash
aws cloudformation create-stack --stack-name Week13-Metrics-Reservations --template-body file://Metrics/HotelReservations/reservations_table.yaml 
aws cloudformation wait stack-create-complete --stack-name Week13-Metrics-Reservations
aws dynamodb batch-write-item --request-items file://Metrics/HotelReservations/reservations_seeddata.json
```

#### Clean-up
```bash
aws cloudformation delete-stack --region us-east-1 --stack-name Week13-Metrics-Reservations
```

## Test commands

### Iterators and DynamoDB Delete
1. `./gradlew iterators-wishlist-test`
2. `./gradlew iterators-tasktracker-phase1-test`
3. `./gradlew iterators-tasktracker-phase2-test`
4. `./gradlew iterators-clothing-test`
5. `./gradlew iterators-socialcalendar-activitytest`
6. `./gradlew iterators-socialcalendar-eventdaotest`
7. `./gradlew iterators-socialcalendar-invitedaotest`
8. `./gradlew iterators-socialcalendar-memberdaotest`
9. `./gradlew iterators-socialcalendar-all-tests`

### DynamoDB Query
1. `./gradlew dynamodbquery-book-test`
2. `./gradlew dynamodbquery-narrowing-test`
3. `./gradlew dynamodbquery-socialcalendar-deletetest`
4. `./gradlew dynamodbquery-socialcalendar-betweendatestest`
5. `./gradlew dynamodbquery-socialcalendar-getinvitestest`
6. `./gradlew dynamodbquery-socialcalendar-geteventtest`
7. `./gradlew dynamodbquery-phase0`
8. `./gradlew dynamodbquery-phase1`
9. `./gradlew dynamodbquery-phase2`
10. `./gradlew dynamodbquery-phase3`
11. `./gradlew dynamodbquery-phase4`


### Metrics
1. `./gradlew metrics-orders`
2. `./gradlew metrics-hotel-phase0`
3. `./gradlew metrics-hotel-phase1`
4. `./gradlew metrics-hotel-phase2`
5. `./gradlew metrics-hotel-all-tests`
