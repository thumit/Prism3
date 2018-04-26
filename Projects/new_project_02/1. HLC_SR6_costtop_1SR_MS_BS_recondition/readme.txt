Model is last edited by:     PRISM ALPHA 1.1.03     on     2018/04/19   -   23:14:53
Model is created by:     PRISM ALPHA 1.0.27     on     2018/02/13   -   12:53:03
Model location:     C:\EProgramFiles\PRISM\Projects\HLC_DEBUG1\MaxVT_SZ_ND_Harv_Con3
Model database:     C:\EProgramFiles\PRISM\Projects\HLC_DEBUG1\MaxVT_SZ_ND_Harv_Con3\database.db
Original database:     C:\EProgramFiles\PRISM\Databases\HLC_DEBUG5.db
------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------- ADDITIONAL MODEL DESCRIPTION -----------------------------------------------------
------------------------------------------------------------------------------------------------------------------------------------------------------------------
This run - SR6 - debug 2:
Sensitivity Run 6 - MSF constraints only on types - no hardwired natural disturbances

-Adds a budget constraint (and defines the costs!)

-Includes operational limits (such as min/max acres possible for burning, appropriate silv. mix [UE])
-Includes Lynx Constraints
- Include Opening limitations

-Reordered veg H even age yield tables to include no pct yield streams first
- put in a constraint for no pct activity ever in lynx habitat (L and B) in types H and G (dynamic type > 0)
- BUG FIX - "bs" action type was used in the min/max prescribed burn constraint instead of "bb" - reduced volume to 1/3 potential
- Added Rx Burn timing choices for G/W regen and F/V regen, and some existing types as well.

Previous:
- No harvest restrictions in MAG 2-6 (none allowed in 1)
- Objective is only to minimize vegetation and size composition penalty - equally weighted
- Stand-replacing fire, bark beetle, and mixed-severity fire are introduced

- MSF constraint on veg types only for periods 1-5
- MSF total fire constraint (98,500) for perids 1-15
- SRF total approx. 82,000 acres perios 1-15 distributed across veg types
- Bark Beetle 92,100 acres in Period 4, 10

- NDEF constraint
- MAG proportional harvest constraints
- Max UE harvest constraint
- Max Comm Thinning constraint
- Rx Burn acres 20-100k/decade


Feb1-Run1
Updated bark beetle yield tables to allow for period 4 BB outbreak and types H/I/J/K to get BB in period 4.
Allocated BB across vulnerable size classes with the same probability to achieve the anticipated total acres
Max BB with these percentages is 92159 acres
