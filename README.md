This program's purpose is to create and store events in a persistent database and categorize them by creatable categories and tags. 

Categories and Tags may be created and deleted in their respective forms.
Events may be created with a one-to-one relationship to a details class, and a many-to-one relationship with a category class.
Events may have tags connected to them through a many-to-many relationship.
Events may be deleted.

Future improvements inculde:
1. Edit Event funcionality
2. Re-add additional event details
3. Store/Restore deleted Events, Categories, and Tags
4. Add Person class:
   - Extends AbstractEntity for int id
   - String firstName, String lastName, String email, int age, String password, String phoneNumber
   - potential one-to-one classes (not sure about this):
     -ContactInfo (email, phoneNumber)
     -Authentication (password)
   - List<Event> eventsAttending — Many-to-Many relationship with Event
   - List<Tag> tagsFollowing — Many-to-Many relationship with Tags
   - List<Event> eventsCreated — One-to-Many relationship with Event
5. Add Person eventOwner to Event in a Many-to-One relationship
