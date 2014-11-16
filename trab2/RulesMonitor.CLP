(deftemplate Estado
(deftemplate Position
(slot x (default ?NONE))
(slot y (default ?NONE))
(slot z (default ?NONE))
)


(defrule EntryReady_Rule
    (PieceInStation ?b)
    (ElevatorAtStation ?a)
    (SimpleAction LOAD_STATION)
    (test (eq ?b false))
    (test (or (eq ?a Left) (eq ?a Right)))
=> (assert (Error)))


(defrule EntryLoad_Rule
    (PieceInElevator ?a)
    (SimpleAction LOAD_STATION_DONE)
    (ElevatorAtStation ?b)
    (test (eq ?a true))
    (test (or (eq ?b Left) (eq ?b Right)))
=> (assert (Error)))

(defrule OccupiedStation_Rule
    (PieceInStation ?b)
    (ElevatorAtStation ?a)
    (SimpleAction PUT_STATION)
    (test (eq ?a ?b))
=> (assert (Error)))

(defrule NoPiece_Rule
    (PieceInElevator false)
    (SimpleAction PUT_CELL)
    (Position (x ?a) (y ?c) (z ?b))
    (test (eq ?c Middle))
    (test (neq ?a -1))
    (test (neq ?b -1))
=> (assert (Error)))

(defrule StorageFull_Rule
    (StorageFull true)
    (ComplexAction STORE_PIECE)
   => (assert (Error)))

(defrule StorageEmpty_Rule
    (StorageEmpty true)
    (ComplexAction GET_PIECE)
=> (assert (Error)))

(defrule LostPieceOnRetrieval_Rule
    (SimpleAction GET_CELL_DONE)
    (PieceInElevator false)
=> (assert (Error)))


