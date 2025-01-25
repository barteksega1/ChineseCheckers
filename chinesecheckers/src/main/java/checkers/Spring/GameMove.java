// package checkers.Spring;
// import jakarta.persistence.*;

// @Entity
// @Table(name = "game_moves")
// public class GameMove {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @Column(nullable = false)
//     private int playerId;

//     @Column(nullable = false)
//     private int startX;

//     @Column(nullable = false)
//     private int startY;

//     @Column(nullable = false)
//     private int endX;

//     @Column(nullable = false)
//     private int endY;

//     //Getters && Setters
//     public void setPlayerId(int playerId) {
//         this.playerId = playerId;
//     }
//     public void setEndX(int endX) {
//         this.endX = endX;
//     }
//     public void setEndY(int endY) {
//         this.endY = endY;
//     }
//     public void setId(Long id) {
//         this.id = id;
//     }
//     public void setStartX(int startX) {
//         this.startX = startX;
//     }
//     public void setStartY(int startY) {
//         this.startY = startY;
//     }
//     public int getEndX() {
//         return endX;
//     }
//     public int getEndY() {
//         return endY;
//     }
//     public Long getId() {
//         return id;
//     }
//     public int getPlayerId() {
//         return playerId;
//     }
//     public int getStartX() {
//         return startX;
//     }
//     public int getStartY() {
//         return startY;
//     }
// }
