
(defrule EntryReady_Rule
    (PieceInEntryStation false)
    (ElevatorAtEntryStation true)
    (LoadingFromStationToElevator true)
=> (assert (EntryReadyError)))


(defrule EntryLoad_Rule
     (PieceInElevator false)
    (ElevatorLoadComplete true)
    (LoadingFromStationToElevator true)
=> (assert (EntryLoadError)))


(defrule OccupiedStation_Rule
    (ExitStationEmpty false)
    (ElevatorAtExitStation true)
    (RemovingFromStorageToElevator true)
=> (assert (OccupiedStationError)))

(defrule NoPiece_Rule
    (PieceInElevator false)
    (ElevatorAtStorageStation true)
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
    (ElevatorLoadComplete true)
    (PieceInElevator false)
    (RemovingFromStorageToElevator true)
=> (assert (LostPieceOnRetrievalError)))


