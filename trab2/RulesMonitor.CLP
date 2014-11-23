(deftemplate Action
(slot complx (default ?NONE))
(slot simpl  (default ?NONE))
)

(deftemplate Erro
(slot id (default ?NONE))
(slot msg (default ?NONE))
)

(deftemplate PositionXYZ
(slot x (default ?NONE))
(slot y (default ?NONE))
(slot z (default ?NONE))
)

(deftemplate PieceInStation
(slot Position (default ?NONE))
)

(deftemplate ElevatorAtStation
(slot Position (default ?NONE))
)
(deftemplate PieceInElevator
(slot State (default ?NONE))
)

(defrule EntryReady_Rule
    (PieceInStation (Position ?b))
    (Action (complx ?c) (simpl ?d))
    (ElevatorAtStation (Position ?a))
    (test (neq ?b ?a))
    (test (eq ?c PUT_PIECE))
    (test (eq ?d AT_STATION))
    (test (or (eq ?a Left) (eq ?a Right)))
=> (assert (Erro (id 1) (msg Erro))))


(defrule EntryLoad_Rule
    (PieceInElevator (State ?a))
    (Action (complx ?c) (simpl ?d))
    (test (eq ?a false))
    (test (eq ?c PUT_PIECE))
    (test (eq ?d STATION_LOAD_DONE))
=> (assert (Erro (id 2) (msg Erro))))

(defrule OccupiedStation_Rule
    (PieceInStation (Position ?b))
    (ElevatorAtStation (Position ?a))
    (Action (complx ?c)(simpl ?d))
    (test (eq ?a ?b))
    (test (neq ?a false))
    (test (eq ?c GET_PIECE))
    (test (eq ?d STATION_PUT_BEGIN))
=> (assert (Erro (id 3) (msg Erro))))

(defrule NoPiece_Rule
    (PieceInElevator (State false))
    (Action (complx PUT_PIECE) (simpl AT_CELL))    
=> (assert (Erro (id 4) (msg Erro))))

(defrule StorageFull_Rule
    (StorageFull ?c)
    (Action (complx ?b)(simpl ?a))
    (test (eq ?b PUT_PIECE))
    (test (eq ?c true))
=> (assert (Erro (id 5) (msg Erro))))

(defrule StorageEmpty_Rule
    (StorageEmpty ?a)
    (Action (complx ?b) (simpl ?c))
    (test (eq ?b GET_PIECE))
    (test (eq ?a true))
=> (assert (Erro (id 6) (msg Erro))))

(defrule LostPieceOnRetrieval_Rule
    (Action (complx ?a)(simpl ?b))
    (PieceInElevator (State false))
    (test (eq ?b CELL_GET_DONE))
=> (assert (Erro (id 7) (msg Erro))))

