// package checkers.Spring;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// @RestController
// @RequestMapping("/moves")
// public class GameMoveController {

//     @Autowired
//     private GameMoveService gameMoveService;

//     // Pobierz wszystkie ruchy
//     @GetMapping
//     public List<GameMove> getAllMoves() {
//         return gameMoveService.getAllMoves();
//     }

//     // Pobierz ruchy dla konkretnego gracza
//     @GetMapping("/player/{playerId}")
//     public List<GameMove> getMovesByPlayer(@PathVariable int playerId) {
//         return gameMoveService.getMovesByPlayerId(playerId);
//     }
// }

