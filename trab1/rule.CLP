
(defrule erro1
 (pecainStation false) 
(elevatorinStation true)
=> (assert (erro1)))


(defrule erro2
 (loadElevator true)
(ElevatorEmpty true)
=> (assert (erro2)))


(defrule erro3
 (ExitStationEmpty false)
(elevatorinStation true)
=> (assert (erro2)))

