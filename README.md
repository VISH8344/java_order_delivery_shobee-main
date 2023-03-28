# java_order_delivery_shobee
AUTHOR: **CHIAH MING LIANG TP061801, CHAN HONG WEI TP060647**

SHOBEE is an online order and delivery management system that created for our assignment (OODJ - Asia Pacific University)
This system consists of 4 roles which are: guest, customer, delivery staff, and admin.

## Main requirement:
### 1. Admin will be able to:
- manage admin and staff account
- edit all user profile
- manage product and category
- assign order to delivery staff
- search user order and payment
- view sales and staff performance report

### 2. Delivery staff will be able to:
- pick up order (update order status to deliverying)
- update order status to delivered
- receive feedback from customer
- edit and view their own profile

### 3. Customer (registered member))
- view and search product by name and category
- add item to cart
- remove item from cart
- checkout
- view order according to their status
- view and edit their own profile

## What's special for our program?
### Backend side
- we have implemented singleton design pattern (first try xD), for handling front end request and return the wanted objects.
 -'Services' - we have a lot of services such as AddressService, MemberService, OrderService, DeliveryService... to serve front end with backend object
 - in Services, it handle converting text file content to usable objects and vice versa.
 - all service will have only 1 instance

### Frontend side
- in our system, we will only have a frame with several panels, routing to pages will only replace the panels, which makes the user experience better.
- 99% of the UI is self created.

