Model is last edited by:     PRISM ALPHA 1.2.07     on     2019/06/27   -   11:35:29
Model is created by:     PRISM ALPHA 1.1.08     on     2018/06/12   -   09:12:47
Model location:     C:\EProgramFiles\PRISM\Projects\CG_DEIS\AltA_D1_06112018a
Model database:     C:\EProgramFiles\PRISM\Projects\CG_DEIS\AltA_D1_06112018a\database.db
Original database:     C:\EProgramFiles\PRISM\Projects\CG_DEIS\AltA_D1_06112018a\database.db
------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------- ADDITIONAL MODEL DESCRIPTION -----------------------------------------------------
------------------------------------------------------------------------------------------------------------------------------------------------------------------
0611a run:
* Used new landbase from Mary Gonzalez 6/7/2018. This had major differences in the CM and COLD acres
* Updated Lynx and opening constraints to this landbase
* Updated DCs based on these acres
* Had to adjust the amout of MSF and minimum acres treated to accommodate the new landbase

* D1-D6 have higher DC value

*Volume goal is 6 million BOARD feet for D1

0614a run:
* overwrote 0611a (which is why you don't see it)
* cleaned up costs - similar costs moved to single input line (such as per cuft). Corrected the pct cost for Veg H (was $385 per acre, but should have been $128)
* removed planting costs in non-timber mags - only incur disturbance costs in MAG 5&6
*Tried 60,000 acres min. treatment with these lower costs

*Redistributed some of SRF from highest class to lowest density class (5% weight)

0614b run:
* Use assumptions from 0614a - for SRF etc.
* Budget set at 4.6 million annually to achieve at least 15 mmbf D1 and 13 MMBF D2-15
* Budget determined by minimizing costs to achieve the above volumes, finding the average for D1-15 and increasing by 2%
* Objective function is to meet DCs

0614c run:
* Same as 0614b but with a different volume constraint: 6 MMBF D1 and 5 MMBF D2-15

0614d run:
* No Overall budget constraint - still maintain proportional constraints
* No volume constraints
* Maintain proportional volume constraints (per side of the forest) and NDEF
* Maintain minimum acres treated constraints

0614d1 run:
* Start with (d) unconstrained conditions
* add in prescribed burn opportunity everywhere
* add in GS to MAG2
