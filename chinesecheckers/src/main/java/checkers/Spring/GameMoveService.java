// package checkers.Spring;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// @Service
// public class GameMoveService {

//     @Autowired
//     private GameMoveRepository gameMoveRepository;

//     // Pobierz wszystkie ruchy
//     public List<GameMove> getAllMoves() {
//         return gameMoveRepository.findAll();
//     }

//     // Pobierz ruchy dla konkretnego gracza
//     public List<GameMove> getMovesByPlayerId(int playerId) {
//         return gameMoveRepository.findByPlayerId(playerId);
//     }

//     public void saveMove(int playerId, int startX, int startY, int endX, int endY) {
//         GameMove gameMove = new GameMove();
//         gameMove.setPlayerId(playerId);
//         gameMove.setStartX(startX);
//         gameMove.setStartY(startY);
//         gameMove.setEndX(endX);
//         gameMove.setEndY(endY);

//         gameMoveRepository.save(gameMove);
//     }
// }

