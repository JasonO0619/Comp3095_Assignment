print('START - Database Initialization');

// BookingService initialization
db = db.getSiblingDB('booking-service');
db.createUser(
    {
        user: 'admin',
        pwd: 'password',
        roles: [{role: 'readWrite', db: 'booking-service'}]
    }
);
db.createCollection('user');
print('BookingService initialized.');

// EventService initialization
db = db.getSiblingDB('event-service');
db.createUser(
    {
        user: 'admin',
        pwd: 'password',
        roles: [{role: 'readWrite', db: 'event-service'}]
    }
);
db.createCollection('events');
print('EventService initialized.');

// ApprovalService initialization
db = db.getSiblingDB('approval-service');
db.createUser(
    {
        user: 'admin',
        pwd: 'password',
        roles: [{role: 'readWrite', db: 'approval-service'}]
    }
);
db.createCollection('approvals');
print('ApprovalService initialized.');

print('END - Database Initialization');
