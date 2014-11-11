(defrule EntryReady_Rule
    (PieceInStation false)
    (ElevatorAtStation true)
    (LoadingFromStationToElevator true)
=> (assert (Error)))


(defrule EntryLoad_Rule
     (PieceInElevator false)
    (ElevatorLoadFromStationComplete true)
    (LoadingFromStationToElevator true)
=> (assert (Error)))


(defrule OccupiedStation_Rule
    (PieceInStation true)
    (ElevatorAtStation true)
    (RemovingFromCellToElevator true)
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


