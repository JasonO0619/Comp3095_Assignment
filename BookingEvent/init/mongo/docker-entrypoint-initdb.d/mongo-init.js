print('START - Database Initialization');

// BookingService initialization
db = db.getSiblingDB('booking_service_db');
db.createUser({
    user: 'admin',
    pwd: 'password',
    roles: [{ role: 'readWrite', db: 'booking_service_db' }]
});
db.createCollection('bookings');
print('BookingService initialized.');

// EventService initialization
db = db.getSiblingDB('event_service_db');
db.createUser({
    user: 'admin',
    pwd: 'password',
    roles: [{ role: 'readWrite', db: 'event_service_db' }]
});
db.createCollection('events');
print('EventService initialized.');

// ApprovalService initialization
db = db.getSiblingDB('approval_service_db');
db.createUser({
    user: 'admin',
    pwd: 'password',
    roles: [{ role: 'readWrite', db: 'approval_service_db' }]
});
db.createCollection('approvals');
print('ApprovalService initialized.');

// RoomService initialization
db = db.getSiblingDB('room_service_db');
db.createUser({
    user: 'admin',
    pwd: 'password',
    roles: [{ role: 'readWrite', db: 'room_service_db' }]
});
db.createCollection('rooms');
print('RoomService initialized.');

// UserService initialization
db = db.getSiblingDB('user_service_db');
db.createUser({
    user: 'admin',
    pwd: 'password',
    roles: [{ role: 'readWrite', db: 'user_service_db' }]
});
db.createCollection('users');
print('UserService initialized.');

print('END - Database Initialization');