## User Interface
### Admin
![image](https://user-images.githubusercontent.com/83805050/224330225-5c0e8678-c6c5-42fa-9673-27cca7be1ca4.png)
#### Admin - Home page
![image](https://user-images.githubusercontent.com/83805050/224330281-398ad8a0-fe06-48a0-89a7-4648140a20ba.png)
#### Admin - Product management
![image](https://user-images.githubusercontent.com/83805050/224330505-2847c18b-e858-462f-9864-a5d1fccdc8dd.png)
![image](https://user-images.githubusercontent.com/83805050/224331134-1ec24c04-8fc8-4aa8-ad3a-020f702898ca.png)
![image](https://user-images.githubusercontent.com/83805050/224331117-307673bf-da2f-43c3-ac18-fbb27fad7720.png)
#### Admin - User management
![image](https://user-images.githubusercontent.com/83805050/224331696-94d58740-6c03-43d2-88ed-68b9c88b4753.png)
![image](https://user-images.githubusercontent.com/83805050/224331710-2ee57f96-beef-4d87-91fa-ee2edc0536df.png)
![image](https://user-images.githubusercontent.com/83805050/224331729-6cd3ab76-067f-4a23-8ae1-970980f0ee65.png)
![image](https://user-images.githubusercontent.com/83805050/224331749-67868df8-53be-450f-ae0c-c44038e4d2bc.png)
#### Admin - Category management
![image](https://user-images.githubusercontent.com/83805050/224331780-609cf9f8-da61-43a2-b70d-1fb0c7d16a30.png)
![image](https://user-images.githubusercontent.com/83805050/224331875-83079a0c-45c6-47bc-b0b6-96c73a08f100.png)
![image](https://user-images.githubusercontent.com/83805050/224331881-bac73ee2-459f-4e79-8a73-1ed8cce8f179.png)
#### Admin - assign order to staff
![image](https://user-images.githubusercontent.com/83805050/224332049-83d8e273-9be7-4aef-8885-55465ddaf36a.png)
![image](https://user-images.githubusercontent.com/83805050/224332224-933294b3-b5f0-44d0-b323-3d8a8bc408cf.png)
#### Admin - search order and payment record
![image](https://user-images.githubusercontent.com/83805050/224332300-9061226c-1e03-408f-89e2-adea537a8c98.png)
![image](https://user-images.githubusercontent.com/83805050/224332322-5c3bbef5-8a20-4735-96a6-08926be63269.png)
![image](https://user-images.githubusercontent.com/83805050/224332338-562119e5-7864-4604-bfcf-d0efcdfe1d8d.png)
![image](https://user-images.githubusercontent.com/83805050/224332358-b149369c-f8c4-490c-9669-5113c76cb064.png)
#### Admin - report (sales and staff rating)
![image](https://user-images.githubusercontent.com/83805050/224332450-51479840-0eb7-4147-b5bf-8d6fdf428c55.png)
![image](https://user-images.githubusercontent.com/83805050/224332460-b0ec8d13-52db-4b8b-aeba-2300f8dca0f6.png)
![image](https://user-images.githubusercontent.com/83805050/224332478-6776ff1e-75a3-4ef9-9cb8-0d5e836d473a.png)
#### Admin - profile
![image](https://user-images.githubusercontent.com/83805050/224332492-884f35c4-ed4d-4c39-9550-fc36665a2e23.png)


### Delivery staff
![image](https://user-images.githubusercontent.com/83805050/224332656-3617548e-2e25-4799-8b5c-d0608099b052.png)
![image](https://user-images.githubusercontent.com/83805050/224332750-7b565902-dd92-43c1-bfe7-9b7b1add44d5.png)

#### Delivery staff - pick up and complete order
![image](https://user-images.githubusercontent.com/83805050/224332716-8699912b-b4ac-4b7d-8bea-7f6a4e660a37.png)
![image](https://user-images.githubusercontent.com/83805050/224332788-36aa4960-9458-41ed-b2f7-4fb6162bd93f.png)
![image](https://user-images.githubusercontent.com/83805050/224332804-5481cba6-0cea-4181-aaca-cd7b3328dc19.png)
![image](https://user-images.githubusercontent.com/83805050/224332902-02deb140-20cd-4454-b529-25172471195c.png)
![image](https://user-images.githubusercontent.com/83805050/224332910-c4447ff0-2e48-43fe-8529-7b2b017d6bf6.png)
![image](https://user-images.githubusercontent.com/83805050/224332935-848a231f-aa91-4a5a-848b-3dc783a9a49a.png)

#### Delivery - profile
![image](https://user-images.githubusercontent.com/83805050/224332982-9a750290-2b6e-470f-ad40-3667d5316e1b.png)


### Member
![image](https://user-images.githubusercontent.com/83805050/224333049-1c47e87d-03f6-459d-8ca4-5535e7e734a6.png)
#### Member - add product to cart & make payment
![image](https://user-images.githubusercontent.com/83805050/224333097-e7b3a3d0-3ddf-4812-bb9d-57506d4404cd.png)
![image](https://user-images.githubusercontent.com/83805050/224333114-f590566a-cc8e-4cdb-a27c-d07a4d505095.png)
![image](https://user-images.githubusercontent.com/83805050/224333134-c54aed2c-3774-4028-bb27-a7464167aae6.png)
![image](https://user-images.githubusercontent.com/83805050/224333147-2973484e-8784-403c-9952-c43869ed4ba6.png)
![image](https://user-images.githubusercontent.com/83805050/224333157-e55880b7-7c6c-4946-849d-e3f63e9ca2a0.png)
![image](https://user-images.githubusercontent.com/83805050/224333178-f0ad2750-becb-40f2-b3b5-eb14da14b56f.png)
![image](https://user-images.githubusercontent.com/83805050/224333227-b31bbb76-f290-409d-9eee-7ba7fae0fdfb.png)
#### Member - view orders
![image](https://user-images.githubusercontent.com/83805050/224333299-5cd65e5f-aaa2-4fbe-99de-e272044c16f8.png)
![image](https://user-images.githubusercontent.com/83805050/224333311-8d50f14e-8e92-43c3-a00b-ad7e5649a71c.png)
#### Member - profile
![image](https://user-images.githubusercontent.com/83805050/224333365-72d1974c-abfa-4f94-af25-952433d6ee5b.png)

### Guest
![image](https://user-images.githubusercontent.com/83805050/224333397-2144094c-feff-4c56-9075-b101beac416d.png)
![image](https://user-images.githubusercontent.com/83805050/224333412-c83f49e3-b3e6-4067-9c14-32aa8e85dd78.png)
![image](https://user-images.githubusercontent.com/83805050/224333423-3995846a-d803-4e5e-b4ae-4f1b1404a94f.png)



