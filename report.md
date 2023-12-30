# Capitolo 1 - Analisi
## 1.1 Requisiti
Il software, ispirato alla modalità Robo-Rumble di Brawl Stars della Supercell, è un gioco 2d top-down il cui obiettivo è quello di difendere per più tempo possibile il centro della mappa e sopravvivere agli attacchi continui di orde nemiche.
### Requisiti funzionali
- La partita termina in caso di esaurimento dei punti vita del giocatore o del centro della mappa.
- Il player:
  - deve essere controllabile tramite tastiera nelle quattro direzioni.
  - deve essere in grado di sparare proiettili ai nemici in una direzione scelta tramite il mouse.
- I nemici:
  - nascono ad ondate.
  - attaccano il centro della mappa o il giocatore.
- Il livello di difficoltà cresce man mano che il gioco avanza.
- Il gioco è top-down con visuale dall'alto.
- L'ambiente di gioco è delimitato e include ostacoli fisici che il giocatore può usare come copertura.
### Requisiti non funzionali
- Il gioco si presenta con una grafica minimale composta da figure geometriche.
- L'applicazione si presenta con un menu iniziale.
- Il software:
  - deve essere eseguibile in modo fluido.
  - deve essere compatibile con una varia gamma di hardware e sistemi operativi (Linux, Windows e MacOS). 
## 1.2 Analisi e modello del dominio
Il gioco ha luogo all'interno di una mappa, la quale contiene il giocatore, i nemici e gli oggetti permanenti (e.g. muri e cassa centrale). Il player e i nemici devono potersi muovere liberamente all'interno di essa, secondo la volontà del giocatore oppure in base ad un' AI stabilita e possono sparare proiettili per cercare di danneggiare il proprio obiettivo. Il player, i nemici e la cassa infatti hanno un quantitativo di punti vita e all'esaurimento di questi l'entità muore.
```mermaid
classDiagram 
    GameEntity <|-- AliveEntity
    GameEntity <|-- MovableEntity
    GameEntity <|-- Obstacle

    AliveEntity <|-- Chest
    AliveEntity<|--ActiveEntity
    MovableEntity<|--Projectile
    MovableEntity<|--ActiveEntity

    ActiveEntity<|--Player
    ActiveEntity<|--Enemy

    AI --o Enemy
    GameEntity o-- Position2D

    <<Interface>> AI
    <<Interface>> Position2D
    <<Interface>> ActiveEntity
    <<Interface>> Obstacle
    <<Interface>> MovableEntity
    <<Interface>> Projectile
    <<Interface>> Chest
    <<Interface>> Player
    <<Interface>> Enemy
    <<Interface>> AliveEntity
    <<Interface>> GameEntity

    class GameEntity{
        getPosition() Position2D
    }

    class AliveEntity{
        getLife() int

    }

    class MovableEntity{
        move()  void
    }

    class Position2D {
    }

    class ActiveEntity{
        shoot() void
    }
```
# Capitolo 2 - Design
## 2.1 Architettura
## 2.2 Design dettagliato
# Capitolo 3 - Sviluppo
## 3.1 Testing automatizzato
## 3.2 Note di sviluppo
### Baldazzi Andrea
### Bittasi Francesco
### Marchi Luca
### Monaco Andrea
# Capitolo 4 - Commenti finali
## 4.1 Autovalutazione e lavori futuri
## 4.2 Difficoltà incontrate e commenti per i docenti
