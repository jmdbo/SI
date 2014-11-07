
(defrule EntryReady_Rule
    (PieceInEntryStation false)
    (ElevatorAtEntryStation true)
    (LoadingFromStationToElevator true)
=> (assert (Error)))


(defrule EntryLoad_Rule
     (PieceInElevator false)
    (ElevatorLoadComplete true)
    (LoadingFromStationToElevator true)
=> (assert (Error)))


(defrule OccupiedStation_Rule
    (ExitStationEmpty false)
    (ElevatorAtExitStation true)
    (RemovingFromStorageToElevator true)
=> (assert (Error)))

(defrule NoPiece_Rule
    (PieceInElevator false)
    (ElevatorAtStorageStation true)
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
    (ElevatorLoadComplete true)
    (PieceInElevator false)
    (RemovingFromStorageToElevator true)
=> (assert (Error)))


