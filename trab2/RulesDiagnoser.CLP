(defrule EntryReady_Rule
    (PieceInStation false)
    (ElevatorAtStation true)
    (LoadingFromStationToElevator true)
=> (assert (EntryReadyError)))

(defrule EntryLoad_Rule
     (PieceInElevator false)
    (ElevatorLoadFromStationComplete true)
    (LoadingFromStationToElevator true)
=> (assert (EntryLoadError)))

(defrule OccupiedStation_Rule
    (PieceInStation true)
    (ElevatorAtStation true)
    (RemovingFromCellToElevator true)
=> (assert (OccupiedStationError)))

(defrule NoPiece_Rule
    (PieceInElevator false)
    (ElevatorAtCell true)
    (StoringPiece true)
=> (assert (NoPieceError)))

(defrule StorageFull_Rule
    (StorageFull true)
    (StoringPiece true)
   => (assert (StorageFullError)))

(defrule StorageEmpty_Rule
    (StorageEmpty true)
    (RetrievingPiece true)
=> (assert (StorageEmptyError)))

(defrule LostPieceOnRetrieval_Rule
    (ElevatorLoadFromCellComplete true)
    (PieceInElevator false)
    (RemovingFromStorageToElevator true)
=> (assert (LostPieceOnRetrievalError)))