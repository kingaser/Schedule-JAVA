<<<<<<< HEAD
package com.sparta.schedule.entity;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private String password;

=======
package com.sparta.schedule.entity;public class User {
>>>>>>> b4b44489528cc5c4e064de156852e3858ae4a9b5
}
