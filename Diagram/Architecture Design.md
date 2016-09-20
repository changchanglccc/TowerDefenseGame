# Architecture Design 


## MVC desgin structure


- Controller:
![Image of controller](Diagram/Contoller.png) 

- Model  
    - Tower Model: 
    ![Image of tower model](Diagram/TowerModel.png)
    
    - Critter Model:
    ![Image of critter model](Diagram/CritterModel.png)
    
    - Game Map Model:
    ![Image of Game Map model](Diagram/GameMapModel.png)
    
    - Wave Model:
    ![Image of Wave Model](Diagram/WaveModel.png)
    
    - Log Model:
    ![Image of Log model](Diagram/Log.png)

- View
    - TowerView
    - CritterView
    - MapView
    - GameLogView
    - MainGameView
    - MainMenuView
    - MapChooseView
    - MapEditorView
    
- Game logic part:
    - We use a main game timer (refresh timer = 100ms) to refresh the whole game.
    - We use Json file to save GameMap
    - We use XML file to save Game state

- Design Partterns
    - Factory Pattern:
        - WaveFactory: Create waves based on wave number
        - TowerFactory: Create towers based on TowerType
    - Singleton Pattern:
        - LoggerCollection: One logger collection  collects whole game's log
        - WaveFactory: One Factory for create waves
        - TowerFactory: One Factory for create towers
    - Builder Pattern:
        - WaveBuilder: Build wave by create diffente kinds of critters
    - Strategy Pattern:
        - TowerShootingStrategy: Tower targeting strategy 
    - Observer Pattern:
        - Whole Game is bascially based on Observer pattern
        - When the models changes, tell its view to change.
        
     