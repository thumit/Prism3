Model is last edited by:     PRISM ALPHA 1.1.01     on     2018/04/18   -   13:47:16
PA_21_15 10/11/2017

Used 1.0.11 version of PRISM

Uses Proposed Action Analysis Areas
15 time periods
NDEF on cubic volume
Ending inventory constraint on suited volume (per 15 >= average period (5-14)
MSF from SYL calibration
B type sometimes converts to U
Only NG, MS, BS on MAG1
MAG 2,3,4,5,6 allow PB, GS
MAG 5,6 allow EA
Soft constraints for size goals

Set Discount Rate = 0 because the constraint level was discounted but the costs were not

Costs and budget from first draft of figures used. Real budget increased for first 5 decades then was non-binding
U was allowed to be planted across all MAGs
Added a burn constraint of 10,000 acres per year; included all prescriptions across all GAs, land allocations.
Turned on Percentage spending by WUI/Non-WUI constraint (flow) - first 5 decades
Added Opening Constraints by MAG to max 30% of area based on decay functions, etc. described in design document

Changed the penalty for DC goals in periods 6-15 to 0.5 rather than 1. 
    Emphasis should be on achieving DCs in the first 5 decades, but still give value to later periods in maintaining them when possible. 
    But, any trade-offs should go to earlier periods.
Added Lynx constraints - see Constraints.xlsx in the PRISM/CONSTRAINTS folder
NDEF constraint applied to only MAG 5 and 6
Volume in Period 1 weight changed from 0.5 to 0.05
// <old> Try modifying ending inventory constraint to be volume in last period AFTER harvest >= avg. inventory

Add budget constraint for all 15 periods - allocation constraint only to first 5, though
Add in the rest of the DC constraints - size and covertype - soft
Had to fix the way DC constraints were applied - old: Based on static covertype New: Based on Dynamic "vegt"
Tried Ending Inventory mod: avg. inventory 5-14 PLUS Period 15 harvest >= Period 15 before-harvest inventory
Relax penalty of period 6-15 on size and density goals to 0.5
Lynx Habitat restrictions applied to both Occupied and Unoccupied Habitat
Removed planting option for U type
Removed period 1 volume target. This run is DC only
Lowered MSF % for B type to 2/3 previous level
Lowered SRF % for B type to 2/3 previous level (was 14, lowered to 9)
Had to relax the MSF lower bound to account for less fire

**New**
Corrected some opening calculation errors in yield tables
Added GS prescriptions for management of cool/cold types
