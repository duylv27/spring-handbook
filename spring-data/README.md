# Spring Data
Apply this [Principle of least astonishment](https://en.wikipedia.org/wiki/Principle_of_least_astonishment), all fetch type
should be LAZY
## Entity State

---

## Entity Relationship
### One To One
#### Fetch Type
- Fetch Lazy for both side.
- Apply **Share Key** for one-to-one relationship. See `@PrimaryKeyJoinColumn`, `@MapsId`.
- When using **Share Key**, remember to set `optional = false` otherwise, 
fetch lazy will not work then we will get N+1 problem.

After above setting up: when you try to get parent, child will not come together. If you want to get all of them let consider
using `@EntityGrapths` with **JPA Query Method** to fetch the child with parent.
```java
@Entity
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @PrimaryKeyJoinColumn
    private Address address;
}

@Entity
@Table(name = "app_user_address")
public class Address {

    @Id
    @Column(name = "user_id")
    private Long id;

    private String value;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;
}
```
- [The best way to use the JPA OneToOne optional attribute](https://vladmihalcea.com/best-way-onetoone-optional/)
#### Cascade Type



---

## Persistence Type

---
## Fetch Type
### LAZY
- Why lazy fetch must be in transactional context
### EAGER
- Why eager sometimes face **N+1** problem.
### Best Practices
- Consider Lazy for all, using `@EntityGraphs` or 
`JPA Query Method` to get associated entities.

---
## Best Practices
### Understand entity state to optimize JPA functionalities.