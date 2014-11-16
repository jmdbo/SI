(deftemplate Position
(slot x (default ?NONE))
(slot y (default ?NONE))
)

(defrule EntryReady_Rule
    (PieceInStation ?b)
    (ElevatorAtStation ?a)
    (S_LOAD_STATION)
    (test (eq ?b false))
    (test (or (eq ?a Left) (eq ?a Right)))
=> (assert (Error)))


(defrule EntryLoad_Rule
    (PieceInElevator ?a)
    (S_LOAD_STATION_DONE)
    (ElevatorAtStation ?b)
    (test (eq ?a true))
    (test (or (eq ?b Left) (eq ?b Right)))
=> (assert (Error)))

(defrule OccupiedStation_Rule
    (PieceInStation ?b)
    (ElevatorAtStation ?a)
    (S_PUT_STATION)
    (test (eq ?a ?b))
=> (assert (Error)))

(defrule NoPiece_Rule
    (PieceInElevator false)
    (ElevatorAtCell true)
    (StoringPiece true)
=> (assert (Error)))

(defrule StorageFull_Rule
    (StorageFull true)
    (StoringPiece true)
   => (assert (Error)))

(defrule StorageEmpty_Rule
    (StorageEmpty true)
    (RetrievingPiece true)
=> (assert (Error)))

(defrule LostPieceOnRetrieval_Rule
    (ElevatorLoadFromCellComplete true)
    (PieceInElevator false)
    (RemovingFromStorageToElevator true)
=> (assert (Error)))


