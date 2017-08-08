\ENCODING=ISO-8859-1
\Problem name: ilog.cplex

Minimize
 obj:
Subject To
 c1:   x_C,R,N,R,P,M,0 + x_C,R,N,R,P,M,1 + x_C,R,N,R,P,M,2 + x_C,R,N,R,P,M,3
       + x_C,R,N,R,P,M,4 + x_C,R,N,R,P,M,5  = 90
 c2:   x_C,R,N,R,P,M,4 - xMS_E_C,R,N,R,P,M,0,1 - xMS_E_C,R,N,R,P,M,1,1
       - xMS_E_C,R,N,R,P,M,2,1 - xMS_E_C,R,N,R,P,M,3,1 - xMS_E_C,R,N,R,P,M,4,1
        = 0
 c3:   x_C,R,N,R,P,M,4 - xMS_E_C,R,N,R,P,M,0,2 - xMS_E_C,R,N,R,P,M,1,2
       - xMS_E_C,R,N,R,P,M,2,2 - xMS_E_C,R,N,R,P,M,3,2 - xMS_E_C,R,N,R,P,M,4,2
        = 0
 c4:   x_C,R,N,R,P,M,4 - xMS_E_C,R,N,R,P,M,0,3 - xMS_E_C,R,N,R,P,M,1,3
       - xMS_E_C,R,N,R,P,M,2,3 - xMS_E_C,R,N,R,P,M,3,3 - xMS_E_C,R,N,R,P,M,4,3
        = 0
 c5:   x_C,R,N,R,P,M,0 - xNG_E_C,R,N,R,P,M,1  = 0
 c6:   0.998 xNG_E_C,R,N,R,P,M,1 - xNG_E_C,R,N,R,P,M,2  = 0
 c7:   0.998 xNG_E_C,R,N,R,P,M,2 - xNG_E_C,R,N,R,P,M,3  = 0
 c8:   x_C,R,N,R,P,M,1 - xPB_E_C,R,N,R,P,M,0,1 - xPB_E_C,R,N,R,P,M,1,1
       - xPB_E_C,R,N,R,P,M,2,1 - xPB_E_C,R,N,R,P,M,3,1 - xPB_E_C,R,N,R,P,M,4,1
        = 0
 c9:   0.998 xPB_E_C,R,N,R,P,M,0,1 - xPB_E_C,R,N,R,P,M,0,2  = 0
 c10:  0.998 xPB_E_C,R,N,R,P,M,0,2 - xPB_E_C,R,N,R,P,M,0,3  = 0
 c11:  0.998 xPB_E_C,R,N,R,P,M,1,1 - xPB_E_C,R,N,R,P,M,1,2  = 0
 c12:  0.998 xPB_E_C,R,N,R,P,M,1,2 - xPB_E_C,R,N,R,P,M,1,3  = 0
 c13:  0.998 xPB_E_C,R,N,R,P,M,2,1 - xPB_E_C,R,N,R,P,M,2,2  = 0
 c14:  0.998 xPB_E_C,R,N,R,P,M,2,2 - xPB_E_C,R,N,R,P,M,2,3  = 0
 c15:  0.998 xPB_E_C,R,N,R,P,M,3,1 - xPB_E_C,R,N,R,P,M,3,2  = 0
 c16:  0.998 xPB_E_C,R,N,R,P,M,3,2 - xPB_E_C,R,N,R,P,M,3,3  = 0
 c17:  0.998 xPB_E_C,R,N,R,P,M,4,1 - xPB_E_C,R,N,R,P,M,4,2  = 0
 c18:  0.998 xPB_E_C,R,N,R,P,M,4,2 - xPB_E_C,R,N,R,P,M,4,3  = 0
 c19:  x_C,R,N,R,P,M,2 - xGS_E_C,R,N,R,P,M,0,1 - xGS_E_C,R,N,R,P,M,1,1
       - xGS_E_C,R,N,R,P,M,2,1 - xGS_E_C,R,N,R,P,M,3,1 - xGS_E_C,R,N,R,P,M,4,1
        = 0
 c20:  0.998 xGS_E_C,R,N,R,P,M,0,1 - xGS_E_C,R,N,R,P,M,0,2  = 0
 c21:  0.998 xGS_E_C,R,N,R,P,M,0,2 - xGS_E_C,R,N,R,P,M,0,3  = 0
 c22:  0.998 xGS_E_C,R,N,R,P,M,1,1 - xGS_E_C,R,N,R,P,M,1,2  = 0
 c23:  0.998 xGS_E_C,R,N,R,P,M,1,2 - xGS_E_C,R,N,R,P,M,1,3  = 0
 c24:  0.998 xGS_E_C,R,N,R,P,M,2,1 - xGS_E_C,R,N,R,P,M,2,2  = 0
 c25:  0.998 xGS_E_C,R,N,R,P,M,2,2 - xGS_E_C,R,N,R,P,M,2,3  = 0
 c26:  0.998 xGS_E_C,R,N,R,P,M,3,1 - xGS_E_C,R,N,R,P,M,3,2  = 0
 c27:  0.998 xGS_E_C,R,N,R,P,M,3,2 - xGS_E_C,R,N,R,P,M,3,3  = 0
 c28:  0.998 xGS_E_C,R,N,R,P,M,4,1 - xGS_E_C,R,N,R,P,M,4,2  = 0
 c29:  0.998 xGS_E_C,R,N,R,P,M,4,2 - xGS_E_C,R,N,R,P,M,4,3  = 0
 c30:  - 0.002 xNG_E_C,R,N,R,P,M,1 - 0.002 xPB_E_C,R,N,R,P,M,0,1
       - 0.002 xPB_E_C,R,N,R,P,M,1,1 - 0.002 xPB_E_C,R,N,R,P,M,2,1
       - 0.002 xPB_E_C,R,N,R,P,M,3,1 - 0.002 xPB_E_C,R,N,R,P,M,4,1
       - 0.002 xGS_E_C,R,N,R,P,M,0,1 - 0.002 xGS_E_C,R,N,R,P,M,1,1
       - 0.002 xGS_E_C,R,N,R,P,M,2,1 - 0.002 xGS_E_C,R,N,R,P,M,3,1
       - 0.002 xGS_E_C,R,N,R,P,M,4,1 + fire_C,R,N,R,P,1,P + fire_C,R,N,R,P,1,D
       + fire_C,R,N,R,P,1,W + fire_C,R,N,R,P,1,C + fire_C,R,N,R,P,1,I
       + fire_C,R,N,R,P,1,A + fire_C,R,N,R,P,1,L + fire_C,R,N,R,P,1,N  = 0
 c31:  - 0.002 xNG_E_C,R,N,R,P,M,2 - 0.002 xPB_E_C,R,N,R,P,M,0,2
       - 0.002 xPB_E_C,R,N,R,P,M,1,2 - 0.002 xPB_E_C,R,N,R,P,M,2,2
       - 0.002 xPB_E_C,R,N,R,P,M,3,2 - 0.002 xPB_E_C,R,N,R,P,M,4,2
       - 0.002 xGS_E_C,R,N,R,P,M,0,2 - 0.002 xGS_E_C,R,N,R,P,M,1,2
       - 0.002 xGS_E_C,R,N,R,P,M,2,2 - 0.002 xGS_E_C,R,N,R,P,M,3,2
       - 0.002 xGS_E_C,R,N,R,P,M,4,2 - 0.002 xNG_R_C,R,N,R,P,2,1
       + fire_C,R,N,R,P,2,P + fire_C,R,N,R,P,2,D + fire_C,R,N,R,P,2,W
       + fire_C,R,N,R,P,2,C + fire_C,R,N,R,P,2,I + fire_C,R,N,R,P,2,A
       + fire_C,R,N,R,P,2,L + fire_C,R,N,R,P,2,N  = 0
 c32:  - 0.002 xNG_E_C,R,N,R,P,M,3 - 0.002 xPB_E_C,R,N,R,P,M,0,3
       - 0.002 xPB_E_C,R,N,R,P,M,1,3 - 0.002 xPB_E_C,R,N,R,P,M,2,3
       - 0.002 xPB_E_C,R,N,R,P,M,3,3 - 0.002 xPB_E_C,R,N,R,P,M,4,3
       - 0.002 xGS_E_C,R,N,R,P,M,0,3 - 0.002 xGS_E_C,R,N,R,P,M,1,3
       - 0.002 xGS_E_C,R,N,R,P,M,2,3 - 0.002 xGS_E_C,R,N,R,P,M,3,3
       - 0.002 xGS_E_C,R,N,R,P,M,4,3 - 0.002 xNG_R_C,R,N,R,P,3,1
       - 0.002 xNG_R_C,R,N,R,P,3,2 + fire_C,R,N,R,P,3,P + fire_C,R,N,R,P,3,D
       + fire_C,R,N,R,P,3,W + fire_C,R,N,R,P,3,C + fire_C,R,N,R,P,3,I
       + fire_C,R,N,R,P,3,A + fire_C,R,N,R,P,3,L + fire_C,R,N,R,P,3,N  = 0
 c33:  fire_C,R,N,R,D,1,P + fire_C,R,N,R,D,1,D + fire_C,R,N,R,D,1,W
       + fire_C,R,N,R,D,1,C + fire_C,R,N,R,D,1,I + fire_C,R,N,R,D,1,A
       + fire_C,R,N,R,D,1,L + fire_C,R,N,R,D,1,N  = 0
 c34:  - 0.002 xNG_R_C,R,N,R,D,2,1 + fire_C,R,N,R,D,2,P + fire_C,R,N,R,D,2,D
       + fire_C,R,N,R,D,2,W + fire_C,R,N,R,D,2,C + fire_C,R,N,R,D,2,I
       + fire_C,R,N,R,D,2,A + fire_C,R,N,R,D,2,L + fire_C,R,N,R,D,2,N  = 0
 c35:  - 0.002 xNG_R_C,R,N,R,D,3,1 - 0.002 xNG_R_C,R,N,R,D,3,2
       + fire_C,R,N,R,D,3,P + fire_C,R,N,R,D,3,D + fire_C,R,N,R,D,3,W
       + fire_C,R,N,R,D,3,C + fire_C,R,N,R,D,3,I + fire_C,R,N,R,D,3,A
       + fire_C,R,N,R,D,3,L + fire_C,R,N,R,D,3,N  = 0
 c36:  fire_C,R,N,R,W,1,P + fire_C,R,N,R,W,1,D + fire_C,R,N,R,W,1,W
       + fire_C,R,N,R,W,1,C + fire_C,R,N,R,W,1,I + fire_C,R,N,R,W,1,A
       + fire_C,R,N,R,W,1,L + fire_C,R,N,R,W,1,N  = 0
 c37:  - 0.002 xNG_R_C,R,N,R,W,2,1 + fire_C,R,N,R,W,2,P + fire_C,R,N,R,W,2,D
       + fire_C,R,N,R,W,2,W + fire_C,R,N,R,W,2,C + fire_C,R,N,R,W,2,I
       + fire_C,R,N,R,W,2,A + fire_C,R,N,R,W,2,L + fire_C,R,N,R,W,2,N  = 0
 c38:  - 0.002 xNG_R_C,R,N,R,W,3,1 - 0.002 xNG_R_C,R,N,R,W,3,2
       + fire_C,R,N,R,W,3,P + fire_C,R,N,R,W,3,D + fire_C,R,N,R,W,3,W
       + fire_C,R,N,R,W,3,C + fire_C,R,N,R,W,3,I + fire_C,R,N,R,W,3,A
       + fire_C,R,N,R,W,3,L + fire_C,R,N,R,W,3,N  = 0
 c39:  fire_C,R,N,R,C,1,P + fire_C,R,N,R,C,1,D + fire_C,R,N,R,C,1,W
       + fire_C,R,N,R,C,1,C + fire_C,R,N,R,C,1,I + fire_C,R,N,R,C,1,A
       + fire_C,R,N,R,C,1,L + fire_C,R,N,R,C,1,N  = 0
 c40:  - 0.002 xNG_R_C,R,N,R,C,2,1 + fire_C,R,N,R,C,2,P + fire_C,R,N,R,C,2,D
       + fire_C,R,N,R,C,2,W + fire_C,R,N,R,C,2,C + fire_C,R,N,R,C,2,I
       + fire_C,R,N,R,C,2,A + fire_C,R,N,R,C,2,L + fire_C,R,N,R,C,2,N  = 0
 c41:  - 0.002 xNG_R_C,R,N,R,C,3,1 - 0.002 xNG_R_C,R,N,R,C,3,2
       + fire_C,R,N,R,C,3,P + fire_C,R,N,R,C,3,D + fire_C,R,N,R,C,3,W
       + fire_C,R,N,R,C,3,C + fire_C,R,N,R,C,3,I + fire_C,R,N,R,C,3,A
       + fire_C,R,N,R,C,3,L + fire_C,R,N,R,C,3,N  = 0
 c42:  fire_C,R,N,R,I,1,P + fire_C,R,N,R,I,1,D + fire_C,R,N,R,I,1,W
       + fire_C,R,N,R,I,1,C + fire_C,R,N,R,I,1,I + fire_C,R,N,R,I,1,A
       + fire_C,R,N,R,I,1,L + fire_C,R,N,R,I,1,N  = 0
 c43:  - 0.002 xNG_R_C,R,N,R,I,2,1 + fire_C,R,N,R,I,2,P + fire_C,R,N,R,I,2,D
       + fire_C,R,N,R,I,2,W + fire_C,R,N,R,I,2,C + fire_C,R,N,R,I,2,I
       + fire_C,R,N,R,I,2,A + fire_C,R,N,R,I,2,L + fire_C,R,N,R,I,2,N  = 0
 c44:  - 0.002 xNG_R_C,R,N,R,I,3,1 - 0.002 xNG_R_C,R,N,R,I,3,2
       + fire_C,R,N,R,I,3,P + fire_C,R,N,R,I,3,D + fire_C,R,N,R,I,3,W
       + fire_C,R,N,R,I,3,C + fire_C,R,N,R,I,3,I + fire_C,R,N,R,I,3,A
       + fire_C,R,N,R,I,3,L + fire_C,R,N,R,I,3,N  = 0
 c45:  fire_C,R,N,R,A,1,P + fire_C,R,N,R,A,1,D + fire_C,R,N,R,A,1,W
       + fire_C,R,N,R,A,1,C + fire_C,R,N,R,A,1,I + fire_C,R,N,R,A,1,A
       + fire_C,R,N,R,A,1,L + fire_C,R,N,R,A,1,N  = 0
 c46:  - 0.002 xNG_R_C,R,N,R,A,2,1 + fire_C,R,N,R,A,2,P + fire_C,R,N,R,A,2,D
       + fire_C,R,N,R,A,2,W + fire_C,R,N,R,A,2,C + fire_C,R,N,R,A,2,I
       + fire_C,R,N,R,A,2,A + fire_C,R,N,R,A,2,L + fire_C,R,N,R,A,2,N  = 0
 c47:  - 0.002 xNG_R_C,R,N,R,A,3,1 - 0.002 xNG_R_C,R,N,R,A,3,2
       + fire_C,R,N,R,A,3,P + fire_C,R,N,R,A,3,D + fire_C,R,N,R,A,3,W
       + fire_C,R,N,R,A,3,C + fire_C,R,N,R,A,3,I + fire_C,R,N,R,A,3,A
       + fire_C,R,N,R,A,3,L + fire_C,R,N,R,A,3,N  = 0
 c48:  fire_C,R,N,R,L,1,P + fire_C,R,N,R,L,1,D + fire_C,R,N,R,L,1,W
       + fire_C,R,N,R,L,1,C + fire_C,R,N,R,L,1,I + fire_C,R,N,R,L,1,A
       + fire_C,R,N,R,L,1,L + fire_C,R,N,R,L,1,N  = 0
 c49:  - 0.002 xNG_R_C,R,N,R,L,2,1 + fire_C,R,N,R,L,2,P + fire_C,R,N,R,L,2,D
       + fire_C,R,N,R,L,2,W + fire_C,R,N,R,L,2,C + fire_C,R,N,R,L,2,I
       + fire_C,R,N,R,L,2,A + fire_C,R,N,R,L,2,L + fire_C,R,N,R,L,2,N  = 0
 c50:  - 0.002 xNG_R_C,R,N,R,L,3,1 - 0.002 xNG_R_C,R,N,R,L,3,2
       + fire_C,R,N,R,L,3,P + fire_C,R,N,R,L,3,D + fire_C,R,N,R,L,3,W
       + fire_C,R,N,R,L,3,C + fire_C,R,N,R,L,3,I + fire_C,R,N,R,L,3,A
       + fire_C,R,N,R,L,3,L + fire_C,R,N,R,L,3,N  = 0
 c51:  fire_C,R,N,R,N,1,P + fire_C,R,N,R,N,1,D + fire_C,R,N,R,N,1,W
       + fire_C,R,N,R,N,1,C + fire_C,R,N,R,N,1,I + fire_C,R,N,R,N,1,A
       + fire_C,R,N,R,N,1,L + fire_C,R,N,R,N,1,N  = 0
 c52:  fire_C,R,N,R,N,2,P + fire_C,R,N,R,N,2,D + fire_C,R,N,R,N,2,W
       + fire_C,R,N,R,N,2,C + fire_C,R,N,R,N,2,I + fire_C,R,N,R,N,2,A
       + fire_C,R,N,R,N,2,L + fire_C,R,N,R,N,2,N  = 0
 c53:  fire_C,R,N,R,N,3,P + fire_C,R,N,R,N,3,D + fire_C,R,N,R,N,3,W
       + fire_C,R,N,R,N,3,C + fire_C,R,N,R,N,3,I + fire_C,R,N,R,N,3,A
       + fire_C,R,N,R,N,3,L + fire_C,R,N,R,N,3,N  = 0
 c54:  0.5 fire_C,R,N,R,P,1,P - 0.5 fire_C,R,N,R,P,1,D - 0.5 fire_C,R,N,R,P,1,W
       - 0.5 fire_C,R,N,R,P,1,C - 0.5 fire_C,R,N,R,P,1,I
       - 0.5 fire_C,R,N,R,P,1,A - 0.5 fire_C,R,N,R,P,1,L
       - 0.5 fire_C,R,N,R,P,1,N  = 0
 c55:  - 0.5 fire_C,R,N,R,P,1,P + 0.5 fire_C,R,N,R,P,1,D
       - 0.5 fire_C,R,N,R,P,1,W - 0.5 fire_C,R,N,R,P,1,C
       - 0.5 fire_C,R,N,R,P,1,I - 0.5 fire_C,R,N,R,P,1,A
       - 0.5 fire_C,R,N,R,P,1,L - 0.5 fire_C,R,N,R,P,1,N  = 0
 c56:  fire_C,R,N,R,P,1,W  = 0
 c57:  fire_C,R,N,R,P,1,C  = 0
 c58:  fire_C,R,N,R,P,1,I  = 0
 c59:  fire_C,R,N,R,P,1,A  = 0
 c60:  fire_C,R,N,R,P,1,L  = 0
 c61:  fire_C,R,N,R,P,1,N  = 0
 c62:  0.5 fire_C,R,N,R,P,2,P - 0.5 fire_C,R,N,R,P,2,D - 0.5 fire_C,R,N,R,P,2,W
       - 0.5 fire_C,R,N,R,P,2,C - 0.5 fire_C,R,N,R,P,2,I
       - 0.5 fire_C,R,N,R,P,2,A - 0.5 fire_C,R,N,R,P,2,L
       - 0.5 fire_C,R,N,R,P,2,N  = 0
 c63:  - 0.5 fire_C,R,N,R,P,2,P + 0.5 fire_C,R,N,R,P,2,D
       - 0.5 fire_C,R,N,R,P,2,W - 0.5 fire_C,R,N,R,P,2,C
       - 0.5 fire_C,R,N,R,P,2,I - 0.5 fire_C,R,N,R,P,2,A
       - 0.5 fire_C,R,N,R,P,2,L - 0.5 fire_C,R,N,R,P,2,N  = 0
 c64:  fire_C,R,N,R,P,2,W  = 0
 c65:  fire_C,R,N,R,P,2,C  = 0
 c66:  fire_C,R,N,R,P,2,I  = 0
 c67:  fire_C,R,N,R,P,2,A  = 0
 c68:  fire_C,R,N,R,P,2,L  = 0
 c69:  fire_C,R,N,R,P,2,N  = 0
 c70:  0.5 fire_C,R,N,R,P,3,P - 0.5 fire_C,R,N,R,P,3,D - 0.5 fire_C,R,N,R,P,3,W
       - 0.5 fire_C,R,N,R,P,3,C - 0.5 fire_C,R,N,R,P,3,I
       - 0.5 fire_C,R,N,R,P,3,A - 0.5 fire_C,R,N,R,P,3,L
       - 0.5 fire_C,R,N,R,P,3,N  = 0
 c71:  - 0.5 fire_C,R,N,R,P,3,P + 0.5 fire_C,R,N,R,P,3,D
       - 0.5 fire_C,R,N,R,P,3,W - 0.5 fire_C,R,N,R,P,3,C
       - 0.5 fire_C,R,N,R,P,3,I - 0.5 fire_C,R,N,R,P,3,A
       - 0.5 fire_C,R,N,R,P,3,L - 0.5 fire_C,R,N,R,P,3,N  = 0
 c72:  fire_C,R,N,R,P,3,W  = 0
 c73:  fire_C,R,N,R,P,3,C  = 0
 c74:  fire_C,R,N,R,P,3,I  = 0
 c75:  fire_C,R,N,R,P,3,A  = 0
 c76:  fire_C,R,N,R,P,3,L  = 0
 c77:  fire_C,R,N,R,P,3,N  = 0
 c78:  fire_C,R,N,R,D,1,P  = 0
 c79:  - 0.25 fire_C,R,N,R,D,1,P + 0.75 fire_C,R,N,R,D,1,D
       - 0.25 fire_C,R,N,R,D,1,W - 0.25 fire_C,R,N,R,D,1,C
       - 0.25 fire_C,R,N,R,D,1,I - 0.25 fire_C,R,N,R,D,1,A
       - 0.25 fire_C,R,N,R,D,1,L - 0.25 fire_C,R,N,R,D,1,N  = 0
 c80:  - 0.75 fire_C,R,N,R,D,1,P - 0.75 fire_C,R,N,R,D,1,D
       + 0.25 fire_C,R,N,R,D,1,W - 0.75 fire_C,R,N,R,D,1,C
       - 0.75 fire_C,R,N,R,D,1,I - 0.75 fire_C,R,N,R,D,1,A
       - 0.75 fire_C,R,N,R,D,1,L - 0.75 fire_C,R,N,R,D,1,N  = 0
 c81:  fire_C,R,N,R,D,1,C  = 0
 c82:  fire_C,R,N,R,D,1,I  = 0
 c83:  fire_C,R,N,R,D,1,A  = 0
 c84:  fire_C,R,N,R,D,1,L  = 0
 c85:  fire_C,R,N,R,D,1,N  = 0
 c86:  fire_C,R,N,R,D,2,P  = 0
 c87:  - 0.25 fire_C,R,N,R,D,2,P + 0.75 fire_C,R,N,R,D,2,D
       - 0.25 fire_C,R,N,R,D,2,W - 0.25 fire_C,R,N,R,D,2,C
       - 0.25 fire_C,R,N,R,D,2,I - 0.25 fire_C,R,N,R,D,2,A
       - 0.25 fire_C,R,N,R,D,2,L - 0.25 fire_C,R,N,R,D,2,N  = 0
 c88:  - 0.75 fire_C,R,N,R,D,2,P - 0.75 fire_C,R,N,R,D,2,D
       + 0.25 fire_C,R,N,R,D,2,W - 0.75 fire_C,R,N,R,D,2,C
       - 0.75 fire_C,R,N,R,D,2,I - 0.75 fire_C,R,N,R,D,2,A
       - 0.75 fire_C,R,N,R,D,2,L - 0.75 fire_C,R,N,R,D,2,N  = 0
 c89:  fire_C,R,N,R,D,2,C  = 0
 c90:  fire_C,R,N,R,D,2,I  = 0
 c91:  fire_C,R,N,R,D,2,A  = 0
 c92:  fire_C,R,N,R,D,2,L  = 0
 c93:  fire_C,R,N,R,D,2,N  = 0
 c94:  fire_C,R,N,R,D,3,P  = 0
 c95:  - 0.25 fire_C,R,N,R,D,3,P + 0.75 fire_C,R,N,R,D,3,D
       - 0.25 fire_C,R,N,R,D,3,W - 0.25 fire_C,R,N,R,D,3,C
       - 0.25 fire_C,R,N,R,D,3,I - 0.25 fire_C,R,N,R,D,3,A
       - 0.25 fire_C,R,N,R,D,3,L - 0.25 fire_C,R,N,R,D,3,N  = 0
 c96:  - 0.75 fire_C,R,N,R,D,3,P - 0.75 fire_C,R,N,R,D,3,D
       + 0.25 fire_C,R,N,R,D,3,W - 0.75 fire_C,R,N,R,D,3,C
       - 0.75 fire_C,R,N,R,D,3,I - 0.75 fire_C,R,N,R,D,3,A
       - 0.75 fire_C,R,N,R,D,3,L - 0.75 fire_C,R,N,R,D,3,N  = 0
 c97:  fire_C,R,N,R,D,3,C  = 0
 c98:  fire_C,R,N,R,D,3,I  = 0
 c99:  fire_C,R,N,R,D,3,A  = 0
 c100: fire_C,R,N,R,D,3,L  = 0
 c101: fire_C,R,N,R,D,3,N  = 0
 c102: fire_C,R,N,R,W,1,P  = 0
 c103: fire_C,R,N,R,W,1,D  = 0
 c104: - fire_C,R,N,R,W,1,P - fire_C,R,N,R,W,1,D - fire_C,R,N,R,W,1,C
       - fire_C,R,N,R,W,1,I - fire_C,R,N,R,W,1,A - fire_C,R,N,R,W,1,L
       - fire_C,R,N,R,W,1,N  = 0
 c105: fire_C,R,N,R,W,1,C  = 0
 c106: fire_C,R,N,R,W,1,I  = 0
 c107: fire_C,R,N,R,W,1,A  = 0
 c108: fire_C,R,N,R,W,1,L  = 0
 c109: fire_C,R,N,R,W,1,N  = 0
 c110: fire_C,R,N,R,W,2,P  = 0
 c111: fire_C,R,N,R,W,2,D  = 0
 c112: - fire_C,R,N,R,W,2,P - fire_C,R,N,R,W,2,D - fire_C,R,N,R,W,2,C
       - fire_C,R,N,R,W,2,I - fire_C,R,N,R,W,2,A - fire_C,R,N,R,W,2,L
       - fire_C,R,N,R,W,2,N  = 0
 c113: fire_C,R,N,R,W,2,C  = 0
 c114: fire_C,R,N,R,W,2,I  = 0
 c115: fire_C,R,N,R,W,2,A  = 0
 c116: fire_C,R,N,R,W,2,L  = 0
 c117: fire_C,R,N,R,W,2,N  = 0
 c118: fire_C,R,N,R,W,3,P  = 0
 c119: fire_C,R,N,R,W,3,D  = 0
 c120: - fire_C,R,N,R,W,3,P - fire_C,R,N,R,W,3,D - fire_C,R,N,R,W,3,C
       - fire_C,R,N,R,W,3,I - fire_C,R,N,R,W,3,A - fire_C,R,N,R,W,3,L
       - fire_C,R,N,R,W,3,N  = 0
 c121: fire_C,R,N,R,W,3,C  = 0
 c122: fire_C,R,N,R,W,3,I  = 0
 c123: fire_C,R,N,R,W,3,A  = 0
 c124: fire_C,R,N,R,W,3,L  = 0
 c125: fire_C,R,N,R,W,3,N  = 0
 c126: fire_C,R,N,R,C,1,P  = 0
 c127: fire_C,R,N,R,C,1,D  = 0
 c128: fire_C,R,N,R,C,1,W  = 0
 c129: - fire_C,R,N,R,C,1,P - fire_C,R,N,R,C,1,D - fire_C,R,N,R,C,1,W
       - fire_C,R,N,R,C,1,I - fire_C,R,N,R,C,1,A - fire_C,R,N,R,C,1,L
       - fire_C,R,N,R,C,1,N  = 0
 c130: fire_C,R,N,R,C,1,I  = 0
 c131: fire_C,R,N,R,C,1,A  = 0
 c132: fire_C,R,N,R,C,1,L  = 0
 c133: fire_C,R,N,R,C,1,N  = 0
 c134: fire_C,R,N,R,C,2,P  = 0
 c135: fire_C,R,N,R,C,2,D  = 0
 c136: fire_C,R,N,R,C,2,W  = 0
 c137: - fire_C,R,N,R,C,2,P - fire_C,R,N,R,C,2,D - fire_C,R,N,R,C,2,W
       - fire_C,R,N,R,C,2,I - fire_C,R,N,R,C,2,A - fire_C,R,N,R,C,2,L
       - fire_C,R,N,R,C,2,N  = 0
 c138: fire_C,R,N,R,C,2,I  = 0
 c139: fire_C,R,N,R,C,2,A  = 0
 c140: fire_C,R,N,R,C,2,L  = 0
 c141: fire_C,R,N,R,C,2,N  = 0
 c142: fire_C,R,N,R,C,3,P  = 0
 c143: fire_C,R,N,R,C,3,D  = 0
 c144: fire_C,R,N,R,C,3,W  = 0
 c145: - fire_C,R,N,R,C,3,P - fire_C,R,N,R,C,3,D - fire_C,R,N,R,C,3,W
       - fire_C,R,N,R,C,3,I - fire_C,R,N,R,C,3,A - fire_C,R,N,R,C,3,L
       - fire_C,R,N,R,C,3,N  = 0
 c146: fire_C,R,N,R,C,3,I  = 0
 c147: fire_C,R,N,R,C,3,A  = 0
 c148: fire_C,R,N,R,C,3,L  = 0
 c149: fire_C,R,N,R,C,3,N  = 0
 c150: fire_C,R,N,R,I,1,P  = 0
 c151: fire_C,R,N,R,I,1,D  = 0
 c152: fire_C,R,N,R,I,1,W  = 0
 c153: fire_C,R,N,R,I,1,C  = 0
 c154: - fire_C,R,N,R,I,1,P - fire_C,R,N,R,I,1,D - fire_C,R,N,R,I,1,W
       - fire_C,R,N,R,I,1,C - fire_C,R,N,R,I,1,A - fire_C,R,N,R,I,1,L
       - fire_C,R,N,R,I,1,N  = 0
 c155: fire_C,R,N,R,I,1,A  = 0
 c156: fire_C,R,N,R,I,1,L  = 0
 c157: fire_C,R,N,R,I,1,N  = 0
 c158: fire_C,R,N,R,I,2,P  = 0
 c159: fire_C,R,N,R,I,2,D  = 0
 c160: fire_C,R,N,R,I,2,W  = 0
 c161: fire_C,R,N,R,I,2,C  = 0
 c162: - fire_C,R,N,R,I,2,P - fire_C,R,N,R,I,2,D - fire_C,R,N,R,I,2,W
       - fire_C,R,N,R,I,2,C - fire_C,R,N,R,I,2,A - fire_C,R,N,R,I,2,L
       - fire_C,R,N,R,I,2,N  = 0
 c163: fire_C,R,N,R,I,2,A  = 0
 c164: fire_C,R,N,R,I,2,L  = 0
 c165: fire_C,R,N,R,I,2,N  = 0
 c166: fire_C,R,N,R,I,3,P  = 0
 c167: fire_C,R,N,R,I,3,D  = 0
 c168: fire_C,R,N,R,I,3,W  = 0
 c169: fire_C,R,N,R,I,3,C  = 0
 c170: - fire_C,R,N,R,I,3,P - fire_C,R,N,R,I,3,D - fire_C,R,N,R,I,3,W
       - fire_C,R,N,R,I,3,C - fire_C,R,N,R,I,3,A - fire_C,R,N,R,I,3,L
       - fire_C,R,N,R,I,3,N  = 0
 c171: fire_C,R,N,R,I,3,A  = 0
 c172: fire_C,R,N,R,I,3,L  = 0
 c173: fire_C,R,N,R,I,3,N  = 0
 c174: fire_C,R,N,R,A,1,P  = 0
 c175: fire_C,R,N,R,A,1,D  = 0
 c176: fire_C,R,N,R,A,1,W  = 0
 c177: fire_C,R,N,R,A,1,C  = 0
 c178: fire_C,R,N,R,A,1,I  = 0
 c179: - fire_C,R,N,R,A,1,P - fire_C,R,N,R,A,1,D - fire_C,R,N,R,A,1,W
       - fire_C,R,N,R,A,1,C - fire_C,R,N,R,A,1,I - fire_C,R,N,R,A,1,L
       - fire_C,R,N,R,A,1,N  = 0
 c180: fire_C,R,N,R,A,1,L  = 0
 c181: fire_C,R,N,R,A,1,N  = 0
 c182: fire_C,R,N,R,A,2,P  = 0
 c183: fire_C,R,N,R,A,2,D  = 0
 c184: fire_C,R,N,R,A,2,W  = 0
 c185: fire_C,R,N,R,A,2,C  = 0
 c186: fire_C,R,N,R,A,2,I  = 0
 c187: - fire_C,R,N,R,A,2,P - fire_C,R,N,R,A,2,D - fire_C,R,N,R,A,2,W
       - fire_C,R,N,R,A,2,C - fire_C,R,N,R,A,2,I - fire_C,R,N,R,A,2,L
       - fire_C,R,N,R,A,2,N  = 0
 c188: fire_C,R,N,R,A,2,L  = 0
 c189: fire_C,R,N,R,A,2,N  = 0
 c190: fire_C,R,N,R,A,3,P  = 0
 c191: fire_C,R,N,R,A,3,D  = 0
 c192: fire_C,R,N,R,A,3,W  = 0
 c193: fire_C,R,N,R,A,3,C  = 0
 c194: fire_C,R,N,R,A,3,I  = 0
 c195: - fire_C,R,N,R,A,3,P - fire_C,R,N,R,A,3,D - fire_C,R,N,R,A,3,W
       - fire_C,R,N,R,A,3,C - fire_C,R,N,R,A,3,I - fire_C,R,N,R,A,3,L
       - fire_C,R,N,R,A,3,N  = 0
 c196: fire_C,R,N,R,A,3,L  = 0
 c197: fire_C,R,N,R,A,3,N  = 0
 c198: fire_C,R,N,R,L,1,P  = 0
 c199: fire_C,R,N,R,L,1,D  = 0
 c200: fire_C,R,N,R,L,1,W  = 0
 c201: fire_C,R,N,R,L,1,C  = 0
 c202: fire_C,R,N,R,L,1,I  = 0
 c203: fire_C,R,N,R,L,1,A  = 0
 c204: - fire_C,R,N,R,L,1,P - fire_C,R,N,R,L,1,D - fire_C,R,N,R,L,1,W
       - fire_C,R,N,R,L,1,C - fire_C,R,N,R,L,1,I - fire_C,R,N,R,L,1,A
       - fire_C,R,N,R,L,1,N  = 0
 c205: fire_C,R,N,R,L,1,N  = 0
 c206: fire_C,R,N,R,L,2,P  = 0
 c207: fire_C,R,N,R,L,2,D  = 0
 c208: fire_C,R,N,R,L,2,W  = 0
 c209: fire_C,R,N,R,L,2,C  = 0
 c210: fire_C,R,N,R,L,2,I  = 0
 c211: fire_C,R,N,R,L,2,A  = 0
 c212: - fire_C,R,N,R,L,2,P - fire_C,R,N,R,L,2,D - fire_C,R,N,R,L,2,W
       - fire_C,R,N,R,L,2,C - fire_C,R,N,R,L,2,I - fire_C,R,N,R,L,2,A
       - fire_C,R,N,R,L,2,N  = 0
 c213: fire_C,R,N,R,L,2,N  = 0
 c214: fire_C,R,N,R,L,3,P  = 0
 c215: fire_C,R,N,R,L,3,D  = 0
 c216: fire_C,R,N,R,L,3,W  = 0
 c217: fire_C,R,N,R,L,3,C  = 0
 c218: fire_C,R,N,R,L,3,I  = 0
 c219: fire_C,R,N,R,L,3,A  = 0
 c220: - fire_C,R,N,R,L,3,P - fire_C,R,N,R,L,3,D - fire_C,R,N,R,L,3,W
       - fire_C,R,N,R,L,3,C - fire_C,R,N,R,L,3,I - fire_C,R,N,R,L,3,A
       - fire_C,R,N,R,L,3,N  = 0
 c221: fire_C,R,N,R,L,3,N  = 0
 c222: fire_C,R,N,R,N,1,P  = 0
 c223: fire_C,R,N,R,N,1,D  = 0
 c224: fire_C,R,N,R,N,1,W  = 0
 c225: fire_C,R,N,R,N,1,C  = 0
 c226: fire_C,R,N,R,N,1,I  = 0
 c227: fire_C,R,N,R,N,1,A  = 0
 c228: fire_C,R,N,R,N,1,L  = 0
 c229: - fire_C,R,N,R,N,1,P - fire_C,R,N,R,N,1,D - fire_C,R,N,R,N,1,W
       - fire_C,R,N,R,N,1,C - fire_C,R,N,R,N,1,I - fire_C,R,N,R,N,1,A
       - fire_C,R,N,R,N,1,L  = 0
 c230: fire_C,R,N,R,N,2,P  = 0
 c231: fire_C,R,N,R,N,2,D  = 0
 c232: fire_C,R,N,R,N,2,W  = 0
 c233: fire_C,R,N,R,N,2,C  = 0
 c234: fire_C,R,N,R,N,2,I  = 0
 c235: fire_C,R,N,R,N,2,A  = 0
 c236: fire_C,R,N,R,N,2,L  = 0
 c237: - fire_C,R,N,R,N,2,P - fire_C,R,N,R,N,2,D - fire_C,R,N,R,N,2,W
       - fire_C,R,N,R,N,2,C - fire_C,R,N,R,N,2,I - fire_C,R,N,R,N,2,A
       - fire_C,R,N,R,N,2,L  = 0
 c238: fire_C,R,N,R,N,3,P  = 0
 c239: fire_C,R,N,R,N,3,D  = 0
 c240: fire_C,R,N,R,N,3,W  = 0
 c241: fire_C,R,N,R,N,3,C  = 0
 c242: fire_C,R,N,R,N,3,I  = 0
 c243: fire_C,R,N,R,N,3,A  = 0
 c244: fire_C,R,N,R,N,3,L  = 0
 c245: - fire_C,R,N,R,N,3,P - fire_C,R,N,R,N,3,D - fire_C,R,N,R,N,3,W
       - fire_C,R,N,R,N,3,C - fire_C,R,N,R,N,3,I - fire_C,R,N,R,N,3,A
       - fire_C,R,N,R,N,3,L  = 0
 c246: - xNG_R_C,R,N,R,P,2,1 + fire_C,R,N,R,P,1,P + fire_C,R,N,R,D,1,P
       + fire_C,R,N,R,W,1,P + fire_C,R,N,R,C,1,P + fire_C,R,N,R,I,1,P
       + fire_C,R,N,R,A,1,P + fire_C,R,N,R,L,1,P + fire_C,R,N,R,N,1,P  = 0
 c247: - xNG_R_C,R,N,R,D,2,1 + fire_C,R,N,R,P,1,D + fire_C,R,N,R,D,1,D
       + fire_C,R,N,R,W,1,D + fire_C,R,N,R,C,1,D + fire_C,R,N,R,I,1,D
       + fire_C,R,N,R,A,1,D + fire_C,R,N,R,L,1,D + fire_C,R,N,R,N,1,D  = 0
 c248: - xNG_R_C,R,N,R,W,2,1 + fire_C,R,N,R,P,1,W + fire_C,R,N,R,D,1,W
       + fire_C,R,N,R,W,1,W + fire_C,R,N,R,C,1,W + fire_C,R,N,R,I,1,W
       + fire_C,R,N,R,A,1,W + fire_C,R,N,R,L,1,W + fire_C,R,N,R,N,1,W  = 0
 c249: - xNG_R_C,R,N,R,C,2,1 + fire_C,R,N,R,P,1,C + fire_C,R,N,R,D,1,C
       + fire_C,R,N,R,W,1,C + fire_C,R,N,R,C,1,C + fire_C,R,N,R,I,1,C
       + fire_C,R,N,R,A,1,C + fire_C,R,N,R,L,1,C + fire_C,R,N,R,N,1,C  = 0
 c250: - xNG_R_C,R,N,R,I,2,1 + fire_C,R,N,R,P,1,I + fire_C,R,N,R,D,1,I
       + fire_C,R,N,R,W,1,I + fire_C,R,N,R,C,1,I + fire_C,R,N,R,I,1,I
       + fire_C,R,N,R,A,1,I + fire_C,R,N,R,L,1,I + fire_C,R,N,R,N,1,I  = 0
 c251: - xNG_R_C,R,N,R,A,2,1 + fire_C,R,N,R,P,1,A + fire_C,R,N,R,D,1,A
       + fire_C,R,N,R,W,1,A + fire_C,R,N,R,C,1,A + fire_C,R,N,R,I,1,A
       + fire_C,R,N,R,A,1,A + fire_C,R,N,R,L,1,A + fire_C,R,N,R,N,1,A  = 0
 c252: - xNG_R_C,R,N,R,L,2,1 + fire_C,R,N,R,P,1,L + fire_C,R,N,R,D,1,L
       + fire_C,R,N,R,W,1,L + fire_C,R,N,R,C,1,L + fire_C,R,N,R,I,1,L
       + fire_C,R,N,R,A,1,L + fire_C,R,N,R,L,1,L + fire_C,R,N,R,N,1,L  = 0
 c253: fire_C,R,N,R,P,1,N + fire_C,R,N,R,D,1,N + fire_C,R,N,R,W,1,N
       + fire_C,R,N,R,C,1,N + fire_C,R,N,R,I,1,N + fire_C,R,N,R,A,1,N
       + fire_C,R,N,R,L,1,N + fire_C,R,N,R,N,1,N  = 0
 c254: - xNG_R_C,R,N,R,P,3,1 + fire_C,R,N,R,P,2,P + fire_C,R,N,R,D,2,P
       + fire_C,R,N,R,W,2,P + fire_C,R,N,R,C,2,P + fire_C,R,N,R,I,2,P
       + fire_C,R,N,R,A,2,P + fire_C,R,N,R,L,2,P + fire_C,R,N,R,N,2,P  = 0
 c255: - xNG_R_C,R,N,R,D,3,1 + fire_C,R,N,R,P,2,D + fire_C,R,N,R,D,2,D
       + fire_C,R,N,R,W,2,D + fire_C,R,N,R,C,2,D + fire_C,R,N,R,I,2,D
       + fire_C,R,N,R,A,2,D + fire_C,R,N,R,L,2,D + fire_C,R,N,R,N,2,D  = 0
 c256: - xNG_R_C,R,N,R,W,3,1 + fire_C,R,N,R,P,2,W + fire_C,R,N,R,D,2,W
       + fire_C,R,N,R,W,2,W + fire_C,R,N,R,C,2,W + fire_C,R,N,R,I,2,W
       + fire_C,R,N,R,A,2,W + fire_C,R,N,R,L,2,W + fire_C,R,N,R,N,2,W  = 0
 c257: - xNG_R_C,R,N,R,C,3,1 + fire_C,R,N,R,P,2,C + fire_C,R,N,R,D,2,C
       + fire_C,R,N,R,W,2,C + fire_C,R,N,R,C,2,C + fire_C,R,N,R,I,2,C
       + fire_C,R,N,R,A,2,C + fire_C,R,N,R,L,2,C + fire_C,R,N,R,N,2,C  = 0
 c258: - xNG_R_C,R,N,R,I,3,1 + fire_C,R,N,R,P,2,I + fire_C,R,N,R,D,2,I
       + fire_C,R,N,R,W,2,I + fire_C,R,N,R,C,2,I + fire_C,R,N,R,I,2,I
       + fire_C,R,N,R,A,2,I + fire_C,R,N,R,L,2,I + fire_C,R,N,R,N,2,I  = 0
 c259: - xNG_R_C,R,N,R,A,3,1 + fire_C,R,N,R,P,2,A + fire_C,R,N,R,D,2,A
       + fire_C,R,N,R,W,2,A + fire_C,R,N,R,C,2,A + fire_C,R,N,R,I,2,A
       + fire_C,R,N,R,A,2,A + fire_C,R,N,R,L,2,A + fire_C,R,N,R,N,2,A  = 0
 c260: - xNG_R_C,R,N,R,L,3,1 + fire_C,R,N,R,P,2,L + fire_C,R,N,R,D,2,L
       + fire_C,R,N,R,W,2,L + fire_C,R,N,R,C,2,L + fire_C,R,N,R,I,2,L
       + fire_C,R,N,R,A,2,L + fire_C,R,N,R,L,2,L + fire_C,R,N,R,N,2,L  = 0
 c261: fire_C,R,N,R,P,2,N + fire_C,R,N,R,D,2,N + fire_C,R,N,R,W,2,N
       + fire_C,R,N,R,C,2,N + fire_C,R,N,R,I,2,N + fire_C,R,N,R,A,2,N
       + fire_C,R,N,R,L,2,N + fire_C,R,N,R,N,2,N  = 0
 c262: 0.998 xNG_R_C,R,N,R,P,2,1 - xNG_R_C,R,N,R,P,3,2  = 0
 c263: 0.998 xNG_R_C,R,N,R,D,2,1 - xNG_R_C,R,N,R,D,3,2  = 0
 c264: 0.998 xNG_R_C,R,N,R,W,2,1 - xNG_R_C,R,N,R,W,3,2  = 0
 c265: 0.998 xNG_R_C,R,N,R,C,2,1 - xNG_R_C,R,N,R,C,3,2  = 0
 c266: 0.998 xNG_R_C,R,N,R,I,2,1 - xNG_R_C,R,N,R,I,3,2  = 0
 c267: 0.998 xNG_R_C,R,N,R,A,2,1 - xNG_R_C,R,N,R,A,3,2  = 0
 c268: 0.998 xNG_R_C,R,N,R,L,2,1 - xNG_R_C,R,N,R,L,3,2  = 0
 c269: - z_0 + xPB_E_C,R,N,R,P,M,0,1 + xPB_E_C,R,N,R,P,M,0,2
       + xPB_E_C,R,N,R,P,M,0,3 + xPB_E_C,R,N,R,P,M,1,1 + xPB_E_C,R,N,R,P,M,1,2
       + xPB_E_C,R,N,R,P,M,1,3 + xPB_E_C,R,N,R,P,M,2,1 + xPB_E_C,R,N,R,P,M,2,2
       + xPB_E_C,R,N,R,P,M,2,3 + xPB_E_C,R,N,R,P,M,3,1 + xPB_E_C,R,N,R,P,M,3,2
       + xPB_E_C,R,N,R,P,M,3,3 + xPB_E_C,R,N,R,P,M,4,1 + xPB_E_C,R,N,R,P,M,4,2
       + xPB_E_C,R,N,R,P,M,4,3 + xGS_E_C,R,N,R,P,M,0,1 + xGS_E_C,R,N,R,P,M,0,2
       + xGS_E_C,R,N,R,P,M,0,3 + xGS_E_C,R,N,R,P,M,1,1 + xGS_E_C,R,N,R,P,M,1,2
       + xGS_E_C,R,N,R,P,M,1,3 + xGS_E_C,R,N,R,P,M,2,1 + xGS_E_C,R,N,R,P,M,2,2
       + xGS_E_C,R,N,R,P,M,2,3 + xGS_E_C,R,N,R,P,M,3,1 + xGS_E_C,R,N,R,P,M,3,2
       + xGS_E_C,R,N,R,P,M,3,3 + xGS_E_C,R,N,R,P,M,4,1 + xGS_E_C,R,N,R,P,M,4,2
       + xGS_E_C,R,N,R,P,M,4,3  = 0
 c270: - v_0 + 0.002 xNG_E_C,R,N,R,P,M,1 + 0.002 xPB_E_C,R,N,R,P,M,0,1
       + 0.002 xPB_E_C,R,N,R,P,M,1,1 + 0.002 xPB_E_C,R,N,R,P,M,2,1
       + 0.002 xPB_E_C,R,N,R,P,M,3,1 + 0.002 xPB_E_C,R,N,R,P,M,4,1
       + 0.002 xGS_E_C,R,N,R,P,M,0,1 + 0.002 xGS_E_C,R,N,R,P,M,1,1
       + 0.002 xGS_E_C,R,N,R,P,M,2,1 + 0.002 xGS_E_C,R,N,R,P,M,3,1
       + 0.002 xGS_E_C,R,N,R,P,M,4,1  = 0
 c271: - v_1 + 0.002 xNG_E_C,R,N,R,P,M,2 + 0.002 xPB_E_C,R,N,R,P,M,0,2
       + 0.002 xPB_E_C,R,N,R,P,M,1,2 + 0.002 xPB_E_C,R,N,R,P,M,2,2
       + 0.002 xPB_E_C,R,N,R,P,M,3,2 + 0.002 xPB_E_C,R,N,R,P,M,4,2
       + 0.002 xGS_E_C,R,N,R,P,M,0,2 + 0.002 xGS_E_C,R,N,R,P,M,1,2
       + 0.002 xGS_E_C,R,N,R,P,M,2,2 + 0.002 xGS_E_C,R,N,R,P,M,3,2
       + 0.002 xGS_E_C,R,N,R,P,M,4,2 + 0.002 xNG_R_C,R,N,R,P,2,1
       + 0.002 xNG_R_C,R,N,R,D,2,1 + 0.002 xNG_R_C,R,N,R,W,2,1
       + 0.002 xNG_R_C,R,N,R,C,2,1 + 0.002 xNG_R_C,R,N,R,I,2,1
       + 0.002 xNG_R_C,R,N,R,A,2,1 + 0.002 xNG_R_C,R,N,R,L,2,1  = 0
 c272: - v_2 + 0.002 xNG_E_C,R,N,R,P,M,3 + 0.002 xPB_E_C,R,N,R,P,M,0,3
       + 0.002 xPB_E_C,R,N,R,P,M,1,3 + 0.002 xPB_E_C,R,N,R,P,M,2,3
       + 0.002 xPB_E_C,R,N,R,P,M,3,3 + 0.002 xPB_E_C,R,N,R,P,M,4,3
       + 0.002 xGS_E_C,R,N,R,P,M,0,3 + 0.002 xGS_E_C,R,N,R,P,M,1,3
       + 0.002 xGS_E_C,R,N,R,P,M,2,3 + 0.002 xGS_E_C,R,N,R,P,M,3,3
       + 0.002 xGS_E_C,R,N,R,P,M,4,3 + 0.002 xNG_R_C,R,N,R,P,3,1
       + 0.002 xNG_R_C,R,N,R,P,3,2 + 0.002 xNG_R_C,R,N,R,D,3,1
       + 0.002 xNG_R_C,R,N,R,D,3,2 + 0.002 xNG_R_C,R,N,R,W,3,1
       + 0.002 xNG_R_C,R,N,R,W,3,2 + 0.002 xNG_R_C,R,N,R,C,3,1
       + 0.002 xNG_R_C,R,N,R,C,3,2 + 0.002 xNG_R_C,R,N,R,I,3,1
       + 0.002 xNG_R_C,R,N,R,I,3,2 + 0.002 xNG_R_C,R,N,R,A,3,1
       + 0.002 xNG_R_C,R,N,R,A,3,2 + 0.002 xNG_R_C,R,N,R,L,3,1
       + 0.002 xNG_R_C,R,N,R,L,3,2  = 0
Bounds
      z_0 = 0
      x_C,R,N,R,P,M,3 = 0
      x_C,R,N,R,P,M,4 = 4.5
      x_C,R,N,R,P,M,5 = 0
End
