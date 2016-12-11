\ENCODING=ISO-8859-1
\Problem name: ilog.cplex

Minimize
 obj:
Subject To
 c1:    x_B,N,N,L,C,M,0 + x_B,N,N,L,C,M,1 + x_B,N,N,L,C,M,2 + x_B,N,N,L,C,M,3
        + x_B,N,N,L,C,M,4  = 396
 c2:    x_B,N,N,L,C,L,0 + x_B,N,N,L,C,L,1 + x_B,N,N,L,C,L,2 + x_B,N,N,L,C,L,3
        + x_B,N,N,L,C,L,4  = 1396
 c3:    x_B,N,N,L,A,S,0 + x_B,N,N,L,A,S,1 + x_B,N,N,L,A,S,2 + x_B,N,N,L,A,S,3
        + x_B,N,N,L,A,S,4  = 86
 c4:    x_B,N,N,L,A,M,0 + x_B,N,N,L,A,M,1 + x_B,N,N,L,A,M,2 + x_B,N,N,L,A,M,3
        + x_B,N,N,L,A,M,4  = 157
 c5:    x_B,N,N,L,A,L,0 + x_B,N,N,L,A,L,1 + x_B,N,N,L,A,L,2 + x_B,N,N,L,A,L,3
        + x_B,N,N,L,A,L,4  = 378
 c6:    x_B,N,N,H,C,L,0 + x_B,N,N,H,C,L,1 + x_B,N,N,H,C,L,2 + x_B,N,N,H,C,L,3
        + x_B,N,N,H,C,L,4  = 71
 c7:    x_B,N,N,C,P,P,0 + x_B,N,N,C,P,P,1 + x_B,N,N,C,P,P,2 + x_B,N,N,C,P,P,3
        + x_B,N,N,C,P,P,4  = 142
 c8:    x_B,N,N,C,P,M,0 + x_B,N,N,C,P,M,1 + x_B,N,N,C,P,M,2 + x_B,N,N,C,P,M,3
        + x_B,N,N,C,P,M,4  = 909
 c9:    x_B,N,N,C,P,L,0 + x_B,N,N,C,P,L,1 + x_B,N,N,C,P,L,2 + x_B,N,N,C,P,L,3
        + x_B,N,N,C,P,L,4  = 1268
 c10:   x_B,N,N,C,D,S,0 + x_B,N,N,C,D,S,1 + x_B,N,N,C,D,S,2 + x_B,N,N,C,D,S,3
        + x_B,N,N,C,D,S,4  = 84
 c11:   x_B,N,N,C,D,P,0 + x_B,N,N,C,D,P,1 + x_B,N,N,C,D,P,2 + x_B,N,N,C,D,P,3
        + x_B,N,N,C,D,P,4  = 77
 c12:   x_B,N,N,C,D,M,0 + x_B,N,N,C,D,M,1 + x_B,N,N,C,D,M,2 + x_B,N,N,C,D,M,3
        + x_B,N,N,C,D,M,4  = 540
 c13:   x_B,N,N,C,D,L,0 + x_B,N,N,C,D,L,1 + x_B,N,N,C,D,L,2 + x_B,N,N,C,D,L,3
        + x_B,N,N,C,D,L,4  = 1725
 c14:   x_B,N,N,C,W,S,0 + x_B,N,N,C,W,S,1 + x_B,N,N,C,W,S,2 + x_B,N,N,C,W,S,3
        + x_B,N,N,C,W,S,4  = 107
 c15:   x_B,N,N,C,W,P,0 + x_B,N,N,C,W,P,1 + x_B,N,N,C,W,P,2 + x_B,N,N,C,W,P,3
        + x_B,N,N,C,W,P,4  = 310
 c16:   x_B,N,N,C,W,M,0 + x_B,N,N,C,W,M,1 + x_B,N,N,C,W,M,2 + x_B,N,N,C,W,M,3
        + x_B,N,N,C,W,M,4  = 1664
 c17:   x_B,N,N,C,W,L,0 + x_B,N,N,C,W,L,1 + x_B,N,N,C,W,L,2 + x_B,N,N,C,W,L,3
        + x_B,N,N,C,W,L,4  = 4299
 c18:   x_B,N,N,C,C,S,0 + x_B,N,N,C,C,S,1 + x_B,N,N,C,C,S,2 + x_B,N,N,C,C,S,3
        + x_B,N,N,C,C,S,4  = 97
 c19:   x_B,N,N,C,C,P,0 + x_B,N,N,C,C,P,1 + x_B,N,N,C,C,P,2 + x_B,N,N,C,C,P,3
        + x_B,N,N,C,C,P,4  = 651
 c20:   x_B,N,N,C,C,M,0 + x_B,N,N,C,C,M,1 + x_B,N,N,C,C,M,2 + x_B,N,N,C,C,M,3
        + x_B,N,N,C,C,M,4  = 1353
 c21:   x_B,N,N,C,C,L,0 + x_B,N,N,C,C,L,1 + x_B,N,N,C,C,L,2 + x_B,N,N,C,C,L,3
        + x_B,N,N,C,C,L,4  = 4121
 c22:   x_B,N,N,C,I,M,0 + x_B,N,N,C,I,M,1 + x_B,N,N,C,I,M,2 + x_B,N,N,C,I,M,3
        + x_B,N,N,C,I,M,4  = 61
 c23:   x_B,N,N,C,I,L,0 + x_B,N,N,C,I,L,1 + x_B,N,N,C,I,L,2 + x_B,N,N,C,I,L,3
        + x_B,N,N,C,I,L,4  = 247
 c24:   x_B,N,N,C,A,P,0 + x_B,N,N,C,A,P,1 + x_B,N,N,C,A,P,2 + x_B,N,N,C,A,P,3
        + x_B,N,N,C,A,P,4  = 64
 c25:   x_B,N,N,C,A,M,0 + x_B,N,N,C,A,M,1 + x_B,N,N,C,A,M,2 + x_B,N,N,C,A,M,3
        + x_B,N,N,C,A,M,4  = 63
 c26:   x_B,N,N,C,A,L,0 + x_B,N,N,C,A,L,1 + x_B,N,N,C,A,L,2 + x_B,N,N,C,A,L,3
        + x_B,N,N,C,A,L,4  = 335
 c27:   x_B,N,N,C,L,M,0 + x_B,N,N,C,L,M,1 + x_B,N,N,C,L,M,2 + x_B,N,N,C,L,M,3
        + x_B,N,N,C,L,M,4  = 92
 c28:   x_B,N,N,C,L,L,0 + x_B,N,N,C,L,L,1 + x_B,N,N,C,L,L,2 + x_B,N,N,C,L,L,3
        + x_B,N,N,C,L,L,4  = 181
 c29:   x_B,N,N,C,N,N,0 + x_B,N,N,C,N,N,1 + x_B,N,N,C,N,N,2 + x_B,N,N,C,N,N,3
        + x_B,N,N,C,N,N,4  = 1279
 c30:   x_B,N,N,L,C,M,4  = 19.8
 c31:   x_B,N,N,L,C,L,4  = 69.8
 c32:   x_B,N,N,L,A,S,4  = 4.3
 c33:   x_B,N,N,L,A,M,4  = 7.85
 c34:   x_B,N,N,L,A,L,4  = 18.9
 c35:   x_B,N,N,H,C,L,4  = 3.55
 c36:   x_B,N,N,C,P,P,4  = 7.1
 c37:   x_B,N,N,C,P,M,4  = 45.45
 c38:   x_B,N,N,C,P,L,4  = 63.4
 c39:   x_B,N,N,C,D,S,4  = 4.2
 c40:   x_B,N,N,C,D,P,4  = 3.85
 c41:   x_B,N,N,C,D,M,4  = 27
 c42:   x_B,N,N,C,D,L,4  = 86.25
 c43:   x_B,N,N,C,W,S,4  = 5.35
 c44:   x_B,N,N,C,W,P,4  = 15.5
 c45:   x_B,N,N,C,W,M,4  = 83.2
 c46:   x_B,N,N,C,W,L,4  = 214.95
 c47:   x_B,N,N,C,C,S,4  = 4.85
 c48:   x_B,N,N,C,C,P,4  = 32.55
 c49:   x_B,N,N,C,C,M,4  = 67.65
 c50:   x_B,N,N,C,C,L,4  = 206.05
 c51:   x_B,N,N,C,I,M,4  = 3.05
 c52:   x_B,N,N,C,I,L,4  = 12.35
 c53:   x_B,N,N,C,A,P,4  = 3.2
 c54:   x_B,N,N,C,A,M,4  = 3.15
 c55:   x_B,N,N,C,A,L,4  = 16.75
 c56:   x_B,N,N,C,L,M,4  = 4.6
 c57:   x_B,N,N,C,L,L,4  = 9.05
 c58:   x_B,N,N,C,N,N,4  = 0
 c59:   x_B,N,N,L,C,M,4 - xMS_B,N,N,L,C,M,0,1 - xMS_B,N,N,L,C,M,1,1
        - xMS_B,N,N,L,C,M,2,1 - xMS_B,N,N,L,C,M,3,1 - xMS_B,N,N,L,C,M,4,1  = 0
 c60:   x_B,N,N,L,C,M,4 - xMS_B,N,N,L,C,M,0,2 - xMS_B,N,N,L,C,M,1,2
        - xMS_B,N,N,L,C,M,2,2 - xMS_B,N,N,L,C,M,3,2 - xMS_B,N,N,L,C,M,4,2  = 0
 c61:   x_B,N,N,L,C,M,4 - xMS_B,N,N,L,C,M,0,3 - xMS_B,N,N,L,C,M,1,3
        - xMS_B,N,N,L,C,M,2,3 - xMS_B,N,N,L,C,M,3,3 - xMS_B,N,N,L,C,M,4,3  = 0
 c62:   x_B,N,N,L,C,M,4 - xMS_B,N,N,L,C,M,0,4 - xMS_B,N,N,L,C,M,1,4
        - xMS_B,N,N,L,C,M,2,4 - xMS_B,N,N,L,C,M,3,4 - xMS_B,N,N,L,C,M,4,4  = 0
 c63:   x_B,N,N,L,C,M,4 - xMS_B,N,N,L,C,M,0,5 - xMS_B,N,N,L,C,M,1,5
        - xMS_B,N,N,L,C,M,2,5 - xMS_B,N,N,L,C,M,3,5 - xMS_B,N,N,L,C,M,4,5  = 0
 c64:   x_B,N,N,L,C,L,4 - xMS_B,N,N,L,C,L,0,1 - xMS_B,N,N,L,C,L,1,1
        - xMS_B,N,N,L,C,L,2,1 - xMS_B,N,N,L,C,L,3,1 - xMS_B,N,N,L,C,L,4,1  = 0
 c65:   x_B,N,N,L,C,L,4 - xMS_B,N,N,L,C,L,0,2 - xMS_B,N,N,L,C,L,1,2
        - xMS_B,N,N,L,C,L,2,2 - xMS_B,N,N,L,C,L,3,2 - xMS_B,N,N,L,C,L,4,2  = 0
 c66:   x_B,N,N,L,C,L,4 - xMS_B,N,N,L,C,L,0,3 - xMS_B,N,N,L,C,L,1,3
        - xMS_B,N,N,L,C,L,2,3 - xMS_B,N,N,L,C,L,3,3 - xMS_B,N,N,L,C,L,4,3  = 0
 c67:   x_B,N,N,L,C,L,4 - xMS_B,N,N,L,C,L,0,4 - xMS_B,N,N,L,C,L,1,4
        - xMS_B,N,N,L,C,L,2,4 - xMS_B,N,N,L,C,L,3,4 - xMS_B,N,N,L,C,L,4,4  = 0
 c68:   x_B,N,N,L,C,L,4 - xMS_B,N,N,L,C,L,0,5 - xMS_B,N,N,L,C,L,1,5
        - xMS_B,N,N,L,C,L,2,5 - xMS_B,N,N,L,C,L,3,5 - xMS_B,N,N,L,C,L,4,5  = 0
 c69:   x_B,N,N,L,A,S,4 - xMS_B,N,N,L,A,S,0,1 - xMS_B,N,N,L,A,S,1,1
        - xMS_B,N,N,L,A,S,2,1 - xMS_B,N,N,L,A,S,3,1 - xMS_B,N,N,L,A,S,4,1  = 0
 c70:   x_B,N,N,L,A,S,4 - xMS_B,N,N,L,A,S,0,2 - xMS_B,N,N,L,A,S,1,2
        - xMS_B,N,N,L,A,S,2,2 - xMS_B,N,N,L,A,S,3,2 - xMS_B,N,N,L,A,S,4,2  = 0
 c71:   x_B,N,N,L,A,S,4 - xMS_B,N,N,L,A,S,0,3 - xMS_B,N,N,L,A,S,1,3
        - xMS_B,N,N,L,A,S,2,3 - xMS_B,N,N,L,A,S,3,3 - xMS_B,N,N,L,A,S,4,3  = 0
 c72:   x_B,N,N,L,A,S,4 - xMS_B,N,N,L,A,S,0,4 - xMS_B,N,N,L,A,S,1,4
        - xMS_B,N,N,L,A,S,2,4 - xMS_B,N,N,L,A,S,3,4 - xMS_B,N,N,L,A,S,4,4  = 0
 c73:   x_B,N,N,L,A,S,4 - xMS_B,N,N,L,A,S,0,5 - xMS_B,N,N,L,A,S,1,5
        - xMS_B,N,N,L,A,S,2,5 - xMS_B,N,N,L,A,S,3,5 - xMS_B,N,N,L,A,S,4,5  = 0
 c74:   x_B,N,N,L,A,M,4 - xMS_B,N,N,L,A,M,0,1 - xMS_B,N,N,L,A,M,1,1
        - xMS_B,N,N,L,A,M,2,1 - xMS_B,N,N,L,A,M,3,1 - xMS_B,N,N,L,A,M,4,1  = 0
 c75:   x_B,N,N,L,A,M,4 - xMS_B,N,N,L,A,M,0,2 - xMS_B,N,N,L,A,M,1,2
        - xMS_B,N,N,L,A,M,2,2 - xMS_B,N,N,L,A,M,3,2 - xMS_B,N,N,L,A,M,4,2  = 0
 c76:   x_B,N,N,L,A,M,4 - xMS_B,N,N,L,A,M,0,3 - xMS_B,N,N,L,A,M,1,3
        - xMS_B,N,N,L,A,M,2,3 - xMS_B,N,N,L,A,M,3,3 - xMS_B,N,N,L,A,M,4,3  = 0
 c77:   x_B,N,N,L,A,M,4 - xMS_B,N,N,L,A,M,0,4 - xMS_B,N,N,L,A,M,1,4
        - xMS_B,N,N,L,A,M,2,4 - xMS_B,N,N,L,A,M,3,4 - xMS_B,N,N,L,A,M,4,4  = 0
 c78:   x_B,N,N,L,A,M,4 - xMS_B,N,N,L,A,M,0,5 - xMS_B,N,N,L,A,M,1,5
        - xMS_B,N,N,L,A,M,2,5 - xMS_B,N,N,L,A,M,3,5 - xMS_B,N,N,L,A,M,4,5  = 0
 c79:   x_B,N,N,L,A,L,4 - xMS_B,N,N,L,A,L,0,1 - xMS_B,N,N,L,A,L,1,1
        - xMS_B,N,N,L,A,L,2,1 - xMS_B,N,N,L,A,L,3,1 - xMS_B,N,N,L,A,L,4,1  = 0
 c80:   x_B,N,N,L,A,L,4 - xMS_B,N,N,L,A,L,0,2 - xMS_B,N,N,L,A,L,1,2
        - xMS_B,N,N,L,A,L,2,2 - xMS_B,N,N,L,A,L,3,2 - xMS_B,N,N,L,A,L,4,2  = 0
 c81:   x_B,N,N,L,A,L,4 - xMS_B,N,N,L,A,L,0,3 - xMS_B,N,N,L,A,L,1,3
        - xMS_B,N,N,L,A,L,2,3 - xMS_B,N,N,L,A,L,3,3 - xMS_B,N,N,L,A,L,4,3  = 0
 c82:   x_B,N,N,L,A,L,4 - xMS_B,N,N,L,A,L,0,4 - xMS_B,N,N,L,A,L,1,4
        - xMS_B,N,N,L,A,L,2,4 - xMS_B,N,N,L,A,L,3,4 - xMS_B,N,N,L,A,L,4,4  = 0
 c83:   x_B,N,N,L,A,L,4 - xMS_B,N,N,L,A,L,0,5 - xMS_B,N,N,L,A,L,1,5
        - xMS_B,N,N,L,A,L,2,5 - xMS_B,N,N,L,A,L,3,5 - xMS_B,N,N,L,A,L,4,5  = 0
 c84:   x_B,N,N,H,C,L,4 - xMS_B,N,N,H,C,L,0,1 - xMS_B,N,N,H,C,L,1,1
        - xMS_B,N,N,H,C,L,2,1 - xMS_B,N,N,H,C,L,3,1 - xMS_B,N,N,H,C,L,4,1  = 0
 c85:   x_B,N,N,H,C,L,4 - xMS_B,N,N,H,C,L,0,2 - xMS_B,N,N,H,C,L,1,2
        - xMS_B,N,N,H,C,L,2,2 - xMS_B,N,N,H,C,L,3,2 - xMS_B,N,N,H,C,L,4,2  = 0
 c86:   x_B,N,N,H,C,L,4 - xMS_B,N,N,H,C,L,0,3 - xMS_B,N,N,H,C,L,1,3
        - xMS_B,N,N,H,C,L,2,3 - xMS_B,N,N,H,C,L,3,3 - xMS_B,N,N,H,C,L,4,3  = 0
 c87:   x_B,N,N,H,C,L,4 - xMS_B,N,N,H,C,L,0,4 - xMS_B,N,N,H,C,L,1,4
        - xMS_B,N,N,H,C,L,2,4 - xMS_B,N,N,H,C,L,3,4 - xMS_B,N,N,H,C,L,4,4  = 0
 c88:   x_B,N,N,H,C,L,4 - xMS_B,N,N,H,C,L,0,5 - xMS_B,N,N,H,C,L,1,5
        - xMS_B,N,N,H,C,L,2,5 - xMS_B,N,N,H,C,L,3,5 - xMS_B,N,N,H,C,L,4,5  = 0
 c89:   x_B,N,N,C,P,P,4 - xMS_B,N,N,C,P,P,0,1 - xMS_B,N,N,C,P,P,1,1
        - xMS_B,N,N,C,P,P,2,1 - xMS_B,N,N,C,P,P,3,1 - xMS_B,N,N,C,P,P,4,1  = 0
 c90:   x_B,N,N,C,P,P,4 - xMS_B,N,N,C,P,P,0,2 - xMS_B,N,N,C,P,P,1,2
        - xMS_B,N,N,C,P,P,2,2 - xMS_B,N,N,C,P,P,3,2 - xMS_B,N,N,C,P,P,4,2  = 0
 c91:   x_B,N,N,C,P,P,4 - xMS_B,N,N,C,P,P,0,3 - xMS_B,N,N,C,P,P,1,3
        - xMS_B,N,N,C,P,P,2,3 - xMS_B,N,N,C,P,P,3,3 - xMS_B,N,N,C,P,P,4,3  = 0
 c92:   x_B,N,N,C,P,P,4 - xMS_B,N,N,C,P,P,0,4 - xMS_B,N,N,C,P,P,1,4
        - xMS_B,N,N,C,P,P,2,4 - xMS_B,N,N,C,P,P,3,4 - xMS_B,N,N,C,P,P,4,4  = 0
 c93:   x_B,N,N,C,P,P,4 - xMS_B,N,N,C,P,P,0,5 - xMS_B,N,N,C,P,P,1,5
        - xMS_B,N,N,C,P,P,2,5 - xMS_B,N,N,C,P,P,3,5 - xMS_B,N,N,C,P,P,4,5  = 0
 c94:   x_B,N,N,C,P,M,4 - xMS_B,N,N,C,P,M,0,1 - xMS_B,N,N,C,P,M,1,1
        - xMS_B,N,N,C,P,M,2,1 - xMS_B,N,N,C,P,M,3,1 - xMS_B,N,N,C,P,M,4,1  = 0
 c95:   x_B,N,N,C,P,M,4 - xMS_B,N,N,C,P,M,0,2 - xMS_B,N,N,C,P,M,1,2
        - xMS_B,N,N,C,P,M,2,2 - xMS_B,N,N,C,P,M,3,2 - xMS_B,N,N,C,P,M,4,2  = 0
 c96:   x_B,N,N,C,P,M,4 - xMS_B,N,N,C,P,M,0,3 - xMS_B,N,N,C,P,M,1,3
        - xMS_B,N,N,C,P,M,2,3 - xMS_B,N,N,C,P,M,3,3 - xMS_B,N,N,C,P,M,4,3  = 0
 c97:   x_B,N,N,C,P,M,4 - xMS_B,N,N,C,P,M,0,4 - xMS_B,N,N,C,P,M,1,4
        - xMS_B,N,N,C,P,M,2,4 - xMS_B,N,N,C,P,M,3,4 - xMS_B,N,N,C,P,M,4,4  = 0
 c98:   x_B,N,N,C,P,M,4 - xMS_B,N,N,C,P,M,0,5 - xMS_B,N,N,C,P,M,1,5
        - xMS_B,N,N,C,P,M,2,5 - xMS_B,N,N,C,P,M,3,5 - xMS_B,N,N,C,P,M,4,5  = 0
 c99:   x_B,N,N,C,P,L,4 - xMS_B,N,N,C,P,L,0,1 - xMS_B,N,N,C,P,L,1,1
        - xMS_B,N,N,C,P,L,2,1 - xMS_B,N,N,C,P,L,3,1 - xMS_B,N,N,C,P,L,4,1  = 0
 c100:  x_B,N,N,C,P,L,4 - xMS_B,N,N,C,P,L,0,2 - xMS_B,N,N,C,P,L,1,2
        - xMS_B,N,N,C,P,L,2,2 - xMS_B,N,N,C,P,L,3,2 - xMS_B,N,N,C,P,L,4,2  = 0
 c101:  x_B,N,N,C,P,L,4 - xMS_B,N,N,C,P,L,0,3 - xMS_B,N,N,C,P,L,1,3
        - xMS_B,N,N,C,P,L,2,3 - xMS_B,N,N,C,P,L,3,3 - xMS_B,N,N,C,P,L,4,3  = 0
 c102:  x_B,N,N,C,P,L,4 - xMS_B,N,N,C,P,L,0,4 - xMS_B,N,N,C,P,L,1,4
        - xMS_B,N,N,C,P,L,2,4 - xMS_B,N,N,C,P,L,3,4 - xMS_B,N,N,C,P,L,4,4  = 0
 c103:  x_B,N,N,C,P,L,4 - xMS_B,N,N,C,P,L,0,5 - xMS_B,N,N,C,P,L,1,5
        - xMS_B,N,N,C,P,L,2,5 - xMS_B,N,N,C,P,L,3,5 - xMS_B,N,N,C,P,L,4,5  = 0
 c104:  x_B,N,N,C,D,S,4 - xMS_B,N,N,C,D,S,0,1 - xMS_B,N,N,C,D,S,1,1
        - xMS_B,N,N,C,D,S,2,1 - xMS_B,N,N,C,D,S,3,1 - xMS_B,N,N,C,D,S,4,1  = 0
 c105:  x_B,N,N,C,D,S,4 - xMS_B,N,N,C,D,S,0,2 - xMS_B,N,N,C,D,S,1,2
        - xMS_B,N,N,C,D,S,2,2 - xMS_B,N,N,C,D,S,3,2 - xMS_B,N,N,C,D,S,4,2  = 0
 c106:  x_B,N,N,C,D,S,4 - xMS_B,N,N,C,D,S,0,3 - xMS_B,N,N,C,D,S,1,3
        - xMS_B,N,N,C,D,S,2,3 - xMS_B,N,N,C,D,S,3,3 - xMS_B,N,N,C,D,S,4,3  = 0
 c107:  x_B,N,N,C,D,S,4 - xMS_B,N,N,C,D,S,0,4 - xMS_B,N,N,C,D,S,1,4
        - xMS_B,N,N,C,D,S,2,4 - xMS_B,N,N,C,D,S,3,4 - xMS_B,N,N,C,D,S,4,4  = 0
 c108:  x_B,N,N,C,D,S,4 - xMS_B,N,N,C,D,S,0,5 - xMS_B,N,N,C,D,S,1,5
        - xMS_B,N,N,C,D,S,2,5 - xMS_B,N,N,C,D,S,3,5 - xMS_B,N,N,C,D,S,4,5  = 0
 c109:  x_B,N,N,C,D,P,4 - xMS_B,N,N,C,D,P,0,1 - xMS_B,N,N,C,D,P,1,1
        - xMS_B,N,N,C,D,P,2,1 - xMS_B,N,N,C,D,P,3,1 - xMS_B,N,N,C,D,P,4,1  = 0
 c110:  x_B,N,N,C,D,P,4 - xMS_B,N,N,C,D,P,0,2 - xMS_B,N,N,C,D,P,1,2
        - xMS_B,N,N,C,D,P,2,2 - xMS_B,N,N,C,D,P,3,2 - xMS_B,N,N,C,D,P,4,2  = 0
 c111:  x_B,N,N,C,D,P,4 - xMS_B,N,N,C,D,P,0,3 - xMS_B,N,N,C,D,P,1,3
        - xMS_B,N,N,C,D,P,2,3 - xMS_B,N,N,C,D,P,3,3 - xMS_B,N,N,C,D,P,4,3  = 0
 c112:  x_B,N,N,C,D,P,4 - xMS_B,N,N,C,D,P,0,4 - xMS_B,N,N,C,D,P,1,4
        - xMS_B,N,N,C,D,P,2,4 - xMS_B,N,N,C,D,P,3,4 - xMS_B,N,N,C,D,P,4,4  = 0
 c113:  x_B,N,N,C,D,P,4 - xMS_B,N,N,C,D,P,0,5 - xMS_B,N,N,C,D,P,1,5
        - xMS_B,N,N,C,D,P,2,5 - xMS_B,N,N,C,D,P,3,5 - xMS_B,N,N,C,D,P,4,5  = 0
 c114:  x_B,N,N,C,D,M,4 - xMS_B,N,N,C,D,M,0,1 - xMS_B,N,N,C,D,M,1,1
        - xMS_B,N,N,C,D,M,2,1 - xMS_B,N,N,C,D,M,3,1 - xMS_B,N,N,C,D,M,4,1  = 0
 c115:  x_B,N,N,C,D,M,4 - xMS_B,N,N,C,D,M,0,2 - xMS_B,N,N,C,D,M,1,2
        - xMS_B,N,N,C,D,M,2,2 - xMS_B,N,N,C,D,M,3,2 - xMS_B,N,N,C,D,M,4,2  = 0
 c116:  x_B,N,N,C,D,M,4 - xMS_B,N,N,C,D,M,0,3 - xMS_B,N,N,C,D,M,1,3
        - xMS_B,N,N,C,D,M,2,3 - xMS_B,N,N,C,D,M,3,3 - xMS_B,N,N,C,D,M,4,3  = 0
 c117:  x_B,N,N,C,D,M,4 - xMS_B,N,N,C,D,M,0,4 - xMS_B,N,N,C,D,M,1,4
        - xMS_B,N,N,C,D,M,2,4 - xMS_B,N,N,C,D,M,3,4 - xMS_B,N,N,C,D,M,4,4  = 0
 c118:  x_B,N,N,C,D,M,4 - xMS_B,N,N,C,D,M,0,5 - xMS_B,N,N,C,D,M,1,5
        - xMS_B,N,N,C,D,M,2,5 - xMS_B,N,N,C,D,M,3,5 - xMS_B,N,N,C,D,M,4,5  = 0
 c119:  x_B,N,N,C,D,L,4 - xMS_B,N,N,C,D,L,0,1 - xMS_B,N,N,C,D,L,1,1
        - xMS_B,N,N,C,D,L,2,1 - xMS_B,N,N,C,D,L,3,1 - xMS_B,N,N,C,D,L,4,1  = 0
 c120:  x_B,N,N,C,D,L,4 - xMS_B,N,N,C,D,L,0,2 - xMS_B,N,N,C,D,L,1,2
        - xMS_B,N,N,C,D,L,2,2 - xMS_B,N,N,C,D,L,3,2 - xMS_B,N,N,C,D,L,4,2  = 0
 c121:  x_B,N,N,C,D,L,4 - xMS_B,N,N,C,D,L,0,3 - xMS_B,N,N,C,D,L,1,3
        - xMS_B,N,N,C,D,L,2,3 - xMS_B,N,N,C,D,L,3,3 - xMS_B,N,N,C,D,L,4,3  = 0
 c122:  x_B,N,N,C,D,L,4 - xMS_B,N,N,C,D,L,0,4 - xMS_B,N,N,C,D,L,1,4
        - xMS_B,N,N,C,D,L,2,4 - xMS_B,N,N,C,D,L,3,4 - xMS_B,N,N,C,D,L,4,4  = 0
 c123:  x_B,N,N,C,D,L,4 - xMS_B,N,N,C,D,L,0,5 - xMS_B,N,N,C,D,L,1,5
        - xMS_B,N,N,C,D,L,2,5 - xMS_B,N,N,C,D,L,3,5 - xMS_B,N,N,C,D,L,4,5  = 0
 c124:  x_B,N,N,C,W,S,4 - xMS_B,N,N,C,W,S,0,1 - xMS_B,N,N,C,W,S,1,1
        - xMS_B,N,N,C,W,S,2,1 - xMS_B,N,N,C,W,S,3,1 - xMS_B,N,N,C,W,S,4,1  = 0
 c125:  x_B,N,N,C,W,S,4 - xMS_B,N,N,C,W,S,0,2 - xMS_B,N,N,C,W,S,1,2
        - xMS_B,N,N,C,W,S,2,2 - xMS_B,N,N,C,W,S,3,2 - xMS_B,N,N,C,W,S,4,2  = 0
 c126:  x_B,N,N,C,W,S,4 - xMS_B,N,N,C,W,S,0,3 - xMS_B,N,N,C,W,S,1,3
        - xMS_B,N,N,C,W,S,2,3 - xMS_B,N,N,C,W,S,3,3 - xMS_B,N,N,C,W,S,4,3  = 0
 c127:  x_B,N,N,C,W,S,4 - xMS_B,N,N,C,W,S,0,4 - xMS_B,N,N,C,W,S,1,4
        - xMS_B,N,N,C,W,S,2,4 - xMS_B,N,N,C,W,S,3,4 - xMS_B,N,N,C,W,S,4,4  = 0
 c128:  x_B,N,N,C,W,S,4 - xMS_B,N,N,C,W,S,0,5 - xMS_B,N,N,C,W,S,1,5
        - xMS_B,N,N,C,W,S,2,5 - xMS_B,N,N,C,W,S,3,5 - xMS_B,N,N,C,W,S,4,5  = 0
 c129:  x_B,N,N,C,W,P,4 - xMS_B,N,N,C,W,P,0,1 - xMS_B,N,N,C,W,P,1,1
        - xMS_B,N,N,C,W,P,2,1 - xMS_B,N,N,C,W,P,3,1 - xMS_B,N,N,C,W,P,4,1  = 0
 c130:  x_B,N,N,C,W,P,4 - xMS_B,N,N,C,W,P,0,2 - xMS_B,N,N,C,W,P,1,2
        - xMS_B,N,N,C,W,P,2,2 - xMS_B,N,N,C,W,P,3,2 - xMS_B,N,N,C,W,P,4,2  = 0
 c131:  x_B,N,N,C,W,P,4 - xMS_B,N,N,C,W,P,0,3 - xMS_B,N,N,C,W,P,1,3
        - xMS_B,N,N,C,W,P,2,3 - xMS_B,N,N,C,W,P,3,3 - xMS_B,N,N,C,W,P,4,3  = 0
 c132:  x_B,N,N,C,W,P,4 - xMS_B,N,N,C,W,P,0,4 - xMS_B,N,N,C,W,P,1,4
        - xMS_B,N,N,C,W,P,2,4 - xMS_B,N,N,C,W,P,3,4 - xMS_B,N,N,C,W,P,4,4  = 0
 c133:  x_B,N,N,C,W,P,4 - xMS_B,N,N,C,W,P,0,5 - xMS_B,N,N,C,W,P,1,5
        - xMS_B,N,N,C,W,P,2,5 - xMS_B,N,N,C,W,P,3,5 - xMS_B,N,N,C,W,P,4,5  = 0
 c134:  x_B,N,N,C,W,M,4 - xMS_B,N,N,C,W,M,0,1 - xMS_B,N,N,C,W,M,1,1
        - xMS_B,N,N,C,W,M,2,1 - xMS_B,N,N,C,W,M,3,1 - xMS_B,N,N,C,W,M,4,1  = 0
 c135:  x_B,N,N,C,W,M,4 - xMS_B,N,N,C,W,M,0,2 - xMS_B,N,N,C,W,M,1,2
        - xMS_B,N,N,C,W,M,2,2 - xMS_B,N,N,C,W,M,3,2 - xMS_B,N,N,C,W,M,4,2  = 0
 c136:  x_B,N,N,C,W,M,4 - xMS_B,N,N,C,W,M,0,3 - xMS_B,N,N,C,W,M,1,3
        - xMS_B,N,N,C,W,M,2,3 - xMS_B,N,N,C,W,M,3,3 - xMS_B,N,N,C,W,M,4,3  = 0
 c137:  x_B,N,N,C,W,M,4 - xMS_B,N,N,C,W,M,0,4 - xMS_B,N,N,C,W,M,1,4
        - xMS_B,N,N,C,W,M,2,4 - xMS_B,N,N,C,W,M,3,4 - xMS_B,N,N,C,W,M,4,4  = 0
 c138:  x_B,N,N,C,W,M,4 - xMS_B,N,N,C,W,M,0,5 - xMS_B,N,N,C,W,M,1,5
        - xMS_B,N,N,C,W,M,2,5 - xMS_B,N,N,C,W,M,3,5 - xMS_B,N,N,C,W,M,4,5  = 0
 c139:  x_B,N,N,C,W,L,4 - xMS_B,N,N,C,W,L,0,1 - xMS_B,N,N,C,W,L,1,1
        - xMS_B,N,N,C,W,L,2,1 - xMS_B,N,N,C,W,L,3,1 - xMS_B,N,N,C,W,L,4,1  = 0
 c140:  x_B,N,N,C,W,L,4 - xMS_B,N,N,C,W,L,0,2 - xMS_B,N,N,C,W,L,1,2
        - xMS_B,N,N,C,W,L,2,2 - xMS_B,N,N,C,W,L,3,2 - xMS_B,N,N,C,W,L,4,2  = 0
 c141:  x_B,N,N,C,W,L,4 - xMS_B,N,N,C,W,L,0,3 - xMS_B,N,N,C,W,L,1,3
        - xMS_B,N,N,C,W,L,2,3 - xMS_B,N,N,C,W,L,3,3 - xMS_B,N,N,C,W,L,4,3  = 0
 c142:  x_B,N,N,C,W,L,4 - xMS_B,N,N,C,W,L,0,4 - xMS_B,N,N,C,W,L,1,4
        - xMS_B,N,N,C,W,L,2,4 - xMS_B,N,N,C,W,L,3,4 - xMS_B,N,N,C,W,L,4,4  = 0
 c143:  x_B,N,N,C,W,L,4 - xMS_B,N,N,C,W,L,0,5 - xMS_B,N,N,C,W,L,1,5
        - xMS_B,N,N,C,W,L,2,5 - xMS_B,N,N,C,W,L,3,5 - xMS_B,N,N,C,W,L,4,5  = 0
 c144:  x_B,N,N,C,C,S,4 - xMS_B,N,N,C,C,S,0,1 - xMS_B,N,N,C,C,S,1,1
        - xMS_B,N,N,C,C,S,2,1 - xMS_B,N,N,C,C,S,3,1 - xMS_B,N,N,C,C,S,4,1  = 0
 c145:  x_B,N,N,C,C,S,4 - xMS_B,N,N,C,C,S,0,2 - xMS_B,N,N,C,C,S,1,2
        - xMS_B,N,N,C,C,S,2,2 - xMS_B,N,N,C,C,S,3,2 - xMS_B,N,N,C,C,S,4,2  = 0
 c146:  x_B,N,N,C,C,S,4 - xMS_B,N,N,C,C,S,0,3 - xMS_B,N,N,C,C,S,1,3
        - xMS_B,N,N,C,C,S,2,3 - xMS_B,N,N,C,C,S,3,3 - xMS_B,N,N,C,C,S,4,3  = 0
 c147:  x_B,N,N,C,C,S,4 - xMS_B,N,N,C,C,S,0,4 - xMS_B,N,N,C,C,S,1,4
        - xMS_B,N,N,C,C,S,2,4 - xMS_B,N,N,C,C,S,3,4 - xMS_B,N,N,C,C,S,4,4  = 0
 c148:  x_B,N,N,C,C,S,4 - xMS_B,N,N,C,C,S,0,5 - xMS_B,N,N,C,C,S,1,5
        - xMS_B,N,N,C,C,S,2,5 - xMS_B,N,N,C,C,S,3,5 - xMS_B,N,N,C,C,S,4,5  = 0
 c149:  x_B,N,N,C,C,P,4 - xMS_B,N,N,C,C,P,0,1 - xMS_B,N,N,C,C,P,1,1
        - xMS_B,N,N,C,C,P,2,1 - xMS_B,N,N,C,C,P,3,1 - xMS_B,N,N,C,C,P,4,1  = 0
 c150:  x_B,N,N,C,C,P,4 - xMS_B,N,N,C,C,P,0,2 - xMS_B,N,N,C,C,P,1,2
        - xMS_B,N,N,C,C,P,2,2 - xMS_B,N,N,C,C,P,3,2 - xMS_B,N,N,C,C,P,4,2  = 0
 c151:  x_B,N,N,C,C,P,4 - xMS_B,N,N,C,C,P,0,3 - xMS_B,N,N,C,C,P,1,3
        - xMS_B,N,N,C,C,P,2,3 - xMS_B,N,N,C,C,P,3,3 - xMS_B,N,N,C,C,P,4,3  = 0
 c152:  x_B,N,N,C,C,P,4 - xMS_B,N,N,C,C,P,0,4 - xMS_B,N,N,C,C,P,1,4
        - xMS_B,N,N,C,C,P,2,4 - xMS_B,N,N,C,C,P,3,4 - xMS_B,N,N,C,C,P,4,4  = 0
 c153:  x_B,N,N,C,C,P,4 - xMS_B,N,N,C,C,P,0,5 - xMS_B,N,N,C,C,P,1,5
        - xMS_B,N,N,C,C,P,2,5 - xMS_B,N,N,C,C,P,3,5 - xMS_B,N,N,C,C,P,4,5  = 0
 c154:  x_B,N,N,C,C,M,4 - xMS_B,N,N,C,C,M,0,1 - xMS_B,N,N,C,C,M,1,1
        - xMS_B,N,N,C,C,M,2,1 - xMS_B,N,N,C,C,M,3,1 - xMS_B,N,N,C,C,M,4,1  = 0
 c155:  x_B,N,N,C,C,M,4 - xMS_B,N,N,C,C,M,0,2 - xMS_B,N,N,C,C,M,1,2
        - xMS_B,N,N,C,C,M,2,2 - xMS_B,N,N,C,C,M,3,2 - xMS_B,N,N,C,C,M,4,2  = 0
 c156:  x_B,N,N,C,C,M,4 - xMS_B,N,N,C,C,M,0,3 - xMS_B,N,N,C,C,M,1,3
        - xMS_B,N,N,C,C,M,2,3 - xMS_B,N,N,C,C,M,3,3 - xMS_B,N,N,C,C,M,4,3  = 0
 c157:  x_B,N,N,C,C,M,4 - xMS_B,N,N,C,C,M,0,4 - xMS_B,N,N,C,C,M,1,4
        - xMS_B,N,N,C,C,M,2,4 - xMS_B,N,N,C,C,M,3,4 - xMS_B,N,N,C,C,M,4,4  = 0
 c158:  x_B,N,N,C,C,M,4 - xMS_B,N,N,C,C,M,0,5 - xMS_B,N,N,C,C,M,1,5
        - xMS_B,N,N,C,C,M,2,5 - xMS_B,N,N,C,C,M,3,5 - xMS_B,N,N,C,C,M,4,5  = 0
 c159:  x_B,N,N,C,C,L,4 - xMS_B,N,N,C,C,L,0,1 - xMS_B,N,N,C,C,L,1,1
        - xMS_B,N,N,C,C,L,2,1 - xMS_B,N,N,C,C,L,3,1 - xMS_B,N,N,C,C,L,4,1  = 0
 c160:  x_B,N,N,C,C,L,4 - xMS_B,N,N,C,C,L,0,2 - xMS_B,N,N,C,C,L,1,2
        - xMS_B,N,N,C,C,L,2,2 - xMS_B,N,N,C,C,L,3,2 - xMS_B,N,N,C,C,L,4,2  = 0
 c161:  x_B,N,N,C,C,L,4 - xMS_B,N,N,C,C,L,0,3 - xMS_B,N,N,C,C,L,1,3
        - xMS_B,N,N,C,C,L,2,3 - xMS_B,N,N,C,C,L,3,3 - xMS_B,N,N,C,C,L,4,3  = 0
 c162:  x_B,N,N,C,C,L,4 - xMS_B,N,N,C,C,L,0,4 - xMS_B,N,N,C,C,L,1,4
        - xMS_B,N,N,C,C,L,2,4 - xMS_B,N,N,C,C,L,3,4 - xMS_B,N,N,C,C,L,4,4  = 0
 c163:  x_B,N,N,C,C,L,4 - xMS_B,N,N,C,C,L,0,5 - xMS_B,N,N,C,C,L,1,5
        - xMS_B,N,N,C,C,L,2,5 - xMS_B,N,N,C,C,L,3,5 - xMS_B,N,N,C,C,L,4,5  = 0
 c164:  x_B,N,N,C,I,M,4 - xMS_B,N,N,C,I,M,0,1 - xMS_B,N,N,C,I,M,1,1
        - xMS_B,N,N,C,I,M,2,1 - xMS_B,N,N,C,I,M,3,1 - xMS_B,N,N,C,I,M,4,1  = 0
 c165:  x_B,N,N,C,I,M,4 - xMS_B,N,N,C,I,M,0,2 - xMS_B,N,N,C,I,M,1,2
        - xMS_B,N,N,C,I,M,2,2 - xMS_B,N,N,C,I,M,3,2 - xMS_B,N,N,C,I,M,4,2  = 0
 c166:  x_B,N,N,C,I,M,4 - xMS_B,N,N,C,I,M,0,3 - xMS_B,N,N,C,I,M,1,3
        - xMS_B,N,N,C,I,M,2,3 - xMS_B,N,N,C,I,M,3,3 - xMS_B,N,N,C,I,M,4,3  = 0
 c167:  x_B,N,N,C,I,M,4 - xMS_B,N,N,C,I,M,0,4 - xMS_B,N,N,C,I,M,1,4
        - xMS_B,N,N,C,I,M,2,4 - xMS_B,N,N,C,I,M,3,4 - xMS_B,N,N,C,I,M,4,4  = 0
 c168:  x_B,N,N,C,I,M,4 - xMS_B,N,N,C,I,M,0,5 - xMS_B,N,N,C,I,M,1,5
        - xMS_B,N,N,C,I,M,2,5 - xMS_B,N,N,C,I,M,3,5 - xMS_B,N,N,C,I,M,4,5  = 0
 c169:  x_B,N,N,C,I,L,4 - xMS_B,N,N,C,I,L,0,1 - xMS_B,N,N,C,I,L,1,1
        - xMS_B,N,N,C,I,L,2,1 - xMS_B,N,N,C,I,L,3,1 - xMS_B,N,N,C,I,L,4,1  = 0
 c170:  x_B,N,N,C,I,L,4 - xMS_B,N,N,C,I,L,0,2 - xMS_B,N,N,C,I,L,1,2
        - xMS_B,N,N,C,I,L,2,2 - xMS_B,N,N,C,I,L,3,2 - xMS_B,N,N,C,I,L,4,2  = 0
 c171:  x_B,N,N,C,I,L,4 - xMS_B,N,N,C,I,L,0,3 - xMS_B,N,N,C,I,L,1,3
        - xMS_B,N,N,C,I,L,2,3 - xMS_B,N,N,C,I,L,3,3 - xMS_B,N,N,C,I,L,4,3  = 0
 c172:  x_B,N,N,C,I,L,4 - xMS_B,N,N,C,I,L,0,4 - xMS_B,N,N,C,I,L,1,4
        - xMS_B,N,N,C,I,L,2,4 - xMS_B,N,N,C,I,L,3,4 - xMS_B,N,N,C,I,L,4,4  = 0
 c173:  x_B,N,N,C,I,L,4 - xMS_B,N,N,C,I,L,0,5 - xMS_B,N,N,C,I,L,1,5
        - xMS_B,N,N,C,I,L,2,5 - xMS_B,N,N,C,I,L,3,5 - xMS_B,N,N,C,I,L,4,5  = 0
 c174:  x_B,N,N,C,A,P,4 - xMS_B,N,N,C,A,P,0,1 - xMS_B,N,N,C,A,P,1,1
        - xMS_B,N,N,C,A,P,2,1 - xMS_B,N,N,C,A,P,3,1 - xMS_B,N,N,C,A,P,4,1  = 0
 c175:  x_B,N,N,C,A,P,4 - xMS_B,N,N,C,A,P,0,2 - xMS_B,N,N,C,A,P,1,2
        - xMS_B,N,N,C,A,P,2,2 - xMS_B,N,N,C,A,P,3,2 - xMS_B,N,N,C,A,P,4,2  = 0
 c176:  x_B,N,N,C,A,P,4 - xMS_B,N,N,C,A,P,0,3 - xMS_B,N,N,C,A,P,1,3
        - xMS_B,N,N,C,A,P,2,3 - xMS_B,N,N,C,A,P,3,3 - xMS_B,N,N,C,A,P,4,3  = 0
 c177:  x_B,N,N,C,A,P,4 - xMS_B,N,N,C,A,P,0,4 - xMS_B,N,N,C,A,P,1,4
        - xMS_B,N,N,C,A,P,2,4 - xMS_B,N,N,C,A,P,3,4 - xMS_B,N,N,C,A,P,4,4  = 0
 c178:  x_B,N,N,C,A,P,4 - xMS_B,N,N,C,A,P,0,5 - xMS_B,N,N,C,A,P,1,5
        - xMS_B,N,N,C,A,P,2,5 - xMS_B,N,N,C,A,P,3,5 - xMS_B,N,N,C,A,P,4,5  = 0
 c179:  x_B,N,N,C,A,M,4 - xMS_B,N,N,C,A,M,0,1 - xMS_B,N,N,C,A,M,1,1
        - xMS_B,N,N,C,A,M,2,1 - xMS_B,N,N,C,A,M,3,1 - xMS_B,N,N,C,A,M,4,1  = 0
 c180:  x_B,N,N,C,A,M,4 - xMS_B,N,N,C,A,M,0,2 - xMS_B,N,N,C,A,M,1,2
        - xMS_B,N,N,C,A,M,2,2 - xMS_B,N,N,C,A,M,3,2 - xMS_B,N,N,C,A,M,4,2  = 0
 c181:  x_B,N,N,C,A,M,4 - xMS_B,N,N,C,A,M,0,3 - xMS_B,N,N,C,A,M,1,3
        - xMS_B,N,N,C,A,M,2,3 - xMS_B,N,N,C,A,M,3,3 - xMS_B,N,N,C,A,M,4,3  = 0
 c182:  x_B,N,N,C,A,M,4 - xMS_B,N,N,C,A,M,0,4 - xMS_B,N,N,C,A,M,1,4
        - xMS_B,N,N,C,A,M,2,4 - xMS_B,N,N,C,A,M,3,4 - xMS_B,N,N,C,A,M,4,4  = 0
 c183:  x_B,N,N,C,A,M,4 - xMS_B,N,N,C,A,M,0,5 - xMS_B,N,N,C,A,M,1,5
        - xMS_B,N,N,C,A,M,2,5 - xMS_B,N,N,C,A,M,3,5 - xMS_B,N,N,C,A,M,4,5  = 0
 c184:  x_B,N,N,C,A,L,4 - xMS_B,N,N,C,A,L,0,1 - xMS_B,N,N,C,A,L,1,1
        - xMS_B,N,N,C,A,L,2,1 - xMS_B,N,N,C,A,L,3,1 - xMS_B,N,N,C,A,L,4,1  = 0
 c185:  x_B,N,N,C,A,L,4 - xMS_B,N,N,C,A,L,0,2 - xMS_B,N,N,C,A,L,1,2
        - xMS_B,N,N,C,A,L,2,2 - xMS_B,N,N,C,A,L,3,2 - xMS_B,N,N,C,A,L,4,2  = 0
 c186:  x_B,N,N,C,A,L,4 - xMS_B,N,N,C,A,L,0,3 - xMS_B,N,N,C,A,L,1,3
        - xMS_B,N,N,C,A,L,2,3 - xMS_B,N,N,C,A,L,3,3 - xMS_B,N,N,C,A,L,4,3  = 0
 c187:  x_B,N,N,C,A,L,4 - xMS_B,N,N,C,A,L,0,4 - xMS_B,N,N,C,A,L,1,4
        - xMS_B,N,N,C,A,L,2,4 - xMS_B,N,N,C,A,L,3,4 - xMS_B,N,N,C,A,L,4,4  = 0
 c188:  x_B,N,N,C,A,L,4 - xMS_B,N,N,C,A,L,0,5 - xMS_B,N,N,C,A,L,1,5
        - xMS_B,N,N,C,A,L,2,5 - xMS_B,N,N,C,A,L,3,5 - xMS_B,N,N,C,A,L,4,5  = 0
 c189:  x_B,N,N,C,L,M,4 - xMS_B,N,N,C,L,M,0,1 - xMS_B,N,N,C,L,M,1,1
        - xMS_B,N,N,C,L,M,2,1 - xMS_B,N,N,C,L,M,3,1 - xMS_B,N,N,C,L,M,4,1  = 0
 c190:  x_B,N,N,C,L,M,4 - xMS_B,N,N,C,L,M,0,2 - xMS_B,N,N,C,L,M,1,2
        - xMS_B,N,N,C,L,M,2,2 - xMS_B,N,N,C,L,M,3,2 - xMS_B,N,N,C,L,M,4,2  = 0
 c191:  x_B,N,N,C,L,M,4 - xMS_B,N,N,C,L,M,0,3 - xMS_B,N,N,C,L,M,1,3
        - xMS_B,N,N,C,L,M,2,3 - xMS_B,N,N,C,L,M,3,3 - xMS_B,N,N,C,L,M,4,3  = 0
 c192:  x_B,N,N,C,L,M,4 - xMS_B,N,N,C,L,M,0,4 - xMS_B,N,N,C,L,M,1,4
        - xMS_B,N,N,C,L,M,2,4 - xMS_B,N,N,C,L,M,3,4 - xMS_B,N,N,C,L,M,4,4  = 0
 c193:  x_B,N,N,C,L,M,4 - xMS_B,N,N,C,L,M,0,5 - xMS_B,N,N,C,L,M,1,5
        - xMS_B,N,N,C,L,M,2,5 - xMS_B,N,N,C,L,M,3,5 - xMS_B,N,N,C,L,M,4,5  = 0
 c194:  x_B,N,N,C,L,L,4 - xMS_B,N,N,C,L,L,0,1 - xMS_B,N,N,C,L,L,1,1
        - xMS_B,N,N,C,L,L,2,1 - xMS_B,N,N,C,L,L,3,1 - xMS_B,N,N,C,L,L,4,1  = 0
 c195:  x_B,N,N,C,L,L,4 - xMS_B,N,N,C,L,L,0,2 - xMS_B,N,N,C,L,L,1,2
        - xMS_B,N,N,C,L,L,2,2 - xMS_B,N,N,C,L,L,3,2 - xMS_B,N,N,C,L,L,4,2  = 0
 c196:  x_B,N,N,C,L,L,4 - xMS_B,N,N,C,L,L,0,3 - xMS_B,N,N,C,L,L,1,3
        - xMS_B,N,N,C,L,L,2,3 - xMS_B,N,N,C,L,L,3,3 - xMS_B,N,N,C,L,L,4,3  = 0
 c197:  x_B,N,N,C,L,L,4 - xMS_B,N,N,C,L,L,0,4 - xMS_B,N,N,C,L,L,1,4
        - xMS_B,N,N,C,L,L,2,4 - xMS_B,N,N,C,L,L,3,4 - xMS_B,N,N,C,L,L,4,4  = 0
 c198:  x_B,N,N,C,L,L,4 - xMS_B,N,N,C,L,L,0,5 - xMS_B,N,N,C,L,L,1,5
        - xMS_B,N,N,C,L,L,2,5 - xMS_B,N,N,C,L,L,3,5 - xMS_B,N,N,C,L,L,4,5  = 0
 c199:  x_B,N,N,C,N,N,4 - xMS_B,N,N,C,N,N,0,1 - xMS_B,N,N,C,N,N,1,1
        - xMS_B,N,N,C,N,N,2,1 - xMS_B,N,N,C,N,N,3,1 - xMS_B,N,N,C,N,N,4,1  = 0
 c200:  x_B,N,N,C,N,N,4 - xMS_B,N,N,C,N,N,0,2 - xMS_B,N,N,C,N,N,1,2
        - xMS_B,N,N,C,N,N,2,2 - xMS_B,N,N,C,N,N,3,2 - xMS_B,N,N,C,N,N,4,2  = 0
 c201:  x_B,N,N,C,N,N,4 - xMS_B,N,N,C,N,N,0,3 - xMS_B,N,N,C,N,N,1,3
        - xMS_B,N,N,C,N,N,2,3 - xMS_B,N,N,C,N,N,3,3 - xMS_B,N,N,C,N,N,4,3  = 0
 c202:  x_B,N,N,C,N,N,4 - xMS_B,N,N,C,N,N,0,4 - xMS_B,N,N,C,N,N,1,4
        - xMS_B,N,N,C,N,N,2,4 - xMS_B,N,N,C,N,N,3,4 - xMS_B,N,N,C,N,N,4,4  = 0
 c203:  x_B,N,N,C,N,N,4 - xMS_B,N,N,C,N,N,0,5 - xMS_B,N,N,C,N,N,1,5
        - xMS_B,N,N,C,N,N,2,5 - xMS_B,N,N,C,N,N,3,5 - xMS_B,N,N,C,N,N,4,5  = 0
 c204:  x_B,N,N,L,C,M,0 - xNG_B,N,N,L,C,M,1  = 0
 c205:  x_B,N,N,L,C,L,0 - xNG_B,N,N,L,C,L,1  = 0
 c206:  x_B,N,N,L,A,S,0 - xNG_B,N,N,L,A,S,1  = 0
 c207:  x_B,N,N,L,A,M,0 - xNG_B,N,N,L,A,M,1  = 0
 c208:  x_B,N,N,L,A,L,0 - xNG_B,N,N,L,A,L,1  = 0
 c209:  x_B,N,N,H,C,L,0 - xNG_B,N,N,H,C,L,1  = 0
 c210:  x_B,N,N,C,P,P,0 - xNG_B,N,N,C,P,P,1  = 0
 c211:  x_B,N,N,C,P,M,0 - xNG_B,N,N,C,P,M,1  = 0
 c212:  x_B,N,N,C,P,L,0 - xNG_B,N,N,C,P,L,1  = 0
 c213:  x_B,N,N,C,D,S,0 - xNG_B,N,N,C,D,S,1  = 0
 c214:  x_B,N,N,C,D,P,0 - xNG_B,N,N,C,D,P,1  = 0
 c215:  x_B,N,N,C,D,M,0 - xNG_B,N,N,C,D,M,1  = 0
 c216:  x_B,N,N,C,D,L,0 - xNG_B,N,N,C,D,L,1  = 0
 c217:  x_B,N,N,C,W,S,0 - xNG_B,N,N,C,W,S,1  = 0
 c218:  x_B,N,N,C,W,P,0 - xNG_B,N,N,C,W,P,1  = 0
 c219:  x_B,N,N,C,W,M,0 - xNG_B,N,N,C,W,M,1  = 0
 c220:  x_B,N,N,C,W,L,0 - xNG_B,N,N,C,W,L,1  = 0
 c221:  x_B,N,N,C,C,S,0 - xNG_B,N,N,C,C,S,1  = 0
 c222:  x_B,N,N,C,C,P,0 - xNG_B,N,N,C,C,P,1  = 0
 c223:  x_B,N,N,C,C,M,0 - xNG_B,N,N,C,C,M,1  = 0
 c224:  x_B,N,N,C,C,L,0 - xNG_B,N,N,C,C,L,1  = 0
 c225:  x_B,N,N,C,I,M,0 - xNG_B,N,N,C,I,M,1  = 0
 c226:  x_B,N,N,C,I,L,0 - xNG_B,N,N,C,I,L,1  = 0
 c227:  x_B,N,N,C,A,P,0 - xNG_B,N,N,C,A,P,1  = 0
 c228:  x_B,N,N,C,A,M,0 - xNG_B,N,N,C,A,M,1  = 0
 c229:  x_B,N,N,C,A,L,0 - xNG_B,N,N,C,A,L,1  = 0
 c230:  x_B,N,N,C,L,M,0 - xNG_B,N,N,C,L,M,1  = 0
 c231:  x_B,N,N,C,L,L,0 - xNG_B,N,N,C,L,L,1  = 0
 c232:  x_B,N,N,C,N,N,0 - xNG_B,N,N,C,N,N,1  = 0
 c233:  0.998 xNG_B,N,N,L,C,M,1 - xNG_B,N,N,L,C,M,2  = 0
 c234:  0.998 xNG_B,N,N,L,C,M,2 - xNG_B,N,N,L,C,M,3  = 0
 c235:  0.998 xNG_B,N,N,L,C,M,3 - xNG_B,N,N,L,C,M,4  = 0
 c236:  0.998 xNG_B,N,N,L,C,M,4 - xNG_B,N,N,L,C,M,5  = 0
 c237:  0.998 xNG_B,N,N,L,C,L,1 - xNG_B,N,N,L,C,L,2  = 0
 c238:  0.998 xNG_B,N,N,L,C,L,2 - xNG_B,N,N,L,C,L,3  = 0
 c239:  0.998 xNG_B,N,N,L,C,L,3 - xNG_B,N,N,L,C,L,4  = 0
 c240:  0.998 xNG_B,N,N,L,C,L,4 - xNG_B,N,N,L,C,L,5  = 0
 c241:  0.998 xNG_B,N,N,L,A,S,1 - xNG_B,N,N,L,A,S,2  = 0
 c242:  0.998 xNG_B,N,N,L,A,S,2 - xNG_B,N,N,L,A,S,3  = 0
 c243:  0.998 xNG_B,N,N,L,A,S,3 - xNG_B,N,N,L,A,S,4  = 0
 c244:  0.998 xNG_B,N,N,L,A,S,4 - xNG_B,N,N,L,A,S,5  = 0
 c245:  0.998 xNG_B,N,N,L,A,M,1 - xNG_B,N,N,L,A,M,2  = 0
 c246:  0.998 xNG_B,N,N,L,A,M,2 - xNG_B,N,N,L,A,M,3  = 0
 c247:  0.998 xNG_B,N,N,L,A,M,3 - xNG_B,N,N,L,A,M,4  = 0
 c248:  0.998 xNG_B,N,N,L,A,M,4 - xNG_B,N,N,L,A,M,5  = 0
 c249:  0.998 xNG_B,N,N,L,A,L,1 - xNG_B,N,N,L,A,L,2  = 0
 c250:  0.998 xNG_B,N,N,L,A,L,2 - xNG_B,N,N,L,A,L,3  = 0
 c251:  0.998 xNG_B,N,N,L,A,L,3 - xNG_B,N,N,L,A,L,4  = 0
 c252:  0.998 xNG_B,N,N,L,A,L,4 - xNG_B,N,N,L,A,L,5  = 0
 c253:  0.998 xNG_B,N,N,H,C,L,1 - xNG_B,N,N,H,C,L,2  = 0
 c254:  0.998 xNG_B,N,N,H,C,L,2 - xNG_B,N,N,H,C,L,3  = 0
 c255:  0.998 xNG_B,N,N,H,C,L,3 - xNG_B,N,N,H,C,L,4  = 0
 c256:  0.998 xNG_B,N,N,H,C,L,4 - xNG_B,N,N,H,C,L,5  = 0
 c257:  0.998 xNG_B,N,N,C,P,P,1 - xNG_B,N,N,C,P,P,2  = 0
 c258:  0.998 xNG_B,N,N,C,P,P,2 - xNG_B,N,N,C,P,P,3  = 0
 c259:  0.998 xNG_B,N,N,C,P,P,3 - xNG_B,N,N,C,P,P,4  = 0
 c260:  0.998 xNG_B,N,N,C,P,P,4 - xNG_B,N,N,C,P,P,5  = 0
 c261:  0.998 xNG_B,N,N,C,P,M,1 - xNG_B,N,N,C,P,M,2  = 0
 c262:  0.998 xNG_B,N,N,C,P,M,2 - xNG_B,N,N,C,P,M,3  = 0
 c263:  0.998 xNG_B,N,N,C,P,M,3 - xNG_B,N,N,C,P,M,4  = 0
 c264:  0.998 xNG_B,N,N,C,P,M,4 - xNG_B,N,N,C,P,M,5  = 0
 c265:  0.998 xNG_B,N,N,C,P,L,1 - xNG_B,N,N,C,P,L,2  = 0
 c266:  0.998 xNG_B,N,N,C,P,L,2 - xNG_B,N,N,C,P,L,3  = 0
 c267:  0.998 xNG_B,N,N,C,P,L,3 - xNG_B,N,N,C,P,L,4  = 0
 c268:  0.998 xNG_B,N,N,C,P,L,4 - xNG_B,N,N,C,P,L,5  = 0
 c269:  0.998 xNG_B,N,N,C,D,S,1 - xNG_B,N,N,C,D,S,2  = 0
 c270:  0.998 xNG_B,N,N,C,D,S,2 - xNG_B,N,N,C,D,S,3  = 0
 c271:  0.998 xNG_B,N,N,C,D,S,3 - xNG_B,N,N,C,D,S,4  = 0
 c272:  0.998 xNG_B,N,N,C,D,S,4 - xNG_B,N,N,C,D,S,5  = 0
 c273:  0.998 xNG_B,N,N,C,D,P,1 - xNG_B,N,N,C,D,P,2  = 0
 c274:  0.998 xNG_B,N,N,C,D,P,2 - xNG_B,N,N,C,D,P,3  = 0
 c275:  0.998 xNG_B,N,N,C,D,P,3 - xNG_B,N,N,C,D,P,4  = 0
 c276:  0.998 xNG_B,N,N,C,D,P,4 - xNG_B,N,N,C,D,P,5  = 0
 c277:  0.998 xNG_B,N,N,C,D,M,1 - xNG_B,N,N,C,D,M,2  = 0
 c278:  0.998 xNG_B,N,N,C,D,M,2 - xNG_B,N,N,C,D,M,3  = 0
 c279:  0.998 xNG_B,N,N,C,D,M,3 - xNG_B,N,N,C,D,M,4  = 0
 c280:  0.998 xNG_B,N,N,C,D,M,4 - xNG_B,N,N,C,D,M,5  = 0
 c281:  0.998 xNG_B,N,N,C,D,L,1 - xNG_B,N,N,C,D,L,2  = 0
 c282:  0.998 xNG_B,N,N,C,D,L,2 - xNG_B,N,N,C,D,L,3  = 0
 c283:  0.998 xNG_B,N,N,C,D,L,3 - xNG_B,N,N,C,D,L,4  = 0
 c284:  0.998 xNG_B,N,N,C,D,L,4 - xNG_B,N,N,C,D,L,5  = 0
 c285:  0.998 xNG_B,N,N,C,W,S,1 - xNG_B,N,N,C,W,S,2  = 0
 c286:  0.998 xNG_B,N,N,C,W,S,2 - xNG_B,N,N,C,W,S,3  = 0
 c287:  0.998 xNG_B,N,N,C,W,S,3 - xNG_B,N,N,C,W,S,4  = 0
 c288:  0.998 xNG_B,N,N,C,W,S,4 - xNG_B,N,N,C,W,S,5  = 0
 c289:  0.998 xNG_B,N,N,C,W,P,1 - xNG_B,N,N,C,W,P,2  = 0
 c290:  0.998 xNG_B,N,N,C,W,P,2 - xNG_B,N,N,C,W,P,3  = 0
 c291:  0.998 xNG_B,N,N,C,W,P,3 - xNG_B,N,N,C,W,P,4  = 0
 c292:  0.998 xNG_B,N,N,C,W,P,4 - xNG_B,N,N,C,W,P,5  = 0
 c293:  0.998 xNG_B,N,N,C,W,M,1 - xNG_B,N,N,C,W,M,2  = 0
 c294:  0.998 xNG_B,N,N,C,W,M,2 - xNG_B,N,N,C,W,M,3  = 0
 c295:  0.998 xNG_B,N,N,C,W,M,3 - xNG_B,N,N,C,W,M,4  = 0
 c296:  0.998 xNG_B,N,N,C,W,M,4 - xNG_B,N,N,C,W,M,5  = 0
 c297:  0.998 xNG_B,N,N,C,W,L,1 - xNG_B,N,N,C,W,L,2  = 0
 c298:  0.998 xNG_B,N,N,C,W,L,2 - xNG_B,N,N,C,W,L,3  = 0
 c299:  0.998 xNG_B,N,N,C,W,L,3 - xNG_B,N,N,C,W,L,4  = 0
 c300:  0.998 xNG_B,N,N,C,W,L,4 - xNG_B,N,N,C,W,L,5  = 0
 c301:  0.998 xNG_B,N,N,C,C,S,1 - xNG_B,N,N,C,C,S,2  = 0
 c302:  0.998 xNG_B,N,N,C,C,S,2 - xNG_B,N,N,C,C,S,3  = 0
 c303:  0.998 xNG_B,N,N,C,C,S,3 - xNG_B,N,N,C,C,S,4  = 0
 c304:  0.998 xNG_B,N,N,C,C,S,4 - xNG_B,N,N,C,C,S,5  = 0
 c305:  0.998 xNG_B,N,N,C,C,P,1 - xNG_B,N,N,C,C,P,2  = 0
 c306:  0.998 xNG_B,N,N,C,C,P,2 - xNG_B,N,N,C,C,P,3  = 0
 c307:  0.998 xNG_B,N,N,C,C,P,3 - xNG_B,N,N,C,C,P,4  = 0
 c308:  0.998 xNG_B,N,N,C,C,P,4 - xNG_B,N,N,C,C,P,5  = 0
 c309:  0.998 xNG_B,N,N,C,C,M,1 - xNG_B,N,N,C,C,M,2  = 0
 c310:  0.998 xNG_B,N,N,C,C,M,2 - xNG_B,N,N,C,C,M,3  = 0
 c311:  0.998 xNG_B,N,N,C,C,M,3 - xNG_B,N,N,C,C,M,4  = 0
 c312:  0.998 xNG_B,N,N,C,C,M,4 - xNG_B,N,N,C,C,M,5  = 0
 c313:  0.998 xNG_B,N,N,C,C,L,1 - xNG_B,N,N,C,C,L,2  = 0
 c314:  0.998 xNG_B,N,N,C,C,L,2 - xNG_B,N,N,C,C,L,3  = 0
 c315:  0.998 xNG_B,N,N,C,C,L,3 - xNG_B,N,N,C,C,L,4  = 0
 c316:  0.998 xNG_B,N,N,C,C,L,4 - xNG_B,N,N,C,C,L,5  = 0
 c317:  0.998 xNG_B,N,N,C,I,M,1 - xNG_B,N,N,C,I,M,2  = 0
 c318:  0.998 xNG_B,N,N,C,I,M,2 - xNG_B,N,N,C,I,M,3  = 0
 c319:  0.998 xNG_B,N,N,C,I,M,3 - xNG_B,N,N,C,I,M,4  = 0
 c320:  0.998 xNG_B,N,N,C,I,M,4 - xNG_B,N,N,C,I,M,5  = 0
 c321:  0.998 xNG_B,N,N,C,I,L,1 - xNG_B,N,N,C,I,L,2  = 0
 c322:  0.998 xNG_B,N,N,C,I,L,2 - xNG_B,N,N,C,I,L,3  = 0
 c323:  0.998 xNG_B,N,N,C,I,L,3 - xNG_B,N,N,C,I,L,4  = 0
 c324:  0.998 xNG_B,N,N,C,I,L,4 - xNG_B,N,N,C,I,L,5  = 0
 c325:  0.998 xNG_B,N,N,C,A,P,1 - xNG_B,N,N,C,A,P,2  = 0
 c326:  0.998 xNG_B,N,N,C,A,P,2 - xNG_B,N,N,C,A,P,3  = 0
 c327:  0.998 xNG_B,N,N,C,A,P,3 - xNG_B,N,N,C,A,P,4  = 0
 c328:  0.998 xNG_B,N,N,C,A,P,4 - xNG_B,N,N,C,A,P,5  = 0
 c329:  0.998 xNG_B,N,N,C,A,M,1 - xNG_B,N,N,C,A,M,2  = 0
 c330:  0.998 xNG_B,N,N,C,A,M,2 - xNG_B,N,N,C,A,M,3  = 0
 c331:  0.998 xNG_B,N,N,C,A,M,3 - xNG_B,N,N,C,A,M,4  = 0
 c332:  0.998 xNG_B,N,N,C,A,M,4 - xNG_B,N,N,C,A,M,5  = 0
 c333:  0.998 xNG_B,N,N,C,A,L,1 - xNG_B,N,N,C,A,L,2  = 0
 c334:  0.998 xNG_B,N,N,C,A,L,2 - xNG_B,N,N,C,A,L,3  = 0
 c335:  0.998 xNG_B,N,N,C,A,L,3 - xNG_B,N,N,C,A,L,4  = 0
 c336:  0.998 xNG_B,N,N,C,A,L,4 - xNG_B,N,N,C,A,L,5  = 0
 c337:  0.998 xNG_B,N,N,C,L,M,1 - xNG_B,N,N,C,L,M,2  = 0
 c338:  0.998 xNG_B,N,N,C,L,M,2 - xNG_B,N,N,C,L,M,3  = 0
 c339:  0.998 xNG_B,N,N,C,L,M,3 - xNG_B,N,N,C,L,M,4  = 0
 c340:  0.998 xNG_B,N,N,C,L,M,4 - xNG_B,N,N,C,L,M,5  = 0
 c341:  0.998 xNG_B,N,N,C,L,L,1 - xNG_B,N,N,C,L,L,2  = 0
 c342:  0.998 xNG_B,N,N,C,L,L,2 - xNG_B,N,N,C,L,L,3  = 0
 c343:  0.998 xNG_B,N,N,C,L,L,3 - xNG_B,N,N,C,L,L,4  = 0
 c344:  0.998 xNG_B,N,N,C,L,L,4 - xNG_B,N,N,C,L,L,5  = 0
 c345:  xNG_B,N,N,C,N,N,1 - xNG_B,N,N,C,N,N,2  = 0
 c346:  xNG_B,N,N,C,N,N,2 - xNG_B,N,N,C,N,N,3  = 0
 c347:  xNG_B,N,N,C,N,N,3 - xNG_B,N,N,C,N,N,4  = 0
 c348:  xNG_B,N,N,C,N,N,4 - xNG_B,N,N,C,N,N,5  = 0
 c349:  x_B,N,N,L,C,M,1 - xPB_B,N,N,L,C,M,0,1 - xPB_B,N,N,L,C,M,1,1
        - xPB_B,N,N,L,C,M,2,1 - xPB_B,N,N,L,C,M,3,1 - xPB_B,N,N,L,C,M,4,1  = 0
 c350:  x_B,N,N,L,C,L,1 - xPB_B,N,N,L,C,L,0,1 - xPB_B,N,N,L,C,L,1,1
        - xPB_B,N,N,L,C,L,2,1 - xPB_B,N,N,L,C,L,3,1 - xPB_B,N,N,L,C,L,4,1  = 0
 c351:  x_B,N,N,L,A,S,1 - xPB_B,N,N,L,A,S,0,1 - xPB_B,N,N,L,A,S,1,1
        - xPB_B,N,N,L,A,S,2,1 - xPB_B,N,N,L,A,S,3,1 - xPB_B,N,N,L,A,S,4,1  = 0
 c352:  x_B,N,N,L,A,M,1 - xPB_B,N,N,L,A,M,0,1 - xPB_B,N,N,L,A,M,1,1
        - xPB_B,N,N,L,A,M,2,1 - xPB_B,N,N,L,A,M,3,1 - xPB_B,N,N,L,A,M,4,1  = 0
 c353:  x_B,N,N,L,A,L,1 - xPB_B,N,N,L,A,L,0,1 - xPB_B,N,N,L,A,L,1,1
        - xPB_B,N,N,L,A,L,2,1 - xPB_B,N,N,L,A,L,3,1 - xPB_B,N,N,L,A,L,4,1  = 0
 c354:  x_B,N,N,H,C,L,1 - xPB_B,N,N,H,C,L,0,1 - xPB_B,N,N,H,C,L,1,1
        - xPB_B,N,N,H,C,L,2,1 - xPB_B,N,N,H,C,L,3,1 - xPB_B,N,N,H,C,L,4,1  = 0
 c355:  x_B,N,N,C,P,P,1 - xPB_B,N,N,C,P,P,0,1 - xPB_B,N,N,C,P,P,1,1
        - xPB_B,N,N,C,P,P,2,1 - xPB_B,N,N,C,P,P,3,1 - xPB_B,N,N,C,P,P,4,1  = 0
 c356:  x_B,N,N,C,P,M,1 - xPB_B,N,N,C,P,M,0,1 - xPB_B,N,N,C,P,M,1,1
        - xPB_B,N,N,C,P,M,2,1 - xPB_B,N,N,C,P,M,3,1 - xPB_B,N,N,C,P,M,4,1  = 0
 c357:  x_B,N,N,C,P,L,1 - xPB_B,N,N,C,P,L,0,1 - xPB_B,N,N,C,P,L,1,1
        - xPB_B,N,N,C,P,L,2,1 - xPB_B,N,N,C,P,L,3,1 - xPB_B,N,N,C,P,L,4,1  = 0
 c358:  x_B,N,N,C,D,S,1 - xPB_B,N,N,C,D,S,0,1 - xPB_B,N,N,C,D,S,1,1
        - xPB_B,N,N,C,D,S,2,1 - xPB_B,N,N,C,D,S,3,1 - xPB_B,N,N,C,D,S,4,1  = 0
 c359:  x_B,N,N,C,D,P,1 - xPB_B,N,N,C,D,P,0,1 - xPB_B,N,N,C,D,P,1,1
        - xPB_B,N,N,C,D,P,2,1 - xPB_B,N,N,C,D,P,3,1 - xPB_B,N,N,C,D,P,4,1  = 0
 c360:  x_B,N,N,C,D,M,1 - xPB_B,N,N,C,D,M,0,1 - xPB_B,N,N,C,D,M,1,1
        - xPB_B,N,N,C,D,M,2,1 - xPB_B,N,N,C,D,M,3,1 - xPB_B,N,N,C,D,M,4,1  = 0
 c361:  x_B,N,N,C,D,L,1 - xPB_B,N,N,C,D,L,0,1 - xPB_B,N,N,C,D,L,1,1
        - xPB_B,N,N,C,D,L,2,1 - xPB_B,N,N,C,D,L,3,1 - xPB_B,N,N,C,D,L,4,1  = 0
 c362:  x_B,N,N,C,W,S,1 - xPB_B,N,N,C,W,S,0,1 - xPB_B,N,N,C,W,S,1,1
        - xPB_B,N,N,C,W,S,2,1 - xPB_B,N,N,C,W,S,3,1 - xPB_B,N,N,C,W,S,4,1  = 0
 c363:  x_B,N,N,C,W,P,1 - xPB_B,N,N,C,W,P,0,1 - xPB_B,N,N,C,W,P,1,1
        - xPB_B,N,N,C,W,P,2,1 - xPB_B,N,N,C,W,P,3,1 - xPB_B,N,N,C,W,P,4,1  = 0
 c364:  x_B,N,N,C,W,M,1 - xPB_B,N,N,C,W,M,0,1 - xPB_B,N,N,C,W,M,1,1
        - xPB_B,N,N,C,W,M,2,1 - xPB_B,N,N,C,W,M,3,1 - xPB_B,N,N,C,W,M,4,1  = 0
 c365:  x_B,N,N,C,W,L,1 - xPB_B,N,N,C,W,L,0,1 - xPB_B,N,N,C,W,L,1,1
        - xPB_B,N,N,C,W,L,2,1 - xPB_B,N,N,C,W,L,3,1 - xPB_B,N,N,C,W,L,4,1  = 0
 c366:  x_B,N,N,C,C,S,1 - xPB_B,N,N,C,C,S,0,1 - xPB_B,N,N,C,C,S,1,1
        - xPB_B,N,N,C,C,S,2,1 - xPB_B,N,N,C,C,S,3,1 - xPB_B,N,N,C,C,S,4,1  = 0
 c367:  x_B,N,N,C,C,P,1 - xPB_B,N,N,C,C,P,0,1 - xPB_B,N,N,C,C,P,1,1
        - xPB_B,N,N,C,C,P,2,1 - xPB_B,N,N,C,C,P,3,1 - xPB_B,N,N,C,C,P,4,1  = 0
 c368:  x_B,N,N,C,C,M,1 - xPB_B,N,N,C,C,M,0,1 - xPB_B,N,N,C,C,M,1,1
        - xPB_B,N,N,C,C,M,2,1 - xPB_B,N,N,C,C,M,3,1 - xPB_B,N,N,C,C,M,4,1  = 0
 c369:  x_B,N,N,C,C,L,1 - xPB_B,N,N,C,C,L,0,1 - xPB_B,N,N,C,C,L,1,1
        - xPB_B,N,N,C,C,L,2,1 - xPB_B,N,N,C,C,L,3,1 - xPB_B,N,N,C,C,L,4,1  = 0
 c370:  x_B,N,N,C,I,M,1 - xPB_B,N,N,C,I,M,0,1 - xPB_B,N,N,C,I,M,1,1
        - xPB_B,N,N,C,I,M,2,1 - xPB_B,N,N,C,I,M,3,1 - xPB_B,N,N,C,I,M,4,1  = 0
 c371:  x_B,N,N,C,I,L,1 - xPB_B,N,N,C,I,L,0,1 - xPB_B,N,N,C,I,L,1,1
        - xPB_B,N,N,C,I,L,2,1 - xPB_B,N,N,C,I,L,3,1 - xPB_B,N,N,C,I,L,4,1  = 0
 c372:  x_B,N,N,C,A,P,1 - xPB_B,N,N,C,A,P,0,1 - xPB_B,N,N,C,A,P,1,1
        - xPB_B,N,N,C,A,P,2,1 - xPB_B,N,N,C,A,P,3,1 - xPB_B,N,N,C,A,P,4,1  = 0
 c373:  x_B,N,N,C,A,M,1 - xPB_B,N,N,C,A,M,0,1 - xPB_B,N,N,C,A,M,1,1
        - xPB_B,N,N,C,A,M,2,1 - xPB_B,N,N,C,A,M,3,1 - xPB_B,N,N,C,A,M,4,1  = 0
 c374:  x_B,N,N,C,A,L,1 - xPB_B,N,N,C,A,L,0,1 - xPB_B,N,N,C,A,L,1,1
        - xPB_B,N,N,C,A,L,2,1 - xPB_B,N,N,C,A,L,3,1 - xPB_B,N,N,C,A,L,4,1  = 0
 c375:  x_B,N,N,C,L,M,1 - xPB_B,N,N,C,L,M,0,1 - xPB_B,N,N,C,L,M,1,1
        - xPB_B,N,N,C,L,M,2,1 - xPB_B,N,N,C,L,M,3,1 - xPB_B,N,N,C,L,M,4,1  = 0
 c376:  x_B,N,N,C,L,L,1 - xPB_B,N,N,C,L,L,0,1 - xPB_B,N,N,C,L,L,1,1
        - xPB_B,N,N,C,L,L,2,1 - xPB_B,N,N,C,L,L,3,1 - xPB_B,N,N,C,L,L,4,1  = 0
 c377:  x_B,N,N,C,N,N,1 - xPB_B,N,N,C,N,N,0,1 - xPB_B,N,N,C,N,N,1,1
        - xPB_B,N,N,C,N,N,2,1 - xPB_B,N,N,C,N,N,3,1 - xPB_B,N,N,C,N,N,4,1  = 0
 c378:  0.998 xPB_B,N,N,L,C,M,0,1 - xPB_B,N,N,L,C,M,0,2  = 0
 c379:  0.998 xPB_B,N,N,L,C,M,0,2 - xPB_B,N,N,L,C,M,0,3  = 0
 c380:  0.998 xPB_B,N,N,L,C,M,0,3 - xPB_B,N,N,L,C,M,0,4  = 0
 c381:  0.998 xPB_B,N,N,L,C,M,0,4 - xPB_B,N,N,L,C,M,0,5  = 0
 c382:  0.998 xPB_B,N,N,L,C,M,1,1 - xPB_B,N,N,L,C,M,1,2  = 0
 c383:  0.998 xPB_B,N,N,L,C,M,1,2 - xPB_B,N,N,L,C,M,1,3  = 0
 c384:  0.998 xPB_B,N,N,L,C,M,1,3 - xPB_B,N,N,L,C,M,1,4  = 0
 c385:  0.998 xPB_B,N,N,L,C,M,1,4 - xPB_B,N,N,L,C,M,1,5  = 0
 c386:  0.998 xPB_B,N,N,L,C,M,2,1 - xPB_B,N,N,L,C,M,2,2  = 0
 c387:  0.998 xPB_B,N,N,L,C,M,2,2 - xPB_B,N,N,L,C,M,2,3  = 0
 c388:  0.998 xPB_B,N,N,L,C,M,2,3 - xPB_B,N,N,L,C,M,2,4  = 0
 c389:  0.998 xPB_B,N,N,L,C,M,2,4 - xPB_B,N,N,L,C,M,2,5  = 0
 c390:  0.998 xPB_B,N,N,L,C,M,3,1 - xPB_B,N,N,L,C,M,3,2  = 0
 c391:  0.998 xPB_B,N,N,L,C,M,3,2 - xPB_B,N,N,L,C,M,3,3  = 0
 c392:  0.998 xPB_B,N,N,L,C,M,3,3 - xPB_B,N,N,L,C,M,3,4  = 0
 c393:  0.998 xPB_B,N,N,L,C,M,3,4 - xPB_B,N,N,L,C,M,3,5  = 0
 c394:  0.998 xPB_B,N,N,L,C,M,4,1 - xPB_B,N,N,L,C,M,4,2  = 0
 c395:  0.998 xPB_B,N,N,L,C,M,4,2 - xPB_B,N,N,L,C,M,4,3  = 0
 c396:  0.998 xPB_B,N,N,L,C,M,4,3 - xPB_B,N,N,L,C,M,4,4  = 0
 c397:  0.998 xPB_B,N,N,L,C,M,4,4 - xPB_B,N,N,L,C,M,4,5  = 0
 c398:  0.998 xPB_B,N,N,L,C,L,0,1 - xPB_B,N,N,L,C,L,0,2  = 0
 c399:  0.998 xPB_B,N,N,L,C,L,0,2 - xPB_B,N,N,L,C,L,0,3  = 0
 c400:  0.998 xPB_B,N,N,L,C,L,0,3 - xPB_B,N,N,L,C,L,0,4  = 0
 c401:  0.998 xPB_B,N,N,L,C,L,0,4 - xPB_B,N,N,L,C,L,0,5  = 0
 c402:  0.998 xPB_B,N,N,L,C,L,1,1 - xPB_B,N,N,L,C,L,1,2  = 0
 c403:  0.998 xPB_B,N,N,L,C,L,1,2 - xPB_B,N,N,L,C,L,1,3  = 0
 c404:  0.998 xPB_B,N,N,L,C,L,1,3 - xPB_B,N,N,L,C,L,1,4  = 0
 c405:  0.998 xPB_B,N,N,L,C,L,1,4 - xPB_B,N,N,L,C,L,1,5  = 0
 c406:  0.998 xPB_B,N,N,L,C,L,2,1 - xPB_B,N,N,L,C,L,2,2  = 0
 c407:  0.998 xPB_B,N,N,L,C,L,2,2 - xPB_B,N,N,L,C,L,2,3  = 0
 c408:  0.998 xPB_B,N,N,L,C,L,2,3 - xPB_B,N,N,L,C,L,2,4  = 0
 c409:  0.998 xPB_B,N,N,L,C,L,2,4 - xPB_B,N,N,L,C,L,2,5  = 0
 c410:  0.998 xPB_B,N,N,L,C,L,3,1 - xPB_B,N,N,L,C,L,3,2  = 0
 c411:  0.998 xPB_B,N,N,L,C,L,3,2 - xPB_B,N,N,L,C,L,3,3  = 0
 c412:  0.998 xPB_B,N,N,L,C,L,3,3 - xPB_B,N,N,L,C,L,3,4  = 0
 c413:  0.998 xPB_B,N,N,L,C,L,3,4 - xPB_B,N,N,L,C,L,3,5  = 0
 c414:  0.998 xPB_B,N,N,L,C,L,4,1 - xPB_B,N,N,L,C,L,4,2  = 0
 c415:  0.998 xPB_B,N,N,L,C,L,4,2 - xPB_B,N,N,L,C,L,4,3  = 0
 c416:  0.998 xPB_B,N,N,L,C,L,4,3 - xPB_B,N,N,L,C,L,4,4  = 0
 c417:  0.998 xPB_B,N,N,L,C,L,4,4 - xPB_B,N,N,L,C,L,4,5  = 0
 c418:  0.998 xPB_B,N,N,L,A,S,0,1 - xPB_B,N,N,L,A,S,0,2  = 0
 c419:  0.998 xPB_B,N,N,L,A,S,0,2 - xPB_B,N,N,L,A,S,0,3  = 0
 c420:  0.998 xPB_B,N,N,L,A,S,0,3 - xPB_B,N,N,L,A,S,0,4  = 0
 c421:  0.998 xPB_B,N,N,L,A,S,0,4 - xPB_B,N,N,L,A,S,0,5  = 0
 c422:  0.998 xPB_B,N,N,L,A,S,1,1 - xPB_B,N,N,L,A,S,1,2  = 0
 c423:  0.998 xPB_B,N,N,L,A,S,1,2 - xPB_B,N,N,L,A,S,1,3  = 0
 c424:  0.998 xPB_B,N,N,L,A,S,1,3 - xPB_B,N,N,L,A,S,1,4  = 0
 c425:  0.998 xPB_B,N,N,L,A,S,1,4 - xPB_B,N,N,L,A,S,1,5  = 0
 c426:  0.998 xPB_B,N,N,L,A,S,2,1 - xPB_B,N,N,L,A,S,2,2  = 0
 c427:  0.998 xPB_B,N,N,L,A,S,2,2 - xPB_B,N,N,L,A,S,2,3  = 0
 c428:  0.998 xPB_B,N,N,L,A,S,2,3 - xPB_B,N,N,L,A,S,2,4  = 0
 c429:  0.998 xPB_B,N,N,L,A,S,2,4 - xPB_B,N,N,L,A,S,2,5  = 0
 c430:  0.998 xPB_B,N,N,L,A,S,3,1 - xPB_B,N,N,L,A,S,3,2  = 0
 c431:  0.998 xPB_B,N,N,L,A,S,3,2 - xPB_B,N,N,L,A,S,3,3  = 0
 c432:  0.998 xPB_B,N,N,L,A,S,3,3 - xPB_B,N,N,L,A,S,3,4  = 0
 c433:  0.998 xPB_B,N,N,L,A,S,3,4 - xPB_B,N,N,L,A,S,3,5  = 0
 c434:  0.998 xPB_B,N,N,L,A,S,4,1 - xPB_B,N,N,L,A,S,4,2  = 0
 c435:  0.998 xPB_B,N,N,L,A,S,4,2 - xPB_B,N,N,L,A,S,4,3  = 0
 c436:  0.998 xPB_B,N,N,L,A,S,4,3 - xPB_B,N,N,L,A,S,4,4  = 0
 c437:  0.998 xPB_B,N,N,L,A,S,4,4 - xPB_B,N,N,L,A,S,4,5  = 0
 c438:  0.998 xPB_B,N,N,L,A,M,0,1 - xPB_B,N,N,L,A,M,0,2  = 0
 c439:  0.998 xPB_B,N,N,L,A,M,0,2 - xPB_B,N,N,L,A,M,0,3  = 0
 c440:  0.998 xPB_B,N,N,L,A,M,0,3 - xPB_B,N,N,L,A,M,0,4  = 0
 c441:  0.998 xPB_B,N,N,L,A,M,0,4 - xPB_B,N,N,L,A,M,0,5  = 0
 c442:  0.998 xPB_B,N,N,L,A,M,1,1 - xPB_B,N,N,L,A,M,1,2  = 0
 c443:  0.998 xPB_B,N,N,L,A,M,1,2 - xPB_B,N,N,L,A,M,1,3  = 0
 c444:  0.998 xPB_B,N,N,L,A,M,1,3 - xPB_B,N,N,L,A,M,1,4  = 0
 c445:  0.998 xPB_B,N,N,L,A,M,1,4 - xPB_B,N,N,L,A,M,1,5  = 0
 c446:  0.998 xPB_B,N,N,L,A,M,2,1 - xPB_B,N,N,L,A,M,2,2  = 0
 c447:  0.998 xPB_B,N,N,L,A,M,2,2 - xPB_B,N,N,L,A,M,2,3  = 0
 c448:  0.998 xPB_B,N,N,L,A,M,2,3 - xPB_B,N,N,L,A,M,2,4  = 0
 c449:  0.998 xPB_B,N,N,L,A,M,2,4 - xPB_B,N,N,L,A,M,2,5  = 0
 c450:  0.998 xPB_B,N,N,L,A,M,3,1 - xPB_B,N,N,L,A,M,3,2  = 0
 c451:  0.998 xPB_B,N,N,L,A,M,3,2 - xPB_B,N,N,L,A,M,3,3  = 0
 c452:  0.998 xPB_B,N,N,L,A,M,3,3 - xPB_B,N,N,L,A,M,3,4  = 0
 c453:  0.998 xPB_B,N,N,L,A,M,3,4 - xPB_B,N,N,L,A,M,3,5  = 0
 c454:  0.998 xPB_B,N,N,L,A,M,4,1 - xPB_B,N,N,L,A,M,4,2  = 0
 c455:  0.998 xPB_B,N,N,L,A,M,4,2 - xPB_B,N,N,L,A,M,4,3  = 0
 c456:  0.998 xPB_B,N,N,L,A,M,4,3 - xPB_B,N,N,L,A,M,4,4  = 0
 c457:  0.998 xPB_B,N,N,L,A,M,4,4 - xPB_B,N,N,L,A,M,4,5  = 0
 c458:  0.998 xPB_B,N,N,L,A,L,0,1 - xPB_B,N,N,L,A,L,0,2  = 0
 c459:  0.998 xPB_B,N,N,L,A,L,0,2 - xPB_B,N,N,L,A,L,0,3  = 0
 c460:  0.998 xPB_B,N,N,L,A,L,0,3 - xPB_B,N,N,L,A,L,0,4  = 0
 c461:  0.998 xPB_B,N,N,L,A,L,0,4 - xPB_B,N,N,L,A,L,0,5  = 0
 c462:  0.998 xPB_B,N,N,L,A,L,1,1 - xPB_B,N,N,L,A,L,1,2  = 0
 c463:  0.998 xPB_B,N,N,L,A,L,1,2 - xPB_B,N,N,L,A,L,1,3  = 0
 c464:  0.998 xPB_B,N,N,L,A,L,1,3 - xPB_B,N,N,L,A,L,1,4  = 0
 c465:  0.998 xPB_B,N,N,L,A,L,1,4 - xPB_B,N,N,L,A,L,1,5  = 0
 c466:  0.998 xPB_B,N,N,L,A,L,2,1 - xPB_B,N,N,L,A,L,2,2  = 0
 c467:  0.998 xPB_B,N,N,L,A,L,2,2 - xPB_B,N,N,L,A,L,2,3  = 0
 c468:  0.998 xPB_B,N,N,L,A,L,2,3 - xPB_B,N,N,L,A,L,2,4  = 0
 c469:  0.998 xPB_B,N,N,L,A,L,2,4 - xPB_B,N,N,L,A,L,2,5  = 0
 c470:  0.998 xPB_B,N,N,L,A,L,3,1 - xPB_B,N,N,L,A,L,3,2  = 0
 c471:  0.998 xPB_B,N,N,L,A,L,3,2 - xPB_B,N,N,L,A,L,3,3  = 0
 c472:  0.998 xPB_B,N,N,L,A,L,3,3 - xPB_B,N,N,L,A,L,3,4  = 0
 c473:  0.998 xPB_B,N,N,L,A,L,3,4 - xPB_B,N,N,L,A,L,3,5  = 0
 c474:  0.998 xPB_B,N,N,L,A,L,4,1 - xPB_B,N,N,L,A,L,4,2  = 0
 c475:  0.998 xPB_B,N,N,L,A,L,4,2 - xPB_B,N,N,L,A,L,4,3  = 0
 c476:  0.998 xPB_B,N,N,L,A,L,4,3 - xPB_B,N,N,L,A,L,4,4  = 0
 c477:  0.998 xPB_B,N,N,L,A,L,4,4 - xPB_B,N,N,L,A,L,4,5  = 0
 c478:  0.998 xPB_B,N,N,H,C,L,0,1 - xPB_B,N,N,H,C,L,0,2  = 0
 c479:  0.998 xPB_B,N,N,H,C,L,0,2 - xPB_B,N,N,H,C,L,0,3  = 0
 c480:  0.998 xPB_B,N,N,H,C,L,0,3 - xPB_B,N,N,H,C,L,0,4  = 0
 c481:  0.998 xPB_B,N,N,H,C,L,0,4 - xPB_B,N,N,H,C,L,0,5  = 0
 c482:  0.998 xPB_B,N,N,H,C,L,1,1 - xPB_B,N,N,H,C,L,1,2  = 0
 c483:  0.998 xPB_B,N,N,H,C,L,1,2 - xPB_B,N,N,H,C,L,1,3  = 0
 c484:  0.998 xPB_B,N,N,H,C,L,1,3 - xPB_B,N,N,H,C,L,1,4  = 0
 c485:  0.998 xPB_B,N,N,H,C,L,1,4 - xPB_B,N,N,H,C,L,1,5  = 0
 c486:  0.998 xPB_B,N,N,H,C,L,2,1 - xPB_B,N,N,H,C,L,2,2  = 0
 c487:  0.998 xPB_B,N,N,H,C,L,2,2 - xPB_B,N,N,H,C,L,2,3  = 0
 c488:  0.998 xPB_B,N,N,H,C,L,2,3 - xPB_B,N,N,H,C,L,2,4  = 0
 c489:  0.998 xPB_B,N,N,H,C,L,2,4 - xPB_B,N,N,H,C,L,2,5  = 0
 c490:  0.998 xPB_B,N,N,H,C,L,3,1 - xPB_B,N,N,H,C,L,3,2  = 0
 c491:  0.998 xPB_B,N,N,H,C,L,3,2 - xPB_B,N,N,H,C,L,3,3  = 0
 c492:  0.998 xPB_B,N,N,H,C,L,3,3 - xPB_B,N,N,H,C,L,3,4  = 0
 c493:  0.998 xPB_B,N,N,H,C,L,3,4 - xPB_B,N,N,H,C,L,3,5  = 0
 c494:  0.998 xPB_B,N,N,H,C,L,4,1 - xPB_B,N,N,H,C,L,4,2  = 0
 c495:  0.998 xPB_B,N,N,H,C,L,4,2 - xPB_B,N,N,H,C,L,4,3  = 0
 c496:  0.998 xPB_B,N,N,H,C,L,4,3 - xPB_B,N,N,H,C,L,4,4  = 0
 c497:  0.998 xPB_B,N,N,H,C,L,4,4 - xPB_B,N,N,H,C,L,4,5  = 0
 c498:  0.998 xPB_B,N,N,C,P,P,0,1 - xPB_B,N,N,C,P,P,0,2  = 0
 c499:  0.998 xPB_B,N,N,C,P,P,0,2 - xPB_B,N,N,C,P,P,0,3  = 0
 c500:  0.998 xPB_B,N,N,C,P,P,0,3 - xPB_B,N,N,C,P,P,0,4  = 0
 c501:  0.998 xPB_B,N,N,C,P,P,0,4 - xPB_B,N,N,C,P,P,0,5  = 0
 c502:  0.998 xPB_B,N,N,C,P,P,1,1 - xPB_B,N,N,C,P,P,1,2  = 0
 c503:  0.998 xPB_B,N,N,C,P,P,1,2 - xPB_B,N,N,C,P,P,1,3  = 0
 c504:  0.998 xPB_B,N,N,C,P,P,1,3 - xPB_B,N,N,C,P,P,1,4  = 0
 c505:  0.998 xPB_B,N,N,C,P,P,1,4 - xPB_B,N,N,C,P,P,1,5  = 0
 c506:  0.998 xPB_B,N,N,C,P,P,2,1 - xPB_B,N,N,C,P,P,2,2  = 0
 c507:  0.998 xPB_B,N,N,C,P,P,2,2 - xPB_B,N,N,C,P,P,2,3  = 0
 c508:  0.998 xPB_B,N,N,C,P,P,2,3 - xPB_B,N,N,C,P,P,2,4  = 0
 c509:  0.998 xPB_B,N,N,C,P,P,2,4 - xPB_B,N,N,C,P,P,2,5  = 0
 c510:  0.998 xPB_B,N,N,C,P,P,3,1 - xPB_B,N,N,C,P,P,3,2  = 0
 c511:  0.998 xPB_B,N,N,C,P,P,3,2 - xPB_B,N,N,C,P,P,3,3  = 0
 c512:  0.998 xPB_B,N,N,C,P,P,3,3 - xPB_B,N,N,C,P,P,3,4  = 0
 c513:  0.998 xPB_B,N,N,C,P,P,3,4 - xPB_B,N,N,C,P,P,3,5  = 0
 c514:  0.998 xPB_B,N,N,C,P,P,4,1 - xPB_B,N,N,C,P,P,4,2  = 0
 c515:  0.998 xPB_B,N,N,C,P,P,4,2 - xPB_B,N,N,C,P,P,4,3  = 0
 c516:  0.998 xPB_B,N,N,C,P,P,4,3 - xPB_B,N,N,C,P,P,4,4  = 0
 c517:  0.998 xPB_B,N,N,C,P,P,4,4 - xPB_B,N,N,C,P,P,4,5  = 0
 c518:  0.998 xPB_B,N,N,C,P,M,0,1 - xPB_B,N,N,C,P,M,0,2  = 0
 c519:  0.998 xPB_B,N,N,C,P,M,0,2 - xPB_B,N,N,C,P,M,0,3  = 0
 c520:  0.998 xPB_B,N,N,C,P,M,0,3 - xPB_B,N,N,C,P,M,0,4  = 0
 c521:  0.998 xPB_B,N,N,C,P,M,0,4 - xPB_B,N,N,C,P,M,0,5  = 0
 c522:  0.998 xPB_B,N,N,C,P,M,1,1 - xPB_B,N,N,C,P,M,1,2  = 0
 c523:  0.998 xPB_B,N,N,C,P,M,1,2 - xPB_B,N,N,C,P,M,1,3  = 0
 c524:  0.998 xPB_B,N,N,C,P,M,1,3 - xPB_B,N,N,C,P,M,1,4  = 0
 c525:  0.998 xPB_B,N,N,C,P,M,1,4 - xPB_B,N,N,C,P,M,1,5  = 0
 c526:  0.998 xPB_B,N,N,C,P,M,2,1 - xPB_B,N,N,C,P,M,2,2  = 0
 c527:  0.998 xPB_B,N,N,C,P,M,2,2 - xPB_B,N,N,C,P,M,2,3  = 0
 c528:  0.998 xPB_B,N,N,C,P,M,2,3 - xPB_B,N,N,C,P,M,2,4  = 0
 c529:  0.998 xPB_B,N,N,C,P,M,2,4 - xPB_B,N,N,C,P,M,2,5  = 0
 c530:  0.998 xPB_B,N,N,C,P,M,3,1 - xPB_B,N,N,C,P,M,3,2  = 0
 c531:  0.998 xPB_B,N,N,C,P,M,3,2 - xPB_B,N,N,C,P,M,3,3  = 0
 c532:  0.998 xPB_B,N,N,C,P,M,3,3 - xPB_B,N,N,C,P,M,3,4  = 0
 c533:  0.998 xPB_B,N,N,C,P,M,3,4 - xPB_B,N,N,C,P,M,3,5  = 0
 c534:  0.998 xPB_B,N,N,C,P,M,4,1 - xPB_B,N,N,C,P,M,4,2  = 0
 c535:  0.998 xPB_B,N,N,C,P,M,4,2 - xPB_B,N,N,C,P,M,4,3  = 0
 c536:  0.998 xPB_B,N,N,C,P,M,4,3 - xPB_B,N,N,C,P,M,4,4  = 0
 c537:  0.998 xPB_B,N,N,C,P,M,4,4 - xPB_B,N,N,C,P,M,4,5  = 0
 c538:  0.998 xPB_B,N,N,C,P,L,0,1 - xPB_B,N,N,C,P,L,0,2  = 0
 c539:  0.998 xPB_B,N,N,C,P,L,0,2 - xPB_B,N,N,C,P,L,0,3  = 0
 c540:  0.998 xPB_B,N,N,C,P,L,0,3 - xPB_B,N,N,C,P,L,0,4  = 0
 c541:  0.998 xPB_B,N,N,C,P,L,0,4 - xPB_B,N,N,C,P,L,0,5  = 0
 c542:  0.998 xPB_B,N,N,C,P,L,1,1 - xPB_B,N,N,C,P,L,1,2  = 0
 c543:  0.998 xPB_B,N,N,C,P,L,1,2 - xPB_B,N,N,C,P,L,1,3  = 0
 c544:  0.998 xPB_B,N,N,C,P,L,1,3 - xPB_B,N,N,C,P,L,1,4  = 0
 c545:  0.998 xPB_B,N,N,C,P,L,1,4 - xPB_B,N,N,C,P,L,1,5  = 0
 c546:  0.998 xPB_B,N,N,C,P,L,2,1 - xPB_B,N,N,C,P,L,2,2  = 0
 c547:  0.998 xPB_B,N,N,C,P,L,2,2 - xPB_B,N,N,C,P,L,2,3  = 0
 c548:  0.998 xPB_B,N,N,C,P,L,2,3 - xPB_B,N,N,C,P,L,2,4  = 0
 c549:  0.998 xPB_B,N,N,C,P,L,2,4 - xPB_B,N,N,C,P,L,2,5  = 0
 c550:  0.998 xPB_B,N,N,C,P,L,3,1 - xPB_B,N,N,C,P,L,3,2  = 0
 c551:  0.998 xPB_B,N,N,C,P,L,3,2 - xPB_B,N,N,C,P,L,3,3  = 0
 c552:  0.998 xPB_B,N,N,C,P,L,3,3 - xPB_B,N,N,C,P,L,3,4  = 0
 c553:  0.998 xPB_B,N,N,C,P,L,3,4 - xPB_B,N,N,C,P,L,3,5  = 0
 c554:  0.998 xPB_B,N,N,C,P,L,4,1 - xPB_B,N,N,C,P,L,4,2  = 0
 c555:  0.998 xPB_B,N,N,C,P,L,4,2 - xPB_B,N,N,C,P,L,4,3  = 0
 c556:  0.998 xPB_B,N,N,C,P,L,4,3 - xPB_B,N,N,C,P,L,4,4  = 0
 c557:  0.998 xPB_B,N,N,C,P,L,4,4 - xPB_B,N,N,C,P,L,4,5  = 0
 c558:  0.998 xPB_B,N,N,C,D,S,0,1 - xPB_B,N,N,C,D,S,0,2  = 0
 c559:  0.998 xPB_B,N,N,C,D,S,0,2 - xPB_B,N,N,C,D,S,0,3  = 0
 c560:  0.998 xPB_B,N,N,C,D,S,0,3 - xPB_B,N,N,C,D,S,0,4  = 0
 c561:  0.998 xPB_B,N,N,C,D,S,0,4 - xPB_B,N,N,C,D,S,0,5  = 0
 c562:  0.998 xPB_B,N,N,C,D,S,1,1 - xPB_B,N,N,C,D,S,1,2  = 0
 c563:  0.998 xPB_B,N,N,C,D,S,1,2 - xPB_B,N,N,C,D,S,1,3  = 0
 c564:  0.998 xPB_B,N,N,C,D,S,1,3 - xPB_B,N,N,C,D,S,1,4  = 0
 c565:  0.998 xPB_B,N,N,C,D,S,1,4 - xPB_B,N,N,C,D,S,1,5  = 0
 c566:  0.998 xPB_B,N,N,C,D,S,2,1 - xPB_B,N,N,C,D,S,2,2  = 0
 c567:  0.998 xPB_B,N,N,C,D,S,2,2 - xPB_B,N,N,C,D,S,2,3  = 0
 c568:  0.998 xPB_B,N,N,C,D,S,2,3 - xPB_B,N,N,C,D,S,2,4  = 0
 c569:  0.998 xPB_B,N,N,C,D,S,2,4 - xPB_B,N,N,C,D,S,2,5  = 0
 c570:  0.998 xPB_B,N,N,C,D,S,3,1 - xPB_B,N,N,C,D,S,3,2  = 0
 c571:  0.998 xPB_B,N,N,C,D,S,3,2 - xPB_B,N,N,C,D,S,3,3  = 0
 c572:  0.998 xPB_B,N,N,C,D,S,3,3 - xPB_B,N,N,C,D,S,3,4  = 0
 c573:  0.998 xPB_B,N,N,C,D,S,3,4 - xPB_B,N,N,C,D,S,3,5  = 0
 c574:  0.998 xPB_B,N,N,C,D,S,4,1 - xPB_B,N,N,C,D,S,4,2  = 0
 c575:  0.998 xPB_B,N,N,C,D,S,4,2 - xPB_B,N,N,C,D,S,4,3  = 0
 c576:  0.998 xPB_B,N,N,C,D,S,4,3 - xPB_B,N,N,C,D,S,4,4  = 0
 c577:  0.998 xPB_B,N,N,C,D,S,4,4 - xPB_B,N,N,C,D,S,4,5  = 0
 c578:  0.998 xPB_B,N,N,C,D,P,0,1 - xPB_B,N,N,C,D,P,0,2  = 0
 c579:  0.998 xPB_B,N,N,C,D,P,0,2 - xPB_B,N,N,C,D,P,0,3  = 0
 c580:  0.998 xPB_B,N,N,C,D,P,0,3 - xPB_B,N,N,C,D,P,0,4  = 0
 c581:  0.998 xPB_B,N,N,C,D,P,0,4 - xPB_B,N,N,C,D,P,0,5  = 0
 c582:  0.998 xPB_B,N,N,C,D,P,1,1 - xPB_B,N,N,C,D,P,1,2  = 0
 c583:  0.998 xPB_B,N,N,C,D,P,1,2 - xPB_B,N,N,C,D,P,1,3  = 0
 c584:  0.998 xPB_B,N,N,C,D,P,1,3 - xPB_B,N,N,C,D,P,1,4  = 0
 c585:  0.998 xPB_B,N,N,C,D,P,1,4 - xPB_B,N,N,C,D,P,1,5  = 0
 c586:  0.998 xPB_B,N,N,C,D,P,2,1 - xPB_B,N,N,C,D,P,2,2  = 0
 c587:  0.998 xPB_B,N,N,C,D,P,2,2 - xPB_B,N,N,C,D,P,2,3  = 0
 c588:  0.998 xPB_B,N,N,C,D,P,2,3 - xPB_B,N,N,C,D,P,2,4  = 0
 c589:  0.998 xPB_B,N,N,C,D,P,2,4 - xPB_B,N,N,C,D,P,2,5  = 0
 c590:  0.998 xPB_B,N,N,C,D,P,3,1 - xPB_B,N,N,C,D,P,3,2  = 0
 c591:  0.998 xPB_B,N,N,C,D,P,3,2 - xPB_B,N,N,C,D,P,3,3  = 0
 c592:  0.998 xPB_B,N,N,C,D,P,3,3 - xPB_B,N,N,C,D,P,3,4  = 0
 c593:  0.998 xPB_B,N,N,C,D,P,3,4 - xPB_B,N,N,C,D,P,3,5  = 0
 c594:  0.998 xPB_B,N,N,C,D,P,4,1 - xPB_B,N,N,C,D,P,4,2  = 0
 c595:  0.998 xPB_B,N,N,C,D,P,4,2 - xPB_B,N,N,C,D,P,4,3  = 0
 c596:  0.998 xPB_B,N,N,C,D,P,4,3 - xPB_B,N,N,C,D,P,4,4  = 0
 c597:  0.998 xPB_B,N,N,C,D,P,4,4 - xPB_B,N,N,C,D,P,4,5  = 0
 c598:  0.998 xPB_B,N,N,C,D,M,0,1 - xPB_B,N,N,C,D,M,0,2  = 0
 c599:  0.998 xPB_B,N,N,C,D,M,0,2 - xPB_B,N,N,C,D,M,0,3  = 0
 c600:  0.998 xPB_B,N,N,C,D,M,0,3 - xPB_B,N,N,C,D,M,0,4  = 0
 c601:  0.998 xPB_B,N,N,C,D,M,0,4 - xPB_B,N,N,C,D,M,0,5  = 0
 c602:  0.998 xPB_B,N,N,C,D,M,1,1 - xPB_B,N,N,C,D,M,1,2  = 0
 c603:  0.998 xPB_B,N,N,C,D,M,1,2 - xPB_B,N,N,C,D,M,1,3  = 0
 c604:  0.998 xPB_B,N,N,C,D,M,1,3 - xPB_B,N,N,C,D,M,1,4  = 0
 c605:  0.998 xPB_B,N,N,C,D,M,1,4 - xPB_B,N,N,C,D,M,1,5  = 0
 c606:  0.998 xPB_B,N,N,C,D,M,2,1 - xPB_B,N,N,C,D,M,2,2  = 0
 c607:  0.998 xPB_B,N,N,C,D,M,2,2 - xPB_B,N,N,C,D,M,2,3  = 0
 c608:  0.998 xPB_B,N,N,C,D,M,2,3 - xPB_B,N,N,C,D,M,2,4  = 0
 c609:  0.998 xPB_B,N,N,C,D,M,2,4 - xPB_B,N,N,C,D,M,2,5  = 0
 c610:  0.998 xPB_B,N,N,C,D,M,3,1 - xPB_B,N,N,C,D,M,3,2  = 0
 c611:  0.998 xPB_B,N,N,C,D,M,3,2 - xPB_B,N,N,C,D,M,3,3  = 0
 c612:  0.998 xPB_B,N,N,C,D,M,3,3 - xPB_B,N,N,C,D,M,3,4  = 0
 c613:  0.998 xPB_B,N,N,C,D,M,3,4 - xPB_B,N,N,C,D,M,3,5  = 0
 c614:  0.998 xPB_B,N,N,C,D,M,4,1 - xPB_B,N,N,C,D,M,4,2  = 0
 c615:  0.998 xPB_B,N,N,C,D,M,4,2 - xPB_B,N,N,C,D,M,4,3  = 0
 c616:  0.998 xPB_B,N,N,C,D,M,4,3 - xPB_B,N,N,C,D,M,4,4  = 0
 c617:  0.998 xPB_B,N,N,C,D,M,4,4 - xPB_B,N,N,C,D,M,4,5  = 0
 c618:  0.998 xPB_B,N,N,C,D,L,0,1 - xPB_B,N,N,C,D,L,0,2  = 0
 c619:  0.998 xPB_B,N,N,C,D,L,0,2 - xPB_B,N,N,C,D,L,0,3  = 0
 c620:  0.998 xPB_B,N,N,C,D,L,0,3 - xPB_B,N,N,C,D,L,0,4  = 0
 c621:  0.998 xPB_B,N,N,C,D,L,0,4 - xPB_B,N,N,C,D,L,0,5  = 0
 c622:  0.998 xPB_B,N,N,C,D,L,1,1 - xPB_B,N,N,C,D,L,1,2  = 0
 c623:  0.998 xPB_B,N,N,C,D,L,1,2 - xPB_B,N,N,C,D,L,1,3  = 0
 c624:  0.998 xPB_B,N,N,C,D,L,1,3 - xPB_B,N,N,C,D,L,1,4  = 0
 c625:  0.998 xPB_B,N,N,C,D,L,1,4 - xPB_B,N,N,C,D,L,1,5  = 0
 c626:  0.998 xPB_B,N,N,C,D,L,2,1 - xPB_B,N,N,C,D,L,2,2  = 0
 c627:  0.998 xPB_B,N,N,C,D,L,2,2 - xPB_B,N,N,C,D,L,2,3  = 0
 c628:  0.998 xPB_B,N,N,C,D,L,2,3 - xPB_B,N,N,C,D,L,2,4  = 0
 c629:  0.998 xPB_B,N,N,C,D,L,2,4 - xPB_B,N,N,C,D,L,2,5  = 0
 c630:  0.998 xPB_B,N,N,C,D,L,3,1 - xPB_B,N,N,C,D,L,3,2  = 0
 c631:  0.998 xPB_B,N,N,C,D,L,3,2 - xPB_B,N,N,C,D,L,3,3  = 0
 c632:  0.998 xPB_B,N,N,C,D,L,3,3 - xPB_B,N,N,C,D,L,3,4  = 0
 c633:  0.998 xPB_B,N,N,C,D,L,3,4 - xPB_B,N,N,C,D,L,3,5  = 0
 c634:  0.998 xPB_B,N,N,C,D,L,4,1 - xPB_B,N,N,C,D,L,4,2  = 0
 c635:  0.998 xPB_B,N,N,C,D,L,4,2 - xPB_B,N,N,C,D,L,4,3  = 0
 c636:  0.998 xPB_B,N,N,C,D,L,4,3 - xPB_B,N,N,C,D,L,4,4  = 0
 c637:  0.998 xPB_B,N,N,C,D,L,4,4 - xPB_B,N,N,C,D,L,4,5  = 0
 c638:  0.998 xPB_B,N,N,C,W,S,0,1 - xPB_B,N,N,C,W,S,0,2  = 0
 c639:  0.998 xPB_B,N,N,C,W,S,0,2 - xPB_B,N,N,C,W,S,0,3  = 0
 c640:  0.998 xPB_B,N,N,C,W,S,0,3 - xPB_B,N,N,C,W,S,0,4  = 0
 c641:  0.998 xPB_B,N,N,C,W,S,0,4 - xPB_B,N,N,C,W,S,0,5  = 0
 c642:  0.998 xPB_B,N,N,C,W,S,1,1 - xPB_B,N,N,C,W,S,1,2  = 0
 c643:  0.998 xPB_B,N,N,C,W,S,1,2 - xPB_B,N,N,C,W,S,1,3  = 0
 c644:  0.998 xPB_B,N,N,C,W,S,1,3 - xPB_B,N,N,C,W,S,1,4  = 0
 c645:  0.998 xPB_B,N,N,C,W,S,1,4 - xPB_B,N,N,C,W,S,1,5  = 0
 c646:  0.998 xPB_B,N,N,C,W,S,2,1 - xPB_B,N,N,C,W,S,2,2  = 0
 c647:  0.998 xPB_B,N,N,C,W,S,2,2 - xPB_B,N,N,C,W,S,2,3  = 0
 c648:  0.998 xPB_B,N,N,C,W,S,2,3 - xPB_B,N,N,C,W,S,2,4  = 0
 c649:  0.998 xPB_B,N,N,C,W,S,2,4 - xPB_B,N,N,C,W,S,2,5  = 0
 c650:  0.998 xPB_B,N,N,C,W,S,3,1 - xPB_B,N,N,C,W,S,3,2  = 0
 c651:  0.998 xPB_B,N,N,C,W,S,3,2 - xPB_B,N,N,C,W,S,3,3  = 0
 c652:  0.998 xPB_B,N,N,C,W,S,3,3 - xPB_B,N,N,C,W,S,3,4  = 0
 c653:  0.998 xPB_B,N,N,C,W,S,3,4 - xPB_B,N,N,C,W,S,3,5  = 0
 c654:  0.998 xPB_B,N,N,C,W,S,4,1 - xPB_B,N,N,C,W,S,4,2  = 0
 c655:  0.998 xPB_B,N,N,C,W,S,4,2 - xPB_B,N,N,C,W,S,4,3  = 0
 c656:  0.998 xPB_B,N,N,C,W,S,4,3 - xPB_B,N,N,C,W,S,4,4  = 0
 c657:  0.998 xPB_B,N,N,C,W,S,4,4 - xPB_B,N,N,C,W,S,4,5  = 0
 c658:  0.998 xPB_B,N,N,C,W,P,0,1 - xPB_B,N,N,C,W,P,0,2  = 0
 c659:  0.998 xPB_B,N,N,C,W,P,0,2 - xPB_B,N,N,C,W,P,0,3  = 0
 c660:  0.998 xPB_B,N,N,C,W,P,0,3 - xPB_B,N,N,C,W,P,0,4  = 0
 c661:  0.998 xPB_B,N,N,C,W,P,0,4 - xPB_B,N,N,C,W,P,0,5  = 0
 c662:  0.998 xPB_B,N,N,C,W,P,1,1 - xPB_B,N,N,C,W,P,1,2  = 0
 c663:  0.998 xPB_B,N,N,C,W,P,1,2 - xPB_B,N,N,C,W,P,1,3  = 0
 c664:  0.998 xPB_B,N,N,C,W,P,1,3 - xPB_B,N,N,C,W,P,1,4  = 0
 c665:  0.998 xPB_B,N,N,C,W,P,1,4 - xPB_B,N,N,C,W,P,1,5  = 0
 c666:  0.998 xPB_B,N,N,C,W,P,2,1 - xPB_B,N,N,C,W,P,2,2  = 0
 c667:  0.998 xPB_B,N,N,C,W,P,2,2 - xPB_B,N,N,C,W,P,2,3  = 0
 c668:  0.998 xPB_B,N,N,C,W,P,2,3 - xPB_B,N,N,C,W,P,2,4  = 0
 c669:  0.998 xPB_B,N,N,C,W,P,2,4 - xPB_B,N,N,C,W,P,2,5  = 0
 c670:  0.998 xPB_B,N,N,C,W,P,3,1 - xPB_B,N,N,C,W,P,3,2  = 0
 c671:  0.998 xPB_B,N,N,C,W,P,3,2 - xPB_B,N,N,C,W,P,3,3  = 0
 c672:  0.998 xPB_B,N,N,C,W,P,3,3 - xPB_B,N,N,C,W,P,3,4  = 0
 c673:  0.998 xPB_B,N,N,C,W,P,3,4 - xPB_B,N,N,C,W,P,3,5  = 0
 c674:  0.998 xPB_B,N,N,C,W,P,4,1 - xPB_B,N,N,C,W,P,4,2  = 0
 c675:  0.998 xPB_B,N,N,C,W,P,4,2 - xPB_B,N,N,C,W,P,4,3  = 0
 c676:  0.998 xPB_B,N,N,C,W,P,4,3 - xPB_B,N,N,C,W,P,4,4  = 0
 c677:  0.998 xPB_B,N,N,C,W,P,4,4 - xPB_B,N,N,C,W,P,4,5  = 0
 c678:  0.998 xPB_B,N,N,C,W,M,0,1 - xPB_B,N,N,C,W,M,0,2  = 0
 c679:  0.998 xPB_B,N,N,C,W,M,0,2 - xPB_B,N,N,C,W,M,0,3  = 0
 c680:  0.998 xPB_B,N,N,C,W,M,0,3 - xPB_B,N,N,C,W,M,0,4  = 0
 c681:  0.998 xPB_B,N,N,C,W,M,0,4 - xPB_B,N,N,C,W,M,0,5  = 0
 c682:  0.998 xPB_B,N,N,C,W,M,1,1 - xPB_B,N,N,C,W,M,1,2  = 0
 c683:  0.998 xPB_B,N,N,C,W,M,1,2 - xPB_B,N,N,C,W,M,1,3  = 0
 c684:  0.998 xPB_B,N,N,C,W,M,1,3 - xPB_B,N,N,C,W,M,1,4  = 0
 c685:  0.998 xPB_B,N,N,C,W,M,1,4 - xPB_B,N,N,C,W,M,1,5  = 0
 c686:  0.998 xPB_B,N,N,C,W,M,2,1 - xPB_B,N,N,C,W,M,2,2  = 0
 c687:  0.998 xPB_B,N,N,C,W,M,2,2 - xPB_B,N,N,C,W,M,2,3  = 0
 c688:  0.998 xPB_B,N,N,C,W,M,2,3 - xPB_B,N,N,C,W,M,2,4  = 0
 c689:  0.998 xPB_B,N,N,C,W,M,2,4 - xPB_B,N,N,C,W,M,2,5  = 0
 c690:  0.998 xPB_B,N,N,C,W,M,3,1 - xPB_B,N,N,C,W,M,3,2  = 0
 c691:  0.998 xPB_B,N,N,C,W,M,3,2 - xPB_B,N,N,C,W,M,3,3  = 0
 c692:  0.998 xPB_B,N,N,C,W,M,3,3 - xPB_B,N,N,C,W,M,3,4  = 0
 c693:  0.998 xPB_B,N,N,C,W,M,3,4 - xPB_B,N,N,C,W,M,3,5  = 0
 c694:  0.998 xPB_B,N,N,C,W,M,4,1 - xPB_B,N,N,C,W,M,4,2  = 0
 c695:  0.998 xPB_B,N,N,C,W,M,4,2 - xPB_B,N,N,C,W,M,4,3  = 0
 c696:  0.998 xPB_B,N,N,C,W,M,4,3 - xPB_B,N,N,C,W,M,4,4  = 0
 c697:  0.998 xPB_B,N,N,C,W,M,4,4 - xPB_B,N,N,C,W,M,4,5  = 0
 c698:  0.998 xPB_B,N,N,C,W,L,0,1 - xPB_B,N,N,C,W,L,0,2  = 0
 c699:  0.998 xPB_B,N,N,C,W,L,0,2 - xPB_B,N,N,C,W,L,0,3  = 0
 c700:  0.998 xPB_B,N,N,C,W,L,0,3 - xPB_B,N,N,C,W,L,0,4  = 0
 c701:  0.998 xPB_B,N,N,C,W,L,0,4 - xPB_B,N,N,C,W,L,0,5  = 0
 c702:  0.998 xPB_B,N,N,C,W,L,1,1 - xPB_B,N,N,C,W,L,1,2  = 0
 c703:  0.998 xPB_B,N,N,C,W,L,1,2 - xPB_B,N,N,C,W,L,1,3  = 0
 c704:  0.998 xPB_B,N,N,C,W,L,1,3 - xPB_B,N,N,C,W,L,1,4  = 0
 c705:  0.998 xPB_B,N,N,C,W,L,1,4 - xPB_B,N,N,C,W,L,1,5  = 0
 c706:  0.998 xPB_B,N,N,C,W,L,2,1 - xPB_B,N,N,C,W,L,2,2  = 0
 c707:  0.998 xPB_B,N,N,C,W,L,2,2 - xPB_B,N,N,C,W,L,2,3  = 0
 c708:  0.998 xPB_B,N,N,C,W,L,2,3 - xPB_B,N,N,C,W,L,2,4  = 0
 c709:  0.998 xPB_B,N,N,C,W,L,2,4 - xPB_B,N,N,C,W,L,2,5  = 0
 c710:  0.998 xPB_B,N,N,C,W,L,3,1 - xPB_B,N,N,C,W,L,3,2  = 0
 c711:  0.998 xPB_B,N,N,C,W,L,3,2 - xPB_B,N,N,C,W,L,3,3  = 0
 c712:  0.998 xPB_B,N,N,C,W,L,3,3 - xPB_B,N,N,C,W,L,3,4  = 0
 c713:  0.998 xPB_B,N,N,C,W,L,3,4 - xPB_B,N,N,C,W,L,3,5  = 0
 c714:  0.998 xPB_B,N,N,C,W,L,4,1 - xPB_B,N,N,C,W,L,4,2  = 0
 c715:  0.998 xPB_B,N,N,C,W,L,4,2 - xPB_B,N,N,C,W,L,4,3  = 0
 c716:  0.998 xPB_B,N,N,C,W,L,4,3 - xPB_B,N,N,C,W,L,4,4  = 0
 c717:  0.998 xPB_B,N,N,C,W,L,4,4 - xPB_B,N,N,C,W,L,4,5  = 0
 c718:  0.998 xPB_B,N,N,C,C,S,0,1 - xPB_B,N,N,C,C,S,0,2  = 0
 c719:  0.998 xPB_B,N,N,C,C,S,0,2 - xPB_B,N,N,C,C,S,0,3  = 0
 c720:  0.998 xPB_B,N,N,C,C,S,0,3 - xPB_B,N,N,C,C,S,0,4  = 0
 c721:  0.998 xPB_B,N,N,C,C,S,0,4 - xPB_B,N,N,C,C,S,0,5  = 0
 c722:  0.998 xPB_B,N,N,C,C,S,1,1 - xPB_B,N,N,C,C,S,1,2  = 0
 c723:  0.998 xPB_B,N,N,C,C,S,1,2 - xPB_B,N,N,C,C,S,1,3  = 0
 c724:  0.998 xPB_B,N,N,C,C,S,1,3 - xPB_B,N,N,C,C,S,1,4  = 0
 c725:  0.998 xPB_B,N,N,C,C,S,1,4 - xPB_B,N,N,C,C,S,1,5  = 0
 c726:  0.998 xPB_B,N,N,C,C,S,2,1 - xPB_B,N,N,C,C,S,2,2  = 0
 c727:  0.998 xPB_B,N,N,C,C,S,2,2 - xPB_B,N,N,C,C,S,2,3  = 0
 c728:  0.998 xPB_B,N,N,C,C,S,2,3 - xPB_B,N,N,C,C,S,2,4  = 0
 c729:  0.998 xPB_B,N,N,C,C,S,2,4 - xPB_B,N,N,C,C,S,2,5  = 0
 c730:  0.998 xPB_B,N,N,C,C,S,3,1 - xPB_B,N,N,C,C,S,3,2  = 0
 c731:  0.998 xPB_B,N,N,C,C,S,3,2 - xPB_B,N,N,C,C,S,3,3  = 0
 c732:  0.998 xPB_B,N,N,C,C,S,3,3 - xPB_B,N,N,C,C,S,3,4  = 0
 c733:  0.998 xPB_B,N,N,C,C,S,3,4 - xPB_B,N,N,C,C,S,3,5  = 0
 c734:  0.998 xPB_B,N,N,C,C,S,4,1 - xPB_B,N,N,C,C,S,4,2  = 0
 c735:  0.998 xPB_B,N,N,C,C,S,4,2 - xPB_B,N,N,C,C,S,4,3  = 0
 c736:  0.998 xPB_B,N,N,C,C,S,4,3 - xPB_B,N,N,C,C,S,4,4  = 0
 c737:  0.998 xPB_B,N,N,C,C,S,4,4 - xPB_B,N,N,C,C,S,4,5  = 0
 c738:  0.998 xPB_B,N,N,C,C,P,0,1 - xPB_B,N,N,C,C,P,0,2  = 0
 c739:  0.998 xPB_B,N,N,C,C,P,0,2 - xPB_B,N,N,C,C,P,0,3  = 0
 c740:  0.998 xPB_B,N,N,C,C,P,0,3 - xPB_B,N,N,C,C,P,0,4  = 0
 c741:  0.998 xPB_B,N,N,C,C,P,0,4 - xPB_B,N,N,C,C,P,0,5  = 0
 c742:  0.998 xPB_B,N,N,C,C,P,1,1 - xPB_B,N,N,C,C,P,1,2  = 0
 c743:  0.998 xPB_B,N,N,C,C,P,1,2 - xPB_B,N,N,C,C,P,1,3  = 0
 c744:  0.998 xPB_B,N,N,C,C,P,1,3 - xPB_B,N,N,C,C,P,1,4  = 0
 c745:  0.998 xPB_B,N,N,C,C,P,1,4 - xPB_B,N,N,C,C,P,1,5  = 0
 c746:  0.998 xPB_B,N,N,C,C,P,2,1 - xPB_B,N,N,C,C,P,2,2  = 0
 c747:  0.998 xPB_B,N,N,C,C,P,2,2 - xPB_B,N,N,C,C,P,2,3  = 0
 c748:  0.998 xPB_B,N,N,C,C,P,2,3 - xPB_B,N,N,C,C,P,2,4  = 0
 c749:  0.998 xPB_B,N,N,C,C,P,2,4 - xPB_B,N,N,C,C,P,2,5  = 0
 c750:  0.998 xPB_B,N,N,C,C,P,3,1 - xPB_B,N,N,C,C,P,3,2  = 0
 c751:  0.998 xPB_B,N,N,C,C,P,3,2 - xPB_B,N,N,C,C,P,3,3  = 0
 c752:  0.998 xPB_B,N,N,C,C,P,3,3 - xPB_B,N,N,C,C,P,3,4  = 0
 c753:  0.998 xPB_B,N,N,C,C,P,3,4 - xPB_B,N,N,C,C,P,3,5  = 0
 c754:  0.998 xPB_B,N,N,C,C,P,4,1 - xPB_B,N,N,C,C,P,4,2  = 0
 c755:  0.998 xPB_B,N,N,C,C,P,4,2 - xPB_B,N,N,C,C,P,4,3  = 0
 c756:  0.998 xPB_B,N,N,C,C,P,4,3 - xPB_B,N,N,C,C,P,4,4  = 0
 c757:  0.998 xPB_B,N,N,C,C,P,4,4 - xPB_B,N,N,C,C,P,4,5  = 0
 c758:  0.998 xPB_B,N,N,C,C,M,0,1 - xPB_B,N,N,C,C,M,0,2  = 0
 c759:  0.998 xPB_B,N,N,C,C,M,0,2 - xPB_B,N,N,C,C,M,0,3  = 0
 c760:  0.998 xPB_B,N,N,C,C,M,0,3 - xPB_B,N,N,C,C,M,0,4  = 0
 c761:  0.998 xPB_B,N,N,C,C,M,0,4 - xPB_B,N,N,C,C,M,0,5  = 0
 c762:  0.998 xPB_B,N,N,C,C,M,1,1 - xPB_B,N,N,C,C,M,1,2  = 0
 c763:  0.998 xPB_B,N,N,C,C,M,1,2 - xPB_B,N,N,C,C,M,1,3  = 0
 c764:  0.998 xPB_B,N,N,C,C,M,1,3 - xPB_B,N,N,C,C,M,1,4  = 0
 c765:  0.998 xPB_B,N,N,C,C,M,1,4 - xPB_B,N,N,C,C,M,1,5  = 0
 c766:  0.998 xPB_B,N,N,C,C,M,2,1 - xPB_B,N,N,C,C,M,2,2  = 0
 c767:  0.998 xPB_B,N,N,C,C,M,2,2 - xPB_B,N,N,C,C,M,2,3  = 0
 c768:  0.998 xPB_B,N,N,C,C,M,2,3 - xPB_B,N,N,C,C,M,2,4  = 0
 c769:  0.998 xPB_B,N,N,C,C,M,2,4 - xPB_B,N,N,C,C,M,2,5  = 0
 c770:  0.998 xPB_B,N,N,C,C,M,3,1 - xPB_B,N,N,C,C,M,3,2  = 0
 c771:  0.998 xPB_B,N,N,C,C,M,3,2 - xPB_B,N,N,C,C,M,3,3  = 0
 c772:  0.998 xPB_B,N,N,C,C,M,3,3 - xPB_B,N,N,C,C,M,3,4  = 0
 c773:  0.998 xPB_B,N,N,C,C,M,3,4 - xPB_B,N,N,C,C,M,3,5  = 0
 c774:  0.998 xPB_B,N,N,C,C,M,4,1 - xPB_B,N,N,C,C,M,4,2  = 0
 c775:  0.998 xPB_B,N,N,C,C,M,4,2 - xPB_B,N,N,C,C,M,4,3  = 0
 c776:  0.998 xPB_B,N,N,C,C,M,4,3 - xPB_B,N,N,C,C,M,4,4  = 0
 c777:  0.998 xPB_B,N,N,C,C,M,4,4 - xPB_B,N,N,C,C,M,4,5  = 0
 c778:  0.998 xPB_B,N,N,C,C,L,0,1 - xPB_B,N,N,C,C,L,0,2  = 0
 c779:  0.998 xPB_B,N,N,C,C,L,0,2 - xPB_B,N,N,C,C,L,0,3  = 0
 c780:  0.998 xPB_B,N,N,C,C,L,0,3 - xPB_B,N,N,C,C,L,0,4  = 0
 c781:  0.998 xPB_B,N,N,C,C,L,0,4 - xPB_B,N,N,C,C,L,0,5  = 0
 c782:  0.998 xPB_B,N,N,C,C,L,1,1 - xPB_B,N,N,C,C,L,1,2  = 0
 c783:  0.998 xPB_B,N,N,C,C,L,1,2 - xPB_B,N,N,C,C,L,1,3  = 0
 c784:  0.998 xPB_B,N,N,C,C,L,1,3 - xPB_B,N,N,C,C,L,1,4  = 0
 c785:  0.998 xPB_B,N,N,C,C,L,1,4 - xPB_B,N,N,C,C,L,1,5  = 0
 c786:  0.998 xPB_B,N,N,C,C,L,2,1 - xPB_B,N,N,C,C,L,2,2  = 0
 c787:  0.998 xPB_B,N,N,C,C,L,2,2 - xPB_B,N,N,C,C,L,2,3  = 0
 c788:  0.998 xPB_B,N,N,C,C,L,2,3 - xPB_B,N,N,C,C,L,2,4  = 0
 c789:  0.998 xPB_B,N,N,C,C,L,2,4 - xPB_B,N,N,C,C,L,2,5  = 0
 c790:  0.998 xPB_B,N,N,C,C,L,3,1 - xPB_B,N,N,C,C,L,3,2  = 0
 c791:  0.998 xPB_B,N,N,C,C,L,3,2 - xPB_B,N,N,C,C,L,3,3  = 0
 c792:  0.998 xPB_B,N,N,C,C,L,3,3 - xPB_B,N,N,C,C,L,3,4  = 0
 c793:  0.998 xPB_B,N,N,C,C,L,3,4 - xPB_B,N,N,C,C,L,3,5  = 0
 c794:  0.998 xPB_B,N,N,C,C,L,4,1 - xPB_B,N,N,C,C,L,4,2  = 0
 c795:  0.998 xPB_B,N,N,C,C,L,4,2 - xPB_B,N,N,C,C,L,4,3  = 0
 c796:  0.998 xPB_B,N,N,C,C,L,4,3 - xPB_B,N,N,C,C,L,4,4  = 0
 c797:  0.998 xPB_B,N,N,C,C,L,4,4 - xPB_B,N,N,C,C,L,4,5  = 0
 c798:  0.998 xPB_B,N,N,C,I,M,0,1 - xPB_B,N,N,C,I,M,0,2  = 0
 c799:  0.998 xPB_B,N,N,C,I,M,0,2 - xPB_B,N,N,C,I,M,0,3  = 0
 c800:  0.998 xPB_B,N,N,C,I,M,0,3 - xPB_B,N,N,C,I,M,0,4  = 0
 c801:  0.998 xPB_B,N,N,C,I,M,0,4 - xPB_B,N,N,C,I,M,0,5  = 0
 c802:  0.998 xPB_B,N,N,C,I,M,1,1 - xPB_B,N,N,C,I,M,1,2  = 0
 c803:  0.998 xPB_B,N,N,C,I,M,1,2 - xPB_B,N,N,C,I,M,1,3  = 0
 c804:  0.998 xPB_B,N,N,C,I,M,1,3 - xPB_B,N,N,C,I,M,1,4  = 0
 c805:  0.998 xPB_B,N,N,C,I,M,1,4 - xPB_B,N,N,C,I,M,1,5  = 0
 c806:  0.998 xPB_B,N,N,C,I,M,2,1 - xPB_B,N,N,C,I,M,2,2  = 0
 c807:  0.998 xPB_B,N,N,C,I,M,2,2 - xPB_B,N,N,C,I,M,2,3  = 0
 c808:  0.998 xPB_B,N,N,C,I,M,2,3 - xPB_B,N,N,C,I,M,2,4  = 0
 c809:  0.998 xPB_B,N,N,C,I,M,2,4 - xPB_B,N,N,C,I,M,2,5  = 0
 c810:  0.998 xPB_B,N,N,C,I,M,3,1 - xPB_B,N,N,C,I,M,3,2  = 0
 c811:  0.998 xPB_B,N,N,C,I,M,3,2 - xPB_B,N,N,C,I,M,3,3  = 0
 c812:  0.998 xPB_B,N,N,C,I,M,3,3 - xPB_B,N,N,C,I,M,3,4  = 0
 c813:  0.998 xPB_B,N,N,C,I,M,3,4 - xPB_B,N,N,C,I,M,3,5  = 0
 c814:  0.998 xPB_B,N,N,C,I,M,4,1 - xPB_B,N,N,C,I,M,4,2  = 0
 c815:  0.998 xPB_B,N,N,C,I,M,4,2 - xPB_B,N,N,C,I,M,4,3  = 0
 c816:  0.998 xPB_B,N,N,C,I,M,4,3 - xPB_B,N,N,C,I,M,4,4  = 0
 c817:  0.998 xPB_B,N,N,C,I,M,4,4 - xPB_B,N,N,C,I,M,4,5  = 0
 c818:  0.998 xPB_B,N,N,C,I,L,0,1 - xPB_B,N,N,C,I,L,0,2  = 0
 c819:  0.998 xPB_B,N,N,C,I,L,0,2 - xPB_B,N,N,C,I,L,0,3  = 0
 c820:  0.998 xPB_B,N,N,C,I,L,0,3 - xPB_B,N,N,C,I,L,0,4  = 0
 c821:  0.998 xPB_B,N,N,C,I,L,0,4 - xPB_B,N,N,C,I,L,0,5  = 0
 c822:  0.998 xPB_B,N,N,C,I,L,1,1 - xPB_B,N,N,C,I,L,1,2  = 0
 c823:  0.998 xPB_B,N,N,C,I,L,1,2 - xPB_B,N,N,C,I,L,1,3  = 0
 c824:  0.998 xPB_B,N,N,C,I,L,1,3 - xPB_B,N,N,C,I,L,1,4  = 0
 c825:  0.998 xPB_B,N,N,C,I,L,1,4 - xPB_B,N,N,C,I,L,1,5  = 0
 c826:  0.998 xPB_B,N,N,C,I,L,2,1 - xPB_B,N,N,C,I,L,2,2  = 0
 c827:  0.998 xPB_B,N,N,C,I,L,2,2 - xPB_B,N,N,C,I,L,2,3  = 0
 c828:  0.998 xPB_B,N,N,C,I,L,2,3 - xPB_B,N,N,C,I,L,2,4  = 0
 c829:  0.998 xPB_B,N,N,C,I,L,2,4 - xPB_B,N,N,C,I,L,2,5  = 0
 c830:  0.998 xPB_B,N,N,C,I,L,3,1 - xPB_B,N,N,C,I,L,3,2  = 0
 c831:  0.998 xPB_B,N,N,C,I,L,3,2 - xPB_B,N,N,C,I,L,3,3  = 0
 c832:  0.998 xPB_B,N,N,C,I,L,3,3 - xPB_B,N,N,C,I,L,3,4  = 0
 c833:  0.998 xPB_B,N,N,C,I,L,3,4 - xPB_B,N,N,C,I,L,3,5  = 0
 c834:  0.998 xPB_B,N,N,C,I,L,4,1 - xPB_B,N,N,C,I,L,4,2  = 0
 c835:  0.998 xPB_B,N,N,C,I,L,4,2 - xPB_B,N,N,C,I,L,4,3  = 0
 c836:  0.998 xPB_B,N,N,C,I,L,4,3 - xPB_B,N,N,C,I,L,4,4  = 0
 c837:  0.998 xPB_B,N,N,C,I,L,4,4 - xPB_B,N,N,C,I,L,4,5  = 0
 c838:  0.998 xPB_B,N,N,C,A,P,0,1 - xPB_B,N,N,C,A,P,0,2  = 0
 c839:  0.998 xPB_B,N,N,C,A,P,0,2 - xPB_B,N,N,C,A,P,0,3  = 0
 c840:  0.998 xPB_B,N,N,C,A,P,0,3 - xPB_B,N,N,C,A,P,0,4  = 0
 c841:  0.998 xPB_B,N,N,C,A,P,0,4 - xPB_B,N,N,C,A,P,0,5  = 0
 c842:  0.998 xPB_B,N,N,C,A,P,1,1 - xPB_B,N,N,C,A,P,1,2  = 0
 c843:  0.998 xPB_B,N,N,C,A,P,1,2 - xPB_B,N,N,C,A,P,1,3  = 0
 c844:  0.998 xPB_B,N,N,C,A,P,1,3 - xPB_B,N,N,C,A,P,1,4  = 0
 c845:  0.998 xPB_B,N,N,C,A,P,1,4 - xPB_B,N,N,C,A,P,1,5  = 0
 c846:  0.998 xPB_B,N,N,C,A,P,2,1 - xPB_B,N,N,C,A,P,2,2  = 0
 c847:  0.998 xPB_B,N,N,C,A,P,2,2 - xPB_B,N,N,C,A,P,2,3  = 0
 c848:  0.998 xPB_B,N,N,C,A,P,2,3 - xPB_B,N,N,C,A,P,2,4  = 0
 c849:  0.998 xPB_B,N,N,C,A,P,2,4 - xPB_B,N,N,C,A,P,2,5  = 0
 c850:  0.998 xPB_B,N,N,C,A,P,3,1 - xPB_B,N,N,C,A,P,3,2  = 0
 c851:  0.998 xPB_B,N,N,C,A,P,3,2 - xPB_B,N,N,C,A,P,3,3  = 0
 c852:  0.998 xPB_B,N,N,C,A,P,3,3 - xPB_B,N,N,C,A,P,3,4  = 0
 c853:  0.998 xPB_B,N,N,C,A,P,3,4 - xPB_B,N,N,C,A,P,3,5  = 0
 c854:  0.998 xPB_B,N,N,C,A,P,4,1 - xPB_B,N,N,C,A,P,4,2  = 0
 c855:  0.998 xPB_B,N,N,C,A,P,4,2 - xPB_B,N,N,C,A,P,4,3  = 0
 c856:  0.998 xPB_B,N,N,C,A,P,4,3 - xPB_B,N,N,C,A,P,4,4  = 0
 c857:  0.998 xPB_B,N,N,C,A,P,4,4 - xPB_B,N,N,C,A,P,4,5  = 0
 c858:  0.998 xPB_B,N,N,C,A,M,0,1 - xPB_B,N,N,C,A,M,0,2  = 0
 c859:  0.998 xPB_B,N,N,C,A,M,0,2 - xPB_B,N,N,C,A,M,0,3  = 0
 c860:  0.998 xPB_B,N,N,C,A,M,0,3 - xPB_B,N,N,C,A,M,0,4  = 0
 c861:  0.998 xPB_B,N,N,C,A,M,0,4 - xPB_B,N,N,C,A,M,0,5  = 0
 c862:  0.998 xPB_B,N,N,C,A,M,1,1 - xPB_B,N,N,C,A,M,1,2  = 0
 c863:  0.998 xPB_B,N,N,C,A,M,1,2 - xPB_B,N,N,C,A,M,1,3  = 0
 c864:  0.998 xPB_B,N,N,C,A,M,1,3 - xPB_B,N,N,C,A,M,1,4  = 0
 c865:  0.998 xPB_B,N,N,C,A,M,1,4 - xPB_B,N,N,C,A,M,1,5  = 0
 c866:  0.998 xPB_B,N,N,C,A,M,2,1 - xPB_B,N,N,C,A,M,2,2  = 0
 c867:  0.998 xPB_B,N,N,C,A,M,2,2 - xPB_B,N,N,C,A,M,2,3  = 0
 c868:  0.998 xPB_B,N,N,C,A,M,2,3 - xPB_B,N,N,C,A,M,2,4  = 0
 c869:  0.998 xPB_B,N,N,C,A,M,2,4 - xPB_B,N,N,C,A,M,2,5  = 0
 c870:  0.998 xPB_B,N,N,C,A,M,3,1 - xPB_B,N,N,C,A,M,3,2  = 0
 c871:  0.998 xPB_B,N,N,C,A,M,3,2 - xPB_B,N,N,C,A,M,3,3  = 0
 c872:  0.998 xPB_B,N,N,C,A,M,3,3 - xPB_B,N,N,C,A,M,3,4  = 0
 c873:  0.998 xPB_B,N,N,C,A,M,3,4 - xPB_B,N,N,C,A,M,3,5  = 0
 c874:  0.998 xPB_B,N,N,C,A,M,4,1 - xPB_B,N,N,C,A,M,4,2  = 0
 c875:  0.998 xPB_B,N,N,C,A,M,4,2 - xPB_B,N,N,C,A,M,4,3  = 0
 c876:  0.998 xPB_B,N,N,C,A,M,4,3 - xPB_B,N,N,C,A,M,4,4  = 0
 c877:  0.998 xPB_B,N,N,C,A,M,4,4 - xPB_B,N,N,C,A,M,4,5  = 0
 c878:  0.998 xPB_B,N,N,C,A,L,0,1 - xPB_B,N,N,C,A,L,0,2  = 0
 c879:  0.998 xPB_B,N,N,C,A,L,0,2 - xPB_B,N,N,C,A,L,0,3  = 0
 c880:  0.998 xPB_B,N,N,C,A,L,0,3 - xPB_B,N,N,C,A,L,0,4  = 0
 c881:  0.998 xPB_B,N,N,C,A,L,0,4 - xPB_B,N,N,C,A,L,0,5  = 0
 c882:  0.998 xPB_B,N,N,C,A,L,1,1 - xPB_B,N,N,C,A,L,1,2  = 0
 c883:  0.998 xPB_B,N,N,C,A,L,1,2 - xPB_B,N,N,C,A,L,1,3  = 0
 c884:  0.998 xPB_B,N,N,C,A,L,1,3 - xPB_B,N,N,C,A,L,1,4  = 0
 c885:  0.998 xPB_B,N,N,C,A,L,1,4 - xPB_B,N,N,C,A,L,1,5  = 0
 c886:  0.998 xPB_B,N,N,C,A,L,2,1 - xPB_B,N,N,C,A,L,2,2  = 0
 c887:  0.998 xPB_B,N,N,C,A,L,2,2 - xPB_B,N,N,C,A,L,2,3  = 0
 c888:  0.998 xPB_B,N,N,C,A,L,2,3 - xPB_B,N,N,C,A,L,2,4  = 0
 c889:  0.998 xPB_B,N,N,C,A,L,2,4 - xPB_B,N,N,C,A,L,2,5  = 0
 c890:  0.998 xPB_B,N,N,C,A,L,3,1 - xPB_B,N,N,C,A,L,3,2  = 0
 c891:  0.998 xPB_B,N,N,C,A,L,3,2 - xPB_B,N,N,C,A,L,3,3  = 0
 c892:  0.998 xPB_B,N,N,C,A,L,3,3 - xPB_B,N,N,C,A,L,3,4  = 0
 c893:  0.998 xPB_B,N,N,C,A,L,3,4 - xPB_B,N,N,C,A,L,3,5  = 0
 c894:  0.998 xPB_B,N,N,C,A,L,4,1 - xPB_B,N,N,C,A,L,4,2  = 0
 c895:  0.998 xPB_B,N,N,C,A,L,4,2 - xPB_B,N,N,C,A,L,4,3  = 0
 c896:  0.998 xPB_B,N,N,C,A,L,4,3 - xPB_B,N,N,C,A,L,4,4  = 0
 c897:  0.998 xPB_B,N,N,C,A,L,4,4 - xPB_B,N,N,C,A,L,4,5  = 0
 c898:  0.998 xPB_B,N,N,C,L,M,0,1 - xPB_B,N,N,C,L,M,0,2  = 0
 c899:  0.998 xPB_B,N,N,C,L,M,0,2 - xPB_B,N,N,C,L,M,0,3  = 0
 c900:  0.998 xPB_B,N,N,C,L,M,0,3 - xPB_B,N,N,C,L,M,0,4  = 0
 c901:  0.998 xPB_B,N,N,C,L,M,0,4 - xPB_B,N,N,C,L,M,0,5  = 0
 c902:  0.998 xPB_B,N,N,C,L,M,1,1 - xPB_B,N,N,C,L,M,1,2  = 0
 c903:  0.998 xPB_B,N,N,C,L,M,1,2 - xPB_B,N,N,C,L,M,1,3  = 0
 c904:  0.998 xPB_B,N,N,C,L,M,1,3 - xPB_B,N,N,C,L,M,1,4  = 0
 c905:  0.998 xPB_B,N,N,C,L,M,1,4 - xPB_B,N,N,C,L,M,1,5  = 0
 c906:  0.998 xPB_B,N,N,C,L,M,2,1 - xPB_B,N,N,C,L,M,2,2  = 0
 c907:  0.998 xPB_B,N,N,C,L,M,2,2 - xPB_B,N,N,C,L,M,2,3  = 0
 c908:  0.998 xPB_B,N,N,C,L,M,2,3 - xPB_B,N,N,C,L,M,2,4  = 0
 c909:  0.998 xPB_B,N,N,C,L,M,2,4 - xPB_B,N,N,C,L,M,2,5  = 0
 c910:  0.998 xPB_B,N,N,C,L,M,3,1 - xPB_B,N,N,C,L,M,3,2  = 0
 c911:  0.998 xPB_B,N,N,C,L,M,3,2 - xPB_B,N,N,C,L,M,3,3  = 0
 c912:  0.998 xPB_B,N,N,C,L,M,3,3 - xPB_B,N,N,C,L,M,3,4  = 0
 c913:  0.998 xPB_B,N,N,C,L,M,3,4 - xPB_B,N,N,C,L,M,3,5  = 0
 c914:  0.998 xPB_B,N,N,C,L,M,4,1 - xPB_B,N,N,C,L,M,4,2  = 0
 c915:  0.998 xPB_B,N,N,C,L,M,4,2 - xPB_B,N,N,C,L,M,4,3  = 0
 c916:  0.998 xPB_B,N,N,C,L,M,4,3 - xPB_B,N,N,C,L,M,4,4  = 0
 c917:  0.998 xPB_B,N,N,C,L,M,4,4 - xPB_B,N,N,C,L,M,4,5  = 0
 c918:  0.998 xPB_B,N,N,C,L,L,0,1 - xPB_B,N,N,C,L,L,0,2  = 0
 c919:  0.998 xPB_B,N,N,C,L,L,0,2 - xPB_B,N,N,C,L,L,0,3  = 0
 c920:  0.998 xPB_B,N,N,C,L,L,0,3 - xPB_B,N,N,C,L,L,0,4  = 0
 c921:  0.998 xPB_B,N,N,C,L,L,0,4 - xPB_B,N,N,C,L,L,0,5  = 0
 c922:  0.998 xPB_B,N,N,C,L,L,1,1 - xPB_B,N,N,C,L,L,1,2  = 0
 c923:  0.998 xPB_B,N,N,C,L,L,1,2 - xPB_B,N,N,C,L,L,1,3  = 0
 c924:  0.998 xPB_B,N,N,C,L,L,1,3 - xPB_B,N,N,C,L,L,1,4  = 0
 c925:  0.998 xPB_B,N,N,C,L,L,1,4 - xPB_B,N,N,C,L,L,1,5  = 0
 c926:  0.998 xPB_B,N,N,C,L,L,2,1 - xPB_B,N,N,C,L,L,2,2  = 0
 c927:  0.998 xPB_B,N,N,C,L,L,2,2 - xPB_B,N,N,C,L,L,2,3  = 0
 c928:  0.998 xPB_B,N,N,C,L,L,2,3 - xPB_B,N,N,C,L,L,2,4  = 0
 c929:  0.998 xPB_B,N,N,C,L,L,2,4 - xPB_B,N,N,C,L,L,2,5  = 0
 c930:  0.998 xPB_B,N,N,C,L,L,3,1 - xPB_B,N,N,C,L,L,3,2  = 0
 c931:  0.998 xPB_B,N,N,C,L,L,3,2 - xPB_B,N,N,C,L,L,3,3  = 0
 c932:  0.998 xPB_B,N,N,C,L,L,3,3 - xPB_B,N,N,C,L,L,3,4  = 0
 c933:  0.998 xPB_B,N,N,C,L,L,3,4 - xPB_B,N,N,C,L,L,3,5  = 0
 c934:  0.998 xPB_B,N,N,C,L,L,4,1 - xPB_B,N,N,C,L,L,4,2  = 0
 c935:  0.998 xPB_B,N,N,C,L,L,4,2 - xPB_B,N,N,C,L,L,4,3  = 0
 c936:  0.998 xPB_B,N,N,C,L,L,4,3 - xPB_B,N,N,C,L,L,4,4  = 0
 c937:  0.998 xPB_B,N,N,C,L,L,4,4 - xPB_B,N,N,C,L,L,4,5  = 0
 c938:  xPB_B,N,N,C,N,N,0,1 - xPB_B,N,N,C,N,N,0,2  = 0
 c939:  xPB_B,N,N,C,N,N,0,2 - xPB_B,N,N,C,N,N,0,3  = 0
 c940:  xPB_B,N,N,C,N,N,0,3 - xPB_B,N,N,C,N,N,0,4  = 0
 c941:  xPB_B,N,N,C,N,N,0,4 - xPB_B,N,N,C,N,N,0,5  = 0
 c942:  xPB_B,N,N,C,N,N,1,1 - xPB_B,N,N,C,N,N,1,2  = 0
 c943:  xPB_B,N,N,C,N,N,1,2 - xPB_B,N,N,C,N,N,1,3  = 0
 c944:  xPB_B,N,N,C,N,N,1,3 - xPB_B,N,N,C,N,N,1,4  = 0
 c945:  xPB_B,N,N,C,N,N,1,4 - xPB_B,N,N,C,N,N,1,5  = 0
 c946:  xPB_B,N,N,C,N,N,2,1 - xPB_B,N,N,C,N,N,2,2  = 0
 c947:  xPB_B,N,N,C,N,N,2,2 - xPB_B,N,N,C,N,N,2,3  = 0
 c948:  xPB_B,N,N,C,N,N,2,3 - xPB_B,N,N,C,N,N,2,4  = 0
 c949:  xPB_B,N,N,C,N,N,2,4 - xPB_B,N,N,C,N,N,2,5  = 0
 c950:  xPB_B,N,N,C,N,N,3,1 - xPB_B,N,N,C,N,N,3,2  = 0
 c951:  xPB_B,N,N,C,N,N,3,2 - xPB_B,N,N,C,N,N,3,3  = 0
 c952:  xPB_B,N,N,C,N,N,3,3 - xPB_B,N,N,C,N,N,3,4  = 0
 c953:  xPB_B,N,N,C,N,N,3,4 - xPB_B,N,N,C,N,N,3,5  = 0
 c954:  xPB_B,N,N,C,N,N,4,1 - xPB_B,N,N,C,N,N,4,2  = 0
 c955:  xPB_B,N,N,C,N,N,4,2 - xPB_B,N,N,C,N,N,4,3  = 0
 c956:  xPB_B,N,N,C,N,N,4,3 - xPB_B,N,N,C,N,N,4,4  = 0
 c957:  xPB_B,N,N,C,N,N,4,4 - xPB_B,N,N,C,N,N,4,5  = 0
 c958:  x_B,N,N,L,C,M,2 - xGS_B,N,N,L,C,M,0,1 - xGS_B,N,N,L,C,M,1,1
        - xGS_B,N,N,L,C,M,2,1 - xGS_B,N,N,L,C,M,3,1 - xGS_B,N,N,L,C,M,4,1  = 0
 c959:  x_B,N,N,L,C,L,2 - xGS_B,N,N,L,C,L,0,1 - xGS_B,N,N,L,C,L,1,1
        - xGS_B,N,N,L,C,L,2,1 - xGS_B,N,N,L,C,L,3,1 - xGS_B,N,N,L,C,L,4,1  = 0
 c960:  x_B,N,N,L,A,S,2 - xGS_B,N,N,L,A,S,0,1 - xGS_B,N,N,L,A,S,1,1
        - xGS_B,N,N,L,A,S,2,1 - xGS_B,N,N,L,A,S,3,1 - xGS_B,N,N,L,A,S,4,1  = 0
 c961:  x_B,N,N,L,A,M,2 - xGS_B,N,N,L,A,M,0,1 - xGS_B,N,N,L,A,M,1,1
        - xGS_B,N,N,L,A,M,2,1 - xGS_B,N,N,L,A,M,3,1 - xGS_B,N,N,L,A,M,4,1  = 0
 c962:  x_B,N,N,L,A,L,2 - xGS_B,N,N,L,A,L,0,1 - xGS_B,N,N,L,A,L,1,1
        - xGS_B,N,N,L,A,L,2,1 - xGS_B,N,N,L,A,L,3,1 - xGS_B,N,N,L,A,L,4,1  = 0
 c963:  x_B,N,N,H,C,L,2 - xGS_B,N,N,H,C,L,0,1 - xGS_B,N,N,H,C,L,1,1
        - xGS_B,N,N,H,C,L,2,1 - xGS_B,N,N,H,C,L,3,1 - xGS_B,N,N,H,C,L,4,1  = 0
 c964:  x_B,N,N,C,P,P,2 - xGS_B,N,N,C,P,P,0,1 - xGS_B,N,N,C,P,P,1,1
        - xGS_B,N,N,C,P,P,2,1 - xGS_B,N,N,C,P,P,3,1 - xGS_B,N,N,C,P,P,4,1  = 0
 c965:  x_B,N,N,C,P,M,2 - xGS_B,N,N,C,P,M,0,1 - xGS_B,N,N,C,P,M,1,1
        - xGS_B,N,N,C,P,M,2,1 - xGS_B,N,N,C,P,M,3,1 - xGS_B,N,N,C,P,M,4,1  = 0
 c966:  x_B,N,N,C,P,L,2 - xGS_B,N,N,C,P,L,0,1 - xGS_B,N,N,C,P,L,1,1
        - xGS_B,N,N,C,P,L,2,1 - xGS_B,N,N,C,P,L,3,1 - xGS_B,N,N,C,P,L,4,1  = 0
 c967:  x_B,N,N,C,D,S,2 - xGS_B,N,N,C,D,S,0,1 - xGS_B,N,N,C,D,S,1,1
        - xGS_B,N,N,C,D,S,2,1 - xGS_B,N,N,C,D,S,3,1 - xGS_B,N,N,C,D,S,4,1  = 0
 c968:  x_B,N,N,C,D,P,2 - xGS_B,N,N,C,D,P,0,1 - xGS_B,N,N,C,D,P,1,1
        - xGS_B,N,N,C,D,P,2,1 - xGS_B,N,N,C,D,P,3,1 - xGS_B,N,N,C,D,P,4,1  = 0
 c969:  x_B,N,N,C,D,M,2 - xGS_B,N,N,C,D,M,0,1 - xGS_B,N,N,C,D,M,1,1
        - xGS_B,N,N,C,D,M,2,1 - xGS_B,N,N,C,D,M,3,1 - xGS_B,N,N,C,D,M,4,1  = 0
 c970:  x_B,N,N,C,D,L,2 - xGS_B,N,N,C,D,L,0,1 - xGS_B,N,N,C,D,L,1,1
        - xGS_B,N,N,C,D,L,2,1 - xGS_B,N,N,C,D,L,3,1 - xGS_B,N,N,C,D,L,4,1  = 0
 c971:  x_B,N,N,C,W,S,2 - xGS_B,N,N,C,W,S,0,1 - xGS_B,N,N,C,W,S,1,1
        - xGS_B,N,N,C,W,S,2,1 - xGS_B,N,N,C,W,S,3,1 - xGS_B,N,N,C,W,S,4,1  = 0
 c972:  x_B,N,N,C,W,P,2 - xGS_B,N,N,C,W,P,0,1 - xGS_B,N,N,C,W,P,1,1
        - xGS_B,N,N,C,W,P,2,1 - xGS_B,N,N,C,W,P,3,1 - xGS_B,N,N,C,W,P,4,1  = 0
 c973:  x_B,N,N,C,W,M,2 - xGS_B,N,N,C,W,M,0,1 - xGS_B,N,N,C,W,M,1,1
        - xGS_B,N,N,C,W,M,2,1 - xGS_B,N,N,C,W,M,3,1 - xGS_B,N,N,C,W,M,4,1  = 0
 c974:  x_B,N,N,C,W,L,2 - xGS_B,N,N,C,W,L,0,1 - xGS_B,N,N,C,W,L,1,1
        - xGS_B,N,N,C,W,L,2,1 - xGS_B,N,N,C,W,L,3,1 - xGS_B,N,N,C,W,L,4,1  = 0
 c975:  x_B,N,N,C,C,S,2 - xGS_B,N,N,C,C,S,0,1 - xGS_B,N,N,C,C,S,1,1
        - xGS_B,N,N,C,C,S,2,1 - xGS_B,N,N,C,C,S,3,1 - xGS_B,N,N,C,C,S,4,1  = 0
 c976:  x_B,N,N,C,C,P,2 - xGS_B,N,N,C,C,P,0,1 - xGS_B,N,N,C,C,P,1,1
        - xGS_B,N,N,C,C,P,2,1 - xGS_B,N,N,C,C,P,3,1 - xGS_B,N,N,C,C,P,4,1  = 0
 c977:  x_B,N,N,C,C,M,2 - xGS_B,N,N,C,C,M,0,1 - xGS_B,N,N,C,C,M,1,1
        - xGS_B,N,N,C,C,M,2,1 - xGS_B,N,N,C,C,M,3,1 - xGS_B,N,N,C,C,M,4,1  = 0
 c978:  x_B,N,N,C,C,L,2 - xGS_B,N,N,C,C,L,0,1 - xGS_B,N,N,C,C,L,1,1
        - xGS_B,N,N,C,C,L,2,1 - xGS_B,N,N,C,C,L,3,1 - xGS_B,N,N,C,C,L,4,1  = 0
 c979:  x_B,N,N,C,I,M,2 - xGS_B,N,N,C,I,M,0,1 - xGS_B,N,N,C,I,M,1,1
        - xGS_B,N,N,C,I,M,2,1 - xGS_B,N,N,C,I,M,3,1 - xGS_B,N,N,C,I,M,4,1  = 0
 c980:  x_B,N,N,C,I,L,2 - xGS_B,N,N,C,I,L,0,1 - xGS_B,N,N,C,I,L,1,1
        - xGS_B,N,N,C,I,L,2,1 - xGS_B,N,N,C,I,L,3,1 - xGS_B,N,N,C,I,L,4,1  = 0
 c981:  x_B,N,N,C,A,P,2 - xGS_B,N,N,C,A,P,0,1 - xGS_B,N,N,C,A,P,1,1
        - xGS_B,N,N,C,A,P,2,1 - xGS_B,N,N,C,A,P,3,1 - xGS_B,N,N,C,A,P,4,1  = 0
 c982:  x_B,N,N,C,A,M,2 - xGS_B,N,N,C,A,M,0,1 - xGS_B,N,N,C,A,M,1,1
        - xGS_B,N,N,C,A,M,2,1 - xGS_B,N,N,C,A,M,3,1 - xGS_B,N,N,C,A,M,4,1  = 0
 c983:  x_B,N,N,C,A,L,2 - xGS_B,N,N,C,A,L,0,1 - xGS_B,N,N,C,A,L,1,1
        - xGS_B,N,N,C,A,L,2,1 - xGS_B,N,N,C,A,L,3,1 - xGS_B,N,N,C,A,L,4,1  = 0
 c984:  x_B,N,N,C,L,M,2 - xGS_B,N,N,C,L,M,0,1 - xGS_B,N,N,C,L,M,1,1
        - xGS_B,N,N,C,L,M,2,1 - xGS_B,N,N,C,L,M,3,1 - xGS_B,N,N,C,L,M,4,1  = 0
 c985:  x_B,N,N,C,L,L,2 - xGS_B,N,N,C,L,L,0,1 - xGS_B,N,N,C,L,L,1,1
        - xGS_B,N,N,C,L,L,2,1 - xGS_B,N,N,C,L,L,3,1 - xGS_B,N,N,C,L,L,4,1  = 0
 c986:  x_B,N,N,C,N,N,2 - xGS_B,N,N,C,N,N,0,1 - xGS_B,N,N,C,N,N,1,1
        - xGS_B,N,N,C,N,N,2,1 - xGS_B,N,N,C,N,N,3,1 - xGS_B,N,N,C,N,N,4,1  = 0
 c987:  0.998 xGS_B,N,N,L,C,M,0,1 - xGS_B,N,N,L,C,M,0,2  = 0
 c988:  0.998 xGS_B,N,N,L,C,M,0,2 - xGS_B,N,N,L,C,M,0,3  = 0
 c989:  0.998 xGS_B,N,N,L,C,M,0,3 - xGS_B,N,N,L,C,M,0,4  = 0
 c990:  0.998 xGS_B,N,N,L,C,M,0,4 - xGS_B,N,N,L,C,M,0,5  = 0
 c991:  0.998 xGS_B,N,N,L,C,M,1,1 - xGS_B,N,N,L,C,M,1,2  = 0
 c992:  0.998 xGS_B,N,N,L,C,M,1,2 - xGS_B,N,N,L,C,M,1,3  = 0
 c993:  0.998 xGS_B,N,N,L,C,M,1,3 - xGS_B,N,N,L,C,M,1,4  = 0
 c994:  0.998 xGS_B,N,N,L,C,M,1,4 - xGS_B,N,N,L,C,M,1,5  = 0
 c995:  0.998 xGS_B,N,N,L,C,M,2,1 - xGS_B,N,N,L,C,M,2,2  = 0
 c996:  0.998 xGS_B,N,N,L,C,M,2,2 - xGS_B,N,N,L,C,M,2,3  = 0
 c997:  0.998 xGS_B,N,N,L,C,M,2,3 - xGS_B,N,N,L,C,M,2,4  = 0
 c998:  0.998 xGS_B,N,N,L,C,M,2,4 - xGS_B,N,N,L,C,M,2,5  = 0
 c999:  0.998 xGS_B,N,N,L,C,M,3,1 - xGS_B,N,N,L,C,M,3,2  = 0
 c1000: 0.998 xGS_B,N,N,L,C,M,3,2 - xGS_B,N,N,L,C,M,3,3  = 0
 c1001: 0.998 xGS_B,N,N,L,C,M,3,3 - xGS_B,N,N,L,C,M,3,4  = 0
 c1002: 0.998 xGS_B,N,N,L,C,M,3,4 - xGS_B,N,N,L,C,M,3,5  = 0
 c1003: 0.998 xGS_B,N,N,L,C,M,4,1 - xGS_B,N,N,L,C,M,4,2  = 0
 c1004: 0.998 xGS_B,N,N,L,C,M,4,2 - xGS_B,N,N,L,C,M,4,3  = 0
 c1005: 0.998 xGS_B,N,N,L,C,M,4,3 - xGS_B,N,N,L,C,M,4,4  = 0
 c1006: 0.998 xGS_B,N,N,L,C,M,4,4 - xGS_B,N,N,L,C,M,4,5  = 0
 c1007: 0.998 xGS_B,N,N,L,C,L,0,1 - xGS_B,N,N,L,C,L,0,2  = 0
 c1008: 0.998 xGS_B,N,N,L,C,L,0,2 - xGS_B,N,N,L,C,L,0,3  = 0
 c1009: 0.998 xGS_B,N,N,L,C,L,0,3 - xGS_B,N,N,L,C,L,0,4  = 0
 c1010: 0.998 xGS_B,N,N,L,C,L,0,4 - xGS_B,N,N,L,C,L,0,5  = 0
 c1011: 0.998 xGS_B,N,N,L,C,L,1,1 - xGS_B,N,N,L,C,L,1,2  = 0
 c1012: 0.998 xGS_B,N,N,L,C,L,1,2 - xGS_B,N,N,L,C,L,1,3  = 0
 c1013: 0.998 xGS_B,N,N,L,C,L,1,3 - xGS_B,N,N,L,C,L,1,4  = 0
 c1014: 0.998 xGS_B,N,N,L,C,L,1,4 - xGS_B,N,N,L,C,L,1,5  = 0
 c1015: 0.998 xGS_B,N,N,L,C,L,2,1 - xGS_B,N,N,L,C,L,2,2  = 0
 c1016: 0.998 xGS_B,N,N,L,C,L,2,2 - xGS_B,N,N,L,C,L,2,3  = 0
 c1017: 0.998 xGS_B,N,N,L,C,L,2,3 - xGS_B,N,N,L,C,L,2,4  = 0
 c1018: 0.998 xGS_B,N,N,L,C,L,2,4 - xGS_B,N,N,L,C,L,2,5  = 0
 c1019: 0.998 xGS_B,N,N,L,C,L,3,1 - xGS_B,N,N,L,C,L,3,2  = 0
 c1020: 0.998 xGS_B,N,N,L,C,L,3,2 - xGS_B,N,N,L,C,L,3,3  = 0
 c1021: 0.998 xGS_B,N,N,L,C,L,3,3 - xGS_B,N,N,L,C,L,3,4  = 0
 c1022: 0.998 xGS_B,N,N,L,C,L,3,4 - xGS_B,N,N,L,C,L,3,5  = 0
 c1023: 0.998 xGS_B,N,N,L,C,L,4,1 - xGS_B,N,N,L,C,L,4,2  = 0
 c1024: 0.998 xGS_B,N,N,L,C,L,4,2 - xGS_B,N,N,L,C,L,4,3  = 0
 c1025: 0.998 xGS_B,N,N,L,C,L,4,3 - xGS_B,N,N,L,C,L,4,4  = 0
 c1026: 0.998 xGS_B,N,N,L,C,L,4,4 - xGS_B,N,N,L,C,L,4,5  = 0
 c1027: 0.998 xGS_B,N,N,L,A,S,0,1 - xGS_B,N,N,L,A,S,0,2  = 0
 c1028: 0.998 xGS_B,N,N,L,A,S,0,2 - xGS_B,N,N,L,A,S,0,3  = 0
 c1029: 0.998 xGS_B,N,N,L,A,S,0,3 - xGS_B,N,N,L,A,S,0,4  = 0
 c1030: 0.998 xGS_B,N,N,L,A,S,0,4 - xGS_B,N,N,L,A,S,0,5  = 0
 c1031: 0.998 xGS_B,N,N,L,A,S,1,1 - xGS_B,N,N,L,A,S,1,2  = 0
 c1032: 0.998 xGS_B,N,N,L,A,S,1,2 - xGS_B,N,N,L,A,S,1,3  = 0
 c1033: 0.998 xGS_B,N,N,L,A,S,1,3 - xGS_B,N,N,L,A,S,1,4  = 0
 c1034: 0.998 xGS_B,N,N,L,A,S,1,4 - xGS_B,N,N,L,A,S,1,5  = 0
 c1035: 0.998 xGS_B,N,N,L,A,S,2,1 - xGS_B,N,N,L,A,S,2,2  = 0
 c1036: 0.998 xGS_B,N,N,L,A,S,2,2 - xGS_B,N,N,L,A,S,2,3  = 0
 c1037: 0.998 xGS_B,N,N,L,A,S,2,3 - xGS_B,N,N,L,A,S,2,4  = 0
 c1038: 0.998 xGS_B,N,N,L,A,S,2,4 - xGS_B,N,N,L,A,S,2,5  = 0
 c1039: 0.998 xGS_B,N,N,L,A,S,3,1 - xGS_B,N,N,L,A,S,3,2  = 0
 c1040: 0.998 xGS_B,N,N,L,A,S,3,2 - xGS_B,N,N,L,A,S,3,3  = 0
 c1041: 0.998 xGS_B,N,N,L,A,S,3,3 - xGS_B,N,N,L,A,S,3,4  = 0
 c1042: 0.998 xGS_B,N,N,L,A,S,3,4 - xGS_B,N,N,L,A,S,3,5  = 0
 c1043: 0.998 xGS_B,N,N,L,A,S,4,1 - xGS_B,N,N,L,A,S,4,2  = 0
 c1044: 0.998 xGS_B,N,N,L,A,S,4,2 - xGS_B,N,N,L,A,S,4,3  = 0
 c1045: 0.998 xGS_B,N,N,L,A,S,4,3 - xGS_B,N,N,L,A,S,4,4  = 0
 c1046: 0.998 xGS_B,N,N,L,A,S,4,4 - xGS_B,N,N,L,A,S,4,5  = 0
 c1047: 0.998 xGS_B,N,N,L,A,M,0,1 - xGS_B,N,N,L,A,M,0,2  = 0
 c1048: 0.998 xGS_B,N,N,L,A,M,0,2 - xGS_B,N,N,L,A,M,0,3  = 0
 c1049: 0.998 xGS_B,N,N,L,A,M,0,3 - xGS_B,N,N,L,A,M,0,4  = 0
 c1050: 0.998 xGS_B,N,N,L,A,M,0,4 - xGS_B,N,N,L,A,M,0,5  = 0
 c1051: 0.998 xGS_B,N,N,L,A,M,1,1 - xGS_B,N,N,L,A,M,1,2  = 0
 c1052: 0.998 xGS_B,N,N,L,A,M,1,2 - xGS_B,N,N,L,A,M,1,3  = 0
 c1053: 0.998 xGS_B,N,N,L,A,M,1,3 - xGS_B,N,N,L,A,M,1,4  = 0
 c1054: 0.998 xGS_B,N,N,L,A,M,1,4 - xGS_B,N,N,L,A,M,1,5  = 0
 c1055: 0.998 xGS_B,N,N,L,A,M,2,1 - xGS_B,N,N,L,A,M,2,2  = 0
 c1056: 0.998 xGS_B,N,N,L,A,M,2,2 - xGS_B,N,N,L,A,M,2,3  = 0
 c1057: 0.998 xGS_B,N,N,L,A,M,2,3 - xGS_B,N,N,L,A,M,2,4  = 0
 c1058: 0.998 xGS_B,N,N,L,A,M,2,4 - xGS_B,N,N,L,A,M,2,5  = 0
 c1059: 0.998 xGS_B,N,N,L,A,M,3,1 - xGS_B,N,N,L,A,M,3,2  = 0
 c1060: 0.998 xGS_B,N,N,L,A,M,3,2 - xGS_B,N,N,L,A,M,3,3  = 0
 c1061: 0.998 xGS_B,N,N,L,A,M,3,3 - xGS_B,N,N,L,A,M,3,4  = 0
 c1062: 0.998 xGS_B,N,N,L,A,M,3,4 - xGS_B,N,N,L,A,M,3,5  = 0
 c1063: 0.998 xGS_B,N,N,L,A,M,4,1 - xGS_B,N,N,L,A,M,4,2  = 0
 c1064: 0.998 xGS_B,N,N,L,A,M,4,2 - xGS_B,N,N,L,A,M,4,3  = 0
 c1065: 0.998 xGS_B,N,N,L,A,M,4,3 - xGS_B,N,N,L,A,M,4,4  = 0
 c1066: 0.998 xGS_B,N,N,L,A,M,4,4 - xGS_B,N,N,L,A,M,4,5  = 0
 c1067: 0.998 xGS_B,N,N,L,A,L,0,1 - xGS_B,N,N,L,A,L,0,2  = 0
 c1068: 0.998 xGS_B,N,N,L,A,L,0,2 - xGS_B,N,N,L,A,L,0,3  = 0
 c1069: 0.998 xGS_B,N,N,L,A,L,0,3 - xGS_B,N,N,L,A,L,0,4  = 0
 c1070: 0.998 xGS_B,N,N,L,A,L,0,4 - xGS_B,N,N,L,A,L,0,5  = 0
 c1071: 0.998 xGS_B,N,N,L,A,L,1,1 - xGS_B,N,N,L,A,L,1,2  = 0
 c1072: 0.998 xGS_B,N,N,L,A,L,1,2 - xGS_B,N,N,L,A,L,1,3  = 0
 c1073: 0.998 xGS_B,N,N,L,A,L,1,3 - xGS_B,N,N,L,A,L,1,4  = 0
 c1074: 0.998 xGS_B,N,N,L,A,L,1,4 - xGS_B,N,N,L,A,L,1,5  = 0
 c1075: 0.998 xGS_B,N,N,L,A,L,2,1 - xGS_B,N,N,L,A,L,2,2  = 0
 c1076: 0.998 xGS_B,N,N,L,A,L,2,2 - xGS_B,N,N,L,A,L,2,3  = 0
 c1077: 0.998 xGS_B,N,N,L,A,L,2,3 - xGS_B,N,N,L,A,L,2,4  = 0
 c1078: 0.998 xGS_B,N,N,L,A,L,2,4 - xGS_B,N,N,L,A,L,2,5  = 0
 c1079: 0.998 xGS_B,N,N,L,A,L,3,1 - xGS_B,N,N,L,A,L,3,2  = 0
 c1080: 0.998 xGS_B,N,N,L,A,L,3,2 - xGS_B,N,N,L,A,L,3,3  = 0
 c1081: 0.998 xGS_B,N,N,L,A,L,3,3 - xGS_B,N,N,L,A,L,3,4  = 0
 c1082: 0.998 xGS_B,N,N,L,A,L,3,4 - xGS_B,N,N,L,A,L,3,5  = 0
 c1083: 0.998 xGS_B,N,N,L,A,L,4,1 - xGS_B,N,N,L,A,L,4,2  = 0
 c1084: 0.998 xGS_B,N,N,L,A,L,4,2 - xGS_B,N,N,L,A,L,4,3  = 0
 c1085: 0.998 xGS_B,N,N,L,A,L,4,3 - xGS_B,N,N,L,A,L,4,4  = 0
 c1086: 0.998 xGS_B,N,N,L,A,L,4,4 - xGS_B,N,N,L,A,L,4,5  = 0
 c1087: 0.998 xGS_B,N,N,H,C,L,0,1 - xGS_B,N,N,H,C,L,0,2  = 0
 c1088: 0.998 xGS_B,N,N,H,C,L,0,2 - xGS_B,N,N,H,C,L,0,3  = 0
 c1089: 0.998 xGS_B,N,N,H,C,L,0,3 - xGS_B,N,N,H,C,L,0,4  = 0
 c1090: 0.998 xGS_B,N,N,H,C,L,0,4 - xGS_B,N,N,H,C,L,0,5  = 0
 c1091: 0.998 xGS_B,N,N,H,C,L,1,1 - xGS_B,N,N,H,C,L,1,2  = 0
 c1092: 0.998 xGS_B,N,N,H,C,L,1,2 - xGS_B,N,N,H,C,L,1,3  = 0
 c1093: 0.998 xGS_B,N,N,H,C,L,1,3 - xGS_B,N,N,H,C,L,1,4  = 0
 c1094: 0.998 xGS_B,N,N,H,C,L,1,4 - xGS_B,N,N,H,C,L,1,5  = 0
 c1095: 0.998 xGS_B,N,N,H,C,L,2,1 - xGS_B,N,N,H,C,L,2,2  = 0
 c1096: 0.998 xGS_B,N,N,H,C,L,2,2 - xGS_B,N,N,H,C,L,2,3  = 0
 c1097: 0.998 xGS_B,N,N,H,C,L,2,3 - xGS_B,N,N,H,C,L,2,4  = 0
 c1098: 0.998 xGS_B,N,N,H,C,L,2,4 - xGS_B,N,N,H,C,L,2,5  = 0
 c1099: 0.998 xGS_B,N,N,H,C,L,3,1 - xGS_B,N,N,H,C,L,3,2  = 0
 c1100: 0.998 xGS_B,N,N,H,C,L,3,2 - xGS_B,N,N,H,C,L,3,3  = 0
 c1101: 0.998 xGS_B,N,N,H,C,L,3,3 - xGS_B,N,N,H,C,L,3,4  = 0
 c1102: 0.998 xGS_B,N,N,H,C,L,3,4 - xGS_B,N,N,H,C,L,3,5  = 0
 c1103: 0.998 xGS_B,N,N,H,C,L,4,1 - xGS_B,N,N,H,C,L,4,2  = 0
 c1104: 0.998 xGS_B,N,N,H,C,L,4,2 - xGS_B,N,N,H,C,L,4,3  = 0
 c1105: 0.998 xGS_B,N,N,H,C,L,4,3 - xGS_B,N,N,H,C,L,4,4  = 0
 c1106: 0.998 xGS_B,N,N,H,C,L,4,4 - xGS_B,N,N,H,C,L,4,5  = 0
 c1107: 0.998 xGS_B,N,N,C,P,P,0,1 - xGS_B,N,N,C,P,P,0,2  = 0
 c1108: 0.998 xGS_B,N,N,C,P,P,0,2 - xGS_B,N,N,C,P,P,0,3  = 0
 c1109: 0.998 xGS_B,N,N,C,P,P,0,3 - xGS_B,N,N,C,P,P,0,4  = 0
 c1110: 0.998 xGS_B,N,N,C,P,P,0,4 - xGS_B,N,N,C,P,P,0,5  = 0
 c1111: 0.998 xGS_B,N,N,C,P,P,1,1 - xGS_B,N,N,C,P,P,1,2  = 0
 c1112: 0.998 xGS_B,N,N,C,P,P,1,2 - xGS_B,N,N,C,P,P,1,3  = 0
 c1113: 0.998 xGS_B,N,N,C,P,P,1,3 - xGS_B,N,N,C,P,P,1,4  = 0
 c1114: 0.998 xGS_B,N,N,C,P,P,1,4 - xGS_B,N,N,C,P,P,1,5  = 0
 c1115: 0.998 xGS_B,N,N,C,P,P,2,1 - xGS_B,N,N,C,P,P,2,2  = 0
 c1116: 0.998 xGS_B,N,N,C,P,P,2,2 - xGS_B,N,N,C,P,P,2,3  = 0
 c1117: 0.998 xGS_B,N,N,C,P,P,2,3 - xGS_B,N,N,C,P,P,2,4  = 0
 c1118: 0.998 xGS_B,N,N,C,P,P,2,4 - xGS_B,N,N,C,P,P,2,5  = 0
 c1119: 0.998 xGS_B,N,N,C,P,P,3,1 - xGS_B,N,N,C,P,P,3,2  = 0
 c1120: 0.998 xGS_B,N,N,C,P,P,3,2 - xGS_B,N,N,C,P,P,3,3  = 0
 c1121: 0.998 xGS_B,N,N,C,P,P,3,3 - xGS_B,N,N,C,P,P,3,4  = 0
 c1122: 0.998 xGS_B,N,N,C,P,P,3,4 - xGS_B,N,N,C,P,P,3,5  = 0
 c1123: 0.998 xGS_B,N,N,C,P,P,4,1 - xGS_B,N,N,C,P,P,4,2  = 0
 c1124: 0.998 xGS_B,N,N,C,P,P,4,2 - xGS_B,N,N,C,P,P,4,3  = 0
 c1125: 0.998 xGS_B,N,N,C,P,P,4,3 - xGS_B,N,N,C,P,P,4,4  = 0
 c1126: 0.998 xGS_B,N,N,C,P,P,4,4 - xGS_B,N,N,C,P,P,4,5  = 0
 c1127: 0.998 xGS_B,N,N,C,P,M,0,1 - xGS_B,N,N,C,P,M,0,2  = 0
 c1128: 0.998 xGS_B,N,N,C,P,M,0,2 - xGS_B,N,N,C,P,M,0,3  = 0
 c1129: 0.998 xGS_B,N,N,C,P,M,0,3 - xGS_B,N,N,C,P,M,0,4  = 0
 c1130: 0.998 xGS_B,N,N,C,P,M,0,4 - xGS_B,N,N,C,P,M,0,5  = 0
 c1131: 0.998 xGS_B,N,N,C,P,M,1,1 - xGS_B,N,N,C,P,M,1,2  = 0
 c1132: 0.998 xGS_B,N,N,C,P,M,1,2 - xGS_B,N,N,C,P,M,1,3  = 0
 c1133: 0.998 xGS_B,N,N,C,P,M,1,3 - xGS_B,N,N,C,P,M,1,4  = 0
 c1134: 0.998 xGS_B,N,N,C,P,M,1,4 - xGS_B,N,N,C,P,M,1,5  = 0
 c1135: 0.998 xGS_B,N,N,C,P,M,2,1 - xGS_B,N,N,C,P,M,2,2  = 0
 c1136: 0.998 xGS_B,N,N,C,P,M,2,2 - xGS_B,N,N,C,P,M,2,3  = 0
 c1137: 0.998 xGS_B,N,N,C,P,M,2,3 - xGS_B,N,N,C,P,M,2,4  = 0
 c1138: 0.998 xGS_B,N,N,C,P,M,2,4 - xGS_B,N,N,C,P,M,2,5  = 0
 c1139: 0.998 xGS_B,N,N,C,P,M,3,1 - xGS_B,N,N,C,P,M,3,2  = 0
 c1140: 0.998 xGS_B,N,N,C,P,M,3,2 - xGS_B,N,N,C,P,M,3,3  = 0
 c1141: 0.998 xGS_B,N,N,C,P,M,3,3 - xGS_B,N,N,C,P,M,3,4  = 0
 c1142: 0.998 xGS_B,N,N,C,P,M,3,4 - xGS_B,N,N,C,P,M,3,5  = 0
 c1143: 0.998 xGS_B,N,N,C,P,M,4,1 - xGS_B,N,N,C,P,M,4,2  = 0
 c1144: 0.998 xGS_B,N,N,C,P,M,4,2 - xGS_B,N,N,C,P,M,4,3  = 0
 c1145: 0.998 xGS_B,N,N,C,P,M,4,3 - xGS_B,N,N,C,P,M,4,4  = 0
 c1146: 0.998 xGS_B,N,N,C,P,M,4,4 - xGS_B,N,N,C,P,M,4,5  = 0
 c1147: 0.998 xGS_B,N,N,C,P,L,0,1 - xGS_B,N,N,C,P,L,0,2  = 0
 c1148: 0.998 xGS_B,N,N,C,P,L,0,2 - xGS_B,N,N,C,P,L,0,3  = 0
 c1149: 0.998 xGS_B,N,N,C,P,L,0,3 - xGS_B,N,N,C,P,L,0,4  = 0
 c1150: 0.998 xGS_B,N,N,C,P,L,0,4 - xGS_B,N,N,C,P,L,0,5  = 0
 c1151: 0.998 xGS_B,N,N,C,P,L,1,1 - xGS_B,N,N,C,P,L,1,2  = 0
 c1152: 0.998 xGS_B,N,N,C,P,L,1,2 - xGS_B,N,N,C,P,L,1,3  = 0
 c1153: 0.998 xGS_B,N,N,C,P,L,1,3 - xGS_B,N,N,C,P,L,1,4  = 0
 c1154: 0.998 xGS_B,N,N,C,P,L,1,4 - xGS_B,N,N,C,P,L,1,5  = 0
 c1155: 0.998 xGS_B,N,N,C,P,L,2,1 - xGS_B,N,N,C,P,L,2,2  = 0
 c1156: 0.998 xGS_B,N,N,C,P,L,2,2 - xGS_B,N,N,C,P,L,2,3  = 0
 c1157: 0.998 xGS_B,N,N,C,P,L,2,3 - xGS_B,N,N,C,P,L,2,4  = 0
 c1158: 0.998 xGS_B,N,N,C,P,L,2,4 - xGS_B,N,N,C,P,L,2,5  = 0
 c1159: 0.998 xGS_B,N,N,C,P,L,3,1 - xGS_B,N,N,C,P,L,3,2  = 0
 c1160: 0.998 xGS_B,N,N,C,P,L,3,2 - xGS_B,N,N,C,P,L,3,3  = 0
 c1161: 0.998 xGS_B,N,N,C,P,L,3,3 - xGS_B,N,N,C,P,L,3,4  = 0
 c1162: 0.998 xGS_B,N,N,C,P,L,3,4 - xGS_B,N,N,C,P,L,3,5  = 0
 c1163: 0.998 xGS_B,N,N,C,P,L,4,1 - xGS_B,N,N,C,P,L,4,2  = 0
 c1164: 0.998 xGS_B,N,N,C,P,L,4,2 - xGS_B,N,N,C,P,L,4,3  = 0
 c1165: 0.998 xGS_B,N,N,C,P,L,4,3 - xGS_B,N,N,C,P,L,4,4  = 0
 c1166: 0.998 xGS_B,N,N,C,P,L,4,4 - xGS_B,N,N,C,P,L,4,5  = 0
 c1167: 0.998 xGS_B,N,N,C,D,S,0,1 - xGS_B,N,N,C,D,S,0,2  = 0
 c1168: 0.998 xGS_B,N,N,C,D,S,0,2 - xGS_B,N,N,C,D,S,0,3  = 0
 c1169: 0.998 xGS_B,N,N,C,D,S,0,3 - xGS_B,N,N,C,D,S,0,4  = 0
 c1170: 0.998 xGS_B,N,N,C,D,S,0,4 - xGS_B,N,N,C,D,S,0,5  = 0
 c1171: 0.998 xGS_B,N,N,C,D,S,1,1 - xGS_B,N,N,C,D,S,1,2  = 0
 c1172: 0.998 xGS_B,N,N,C,D,S,1,2 - xGS_B,N,N,C,D,S,1,3  = 0
 c1173: 0.998 xGS_B,N,N,C,D,S,1,3 - xGS_B,N,N,C,D,S,1,4  = 0
 c1174: 0.998 xGS_B,N,N,C,D,S,1,4 - xGS_B,N,N,C,D,S,1,5  = 0
 c1175: 0.998 xGS_B,N,N,C,D,S,2,1 - xGS_B,N,N,C,D,S,2,2  = 0
 c1176: 0.998 xGS_B,N,N,C,D,S,2,2 - xGS_B,N,N,C,D,S,2,3  = 0
 c1177: 0.998 xGS_B,N,N,C,D,S,2,3 - xGS_B,N,N,C,D,S,2,4  = 0
 c1178: 0.998 xGS_B,N,N,C,D,S,2,4 - xGS_B,N,N,C,D,S,2,5  = 0
 c1179: 0.998 xGS_B,N,N,C,D,S,3,1 - xGS_B,N,N,C,D,S,3,2  = 0
 c1180: 0.998 xGS_B,N,N,C,D,S,3,2 - xGS_B,N,N,C,D,S,3,3  = 0
 c1181: 0.998 xGS_B,N,N,C,D,S,3,3 - xGS_B,N,N,C,D,S,3,4  = 0
 c1182: 0.998 xGS_B,N,N,C,D,S,3,4 - xGS_B,N,N,C,D,S,3,5  = 0
 c1183: 0.998 xGS_B,N,N,C,D,S,4,1 - xGS_B,N,N,C,D,S,4,2  = 0
 c1184: 0.998 xGS_B,N,N,C,D,S,4,2 - xGS_B,N,N,C,D,S,4,3  = 0
 c1185: 0.998 xGS_B,N,N,C,D,S,4,3 - xGS_B,N,N,C,D,S,4,4  = 0
 c1186: 0.998 xGS_B,N,N,C,D,S,4,4 - xGS_B,N,N,C,D,S,4,5  = 0
 c1187: 0.998 xGS_B,N,N,C,D,P,0,1 - xGS_B,N,N,C,D,P,0,2  = 0
 c1188: 0.998 xGS_B,N,N,C,D,P,0,2 - xGS_B,N,N,C,D,P,0,3  = 0
 c1189: 0.998 xGS_B,N,N,C,D,P,0,3 - xGS_B,N,N,C,D,P,0,4  = 0
 c1190: 0.998 xGS_B,N,N,C,D,P,0,4 - xGS_B,N,N,C,D,P,0,5  = 0
 c1191: 0.998 xGS_B,N,N,C,D,P,1,1 - xGS_B,N,N,C,D,P,1,2  = 0
 c1192: 0.998 xGS_B,N,N,C,D,P,1,2 - xGS_B,N,N,C,D,P,1,3  = 0
 c1193: 0.998 xGS_B,N,N,C,D,P,1,3 - xGS_B,N,N,C,D,P,1,4  = 0
 c1194: 0.998 xGS_B,N,N,C,D,P,1,4 - xGS_B,N,N,C,D,P,1,5  = 0
 c1195: 0.998 xGS_B,N,N,C,D,P,2,1 - xGS_B,N,N,C,D,P,2,2  = 0
 c1196: 0.998 xGS_B,N,N,C,D,P,2,2 - xGS_B,N,N,C,D,P,2,3  = 0
 c1197: 0.998 xGS_B,N,N,C,D,P,2,3 - xGS_B,N,N,C,D,P,2,4  = 0
 c1198: 0.998 xGS_B,N,N,C,D,P,2,4 - xGS_B,N,N,C,D,P,2,5  = 0
 c1199: 0.998 xGS_B,N,N,C,D,P,3,1 - xGS_B,N,N,C,D,P,3,2  = 0
 c1200: 0.998 xGS_B,N,N,C,D,P,3,2 - xGS_B,N,N,C,D,P,3,3  = 0
 c1201: 0.998 xGS_B,N,N,C,D,P,3,3 - xGS_B,N,N,C,D,P,3,4  = 0
 c1202: 0.998 xGS_B,N,N,C,D,P,3,4 - xGS_B,N,N,C,D,P,3,5  = 0
 c1203: 0.998 xGS_B,N,N,C,D,P,4,1 - xGS_B,N,N,C,D,P,4,2  = 0
 c1204: 0.998 xGS_B,N,N,C,D,P,4,2 - xGS_B,N,N,C,D,P,4,3  = 0
 c1205: 0.998 xGS_B,N,N,C,D,P,4,3 - xGS_B,N,N,C,D,P,4,4  = 0
 c1206: 0.998 xGS_B,N,N,C,D,P,4,4 - xGS_B,N,N,C,D,P,4,5  = 0
 c1207: 0.998 xGS_B,N,N,C,D,M,0,1 - xGS_B,N,N,C,D,M,0,2  = 0
 c1208: 0.998 xGS_B,N,N,C,D,M,0,2 - xGS_B,N,N,C,D,M,0,3  = 0
 c1209: 0.998 xGS_B,N,N,C,D,M,0,3 - xGS_B,N,N,C,D,M,0,4  = 0
 c1210: 0.998 xGS_B,N,N,C,D,M,0,4 - xGS_B,N,N,C,D,M,0,5  = 0
 c1211: 0.998 xGS_B,N,N,C,D,M,1,1 - xGS_B,N,N,C,D,M,1,2  = 0
 c1212: 0.998 xGS_B,N,N,C,D,M,1,2 - xGS_B,N,N,C,D,M,1,3  = 0
 c1213: 0.998 xGS_B,N,N,C,D,M,1,3 - xGS_B,N,N,C,D,M,1,4  = 0
 c1214: 0.998 xGS_B,N,N,C,D,M,1,4 - xGS_B,N,N,C,D,M,1,5  = 0
 c1215: 0.998 xGS_B,N,N,C,D,M,2,1 - xGS_B,N,N,C,D,M,2,2  = 0
 c1216: 0.998 xGS_B,N,N,C,D,M,2,2 - xGS_B,N,N,C,D,M,2,3  = 0
 c1217: 0.998 xGS_B,N,N,C,D,M,2,3 - xGS_B,N,N,C,D,M,2,4  = 0
 c1218: 0.998 xGS_B,N,N,C,D,M,2,4 - xGS_B,N,N,C,D,M,2,5  = 0
 c1219: 0.998 xGS_B,N,N,C,D,M,3,1 - xGS_B,N,N,C,D,M,3,2  = 0
 c1220: 0.998 xGS_B,N,N,C,D,M,3,2 - xGS_B,N,N,C,D,M,3,3  = 0
 c1221: 0.998 xGS_B,N,N,C,D,M,3,3 - xGS_B,N,N,C,D,M,3,4  = 0
 c1222: 0.998 xGS_B,N,N,C,D,M,3,4 - xGS_B,N,N,C,D,M,3,5  = 0
 c1223: 0.998 xGS_B,N,N,C,D,M,4,1 - xGS_B,N,N,C,D,M,4,2  = 0
 c1224: 0.998 xGS_B,N,N,C,D,M,4,2 - xGS_B,N,N,C,D,M,4,3  = 0
 c1225: 0.998 xGS_B,N,N,C,D,M,4,3 - xGS_B,N,N,C,D,M,4,4  = 0
 c1226: 0.998 xGS_B,N,N,C,D,M,4,4 - xGS_B,N,N,C,D,M,4,5  = 0
 c1227: 0.998 xGS_B,N,N,C,D,L,0,1 - xGS_B,N,N,C,D,L,0,2  = 0
 c1228: 0.998 xGS_B,N,N,C,D,L,0,2 - xGS_B,N,N,C,D,L,0,3  = 0
 c1229: 0.998 xGS_B,N,N,C,D,L,0,3 - xGS_B,N,N,C,D,L,0,4  = 0
 c1230: 0.998 xGS_B,N,N,C,D,L,0,4 - xGS_B,N,N,C,D,L,0,5  = 0
 c1231: 0.998 xGS_B,N,N,C,D,L,1,1 - xGS_B,N,N,C,D,L,1,2  = 0
 c1232: 0.998 xGS_B,N,N,C,D,L,1,2 - xGS_B,N,N,C,D,L,1,3  = 0
 c1233: 0.998 xGS_B,N,N,C,D,L,1,3 - xGS_B,N,N,C,D,L,1,4  = 0
 c1234: 0.998 xGS_B,N,N,C,D,L,1,4 - xGS_B,N,N,C,D,L,1,5  = 0
 c1235: 0.998 xGS_B,N,N,C,D,L,2,1 - xGS_B,N,N,C,D,L,2,2  = 0
 c1236: 0.998 xGS_B,N,N,C,D,L,2,2 - xGS_B,N,N,C,D,L,2,3  = 0
 c1237: 0.998 xGS_B,N,N,C,D,L,2,3 - xGS_B,N,N,C,D,L,2,4  = 0
 c1238: 0.998 xGS_B,N,N,C,D,L,2,4 - xGS_B,N,N,C,D,L,2,5  = 0
 c1239: 0.998 xGS_B,N,N,C,D,L,3,1 - xGS_B,N,N,C,D,L,3,2  = 0
 c1240: 0.998 xGS_B,N,N,C,D,L,3,2 - xGS_B,N,N,C,D,L,3,3  = 0
 c1241: 0.998 xGS_B,N,N,C,D,L,3,3 - xGS_B,N,N,C,D,L,3,4  = 0
 c1242: 0.998 xGS_B,N,N,C,D,L,3,4 - xGS_B,N,N,C,D,L,3,5  = 0
 c1243: 0.998 xGS_B,N,N,C,D,L,4,1 - xGS_B,N,N,C,D,L,4,2  = 0
 c1244: 0.998 xGS_B,N,N,C,D,L,4,2 - xGS_B,N,N,C,D,L,4,3  = 0
 c1245: 0.998 xGS_B,N,N,C,D,L,4,3 - xGS_B,N,N,C,D,L,4,4  = 0
 c1246: 0.998 xGS_B,N,N,C,D,L,4,4 - xGS_B,N,N,C,D,L,4,5  = 0
 c1247: 0.998 xGS_B,N,N,C,W,S,0,1 - xGS_B,N,N,C,W,S,0,2  = 0
 c1248: 0.998 xGS_B,N,N,C,W,S,0,2 - xGS_B,N,N,C,W,S,0,3  = 0
 c1249: 0.998 xGS_B,N,N,C,W,S,0,3 - xGS_B,N,N,C,W,S,0,4  = 0
 c1250: 0.998 xGS_B,N,N,C,W,S,0,4 - xGS_B,N,N,C,W,S,0,5  = 0
 c1251: 0.998 xGS_B,N,N,C,W,S,1,1 - xGS_B,N,N,C,W,S,1,2  = 0
 c1252: 0.998 xGS_B,N,N,C,W,S,1,2 - xGS_B,N,N,C,W,S,1,3  = 0
 c1253: 0.998 xGS_B,N,N,C,W,S,1,3 - xGS_B,N,N,C,W,S,1,4  = 0
 c1254: 0.998 xGS_B,N,N,C,W,S,1,4 - xGS_B,N,N,C,W,S,1,5  = 0
 c1255: 0.998 xGS_B,N,N,C,W,S,2,1 - xGS_B,N,N,C,W,S,2,2  = 0
 c1256: 0.998 xGS_B,N,N,C,W,S,2,2 - xGS_B,N,N,C,W,S,2,3  = 0
 c1257: 0.998 xGS_B,N,N,C,W,S,2,3 - xGS_B,N,N,C,W,S,2,4  = 0
 c1258: 0.998 xGS_B,N,N,C,W,S,2,4 - xGS_B,N,N,C,W,S,2,5  = 0
 c1259: 0.998 xGS_B,N,N,C,W,S,3,1 - xGS_B,N,N,C,W,S,3,2  = 0
 c1260: 0.998 xGS_B,N,N,C,W,S,3,2 - xGS_B,N,N,C,W,S,3,3  = 0
 c1261: 0.998 xGS_B,N,N,C,W,S,3,3 - xGS_B,N,N,C,W,S,3,4  = 0
 c1262: 0.998 xGS_B,N,N,C,W,S,3,4 - xGS_B,N,N,C,W,S,3,5  = 0
 c1263: 0.998 xGS_B,N,N,C,W,S,4,1 - xGS_B,N,N,C,W,S,4,2  = 0
 c1264: 0.998 xGS_B,N,N,C,W,S,4,2 - xGS_B,N,N,C,W,S,4,3  = 0
 c1265: 0.998 xGS_B,N,N,C,W,S,4,3 - xGS_B,N,N,C,W,S,4,4  = 0
 c1266: 0.998 xGS_B,N,N,C,W,S,4,4 - xGS_B,N,N,C,W,S,4,5  = 0
 c1267: 0.998 xGS_B,N,N,C,W,P,0,1 - xGS_B,N,N,C,W,P,0,2  = 0
 c1268: 0.998 xGS_B,N,N,C,W,P,0,2 - xGS_B,N,N,C,W,P,0,3  = 0
 c1269: 0.998 xGS_B,N,N,C,W,P,0,3 - xGS_B,N,N,C,W,P,0,4  = 0
 c1270: 0.998 xGS_B,N,N,C,W,P,0,4 - xGS_B,N,N,C,W,P,0,5  = 0
 c1271: 0.998 xGS_B,N,N,C,W,P,1,1 - xGS_B,N,N,C,W,P,1,2  = 0
 c1272: 0.998 xGS_B,N,N,C,W,P,1,2 - xGS_B,N,N,C,W,P,1,3  = 0
 c1273: 0.998 xGS_B,N,N,C,W,P,1,3 - xGS_B,N,N,C,W,P,1,4  = 0
 c1274: 0.998 xGS_B,N,N,C,W,P,1,4 - xGS_B,N,N,C,W,P,1,5  = 0
 c1275: 0.998 xGS_B,N,N,C,W,P,2,1 - xGS_B,N,N,C,W,P,2,2  = 0
 c1276: 0.998 xGS_B,N,N,C,W,P,2,2 - xGS_B,N,N,C,W,P,2,3  = 0
 c1277: 0.998 xGS_B,N,N,C,W,P,2,3 - xGS_B,N,N,C,W,P,2,4  = 0
 c1278: 0.998 xGS_B,N,N,C,W,P,2,4 - xGS_B,N,N,C,W,P,2,5  = 0
 c1279: 0.998 xGS_B,N,N,C,W,P,3,1 - xGS_B,N,N,C,W,P,3,2  = 0
 c1280: 0.998 xGS_B,N,N,C,W,P,3,2 - xGS_B,N,N,C,W,P,3,3  = 0
 c1281: 0.998 xGS_B,N,N,C,W,P,3,3 - xGS_B,N,N,C,W,P,3,4  = 0
 c1282: 0.998 xGS_B,N,N,C,W,P,3,4 - xGS_B,N,N,C,W,P,3,5  = 0
 c1283: 0.998 xGS_B,N,N,C,W,P,4,1 - xGS_B,N,N,C,W,P,4,2  = 0
 c1284: 0.998 xGS_B,N,N,C,W,P,4,2 - xGS_B,N,N,C,W,P,4,3  = 0
 c1285: 0.998 xGS_B,N,N,C,W,P,4,3 - xGS_B,N,N,C,W,P,4,4  = 0
 c1286: 0.998 xGS_B,N,N,C,W,P,4,4 - xGS_B,N,N,C,W,P,4,5  = 0
 c1287: 0.998 xGS_B,N,N,C,W,M,0,1 - xGS_B,N,N,C,W,M,0,2  = 0
 c1288: 0.998 xGS_B,N,N,C,W,M,0,2 - xGS_B,N,N,C,W,M,0,3  = 0
 c1289: 0.998 xGS_B,N,N,C,W,M,0,3 - xGS_B,N,N,C,W,M,0,4  = 0
 c1290: 0.998 xGS_B,N,N,C,W,M,0,4 - xGS_B,N,N,C,W,M,0,5  = 0
 c1291: 0.998 xGS_B,N,N,C,W,M,1,1 - xGS_B,N,N,C,W,M,1,2  = 0
 c1292: 0.998 xGS_B,N,N,C,W,M,1,2 - xGS_B,N,N,C,W,M,1,3  = 0
 c1293: 0.998 xGS_B,N,N,C,W,M,1,3 - xGS_B,N,N,C,W,M,1,4  = 0
 c1294: 0.998 xGS_B,N,N,C,W,M,1,4 - xGS_B,N,N,C,W,M,1,5  = 0
 c1295: 0.998 xGS_B,N,N,C,W,M,2,1 - xGS_B,N,N,C,W,M,2,2  = 0
 c1296: 0.998 xGS_B,N,N,C,W,M,2,2 - xGS_B,N,N,C,W,M,2,3  = 0
 c1297: 0.998 xGS_B,N,N,C,W,M,2,3 - xGS_B,N,N,C,W,M,2,4  = 0
 c1298: 0.998 xGS_B,N,N,C,W,M,2,4 - xGS_B,N,N,C,W,M,2,5  = 0
 c1299: 0.998 xGS_B,N,N,C,W,M,3,1 - xGS_B,N,N,C,W,M,3,2  = 0
 c1300: 0.998 xGS_B,N,N,C,W,M,3,2 - xGS_B,N,N,C,W,M,3,3  = 0
 c1301: 0.998 xGS_B,N,N,C,W,M,3,3 - xGS_B,N,N,C,W,M,3,4  = 0
 c1302: 0.998 xGS_B,N,N,C,W,M,3,4 - xGS_B,N,N,C,W,M,3,5  = 0
 c1303: 0.998 xGS_B,N,N,C,W,M,4,1 - xGS_B,N,N,C,W,M,4,2  = 0
 c1304: 0.998 xGS_B,N,N,C,W,M,4,2 - xGS_B,N,N,C,W,M,4,3  = 0
 c1305: 0.998 xGS_B,N,N,C,W,M,4,3 - xGS_B,N,N,C,W,M,4,4  = 0
 c1306: 0.998 xGS_B,N,N,C,W,M,4,4 - xGS_B,N,N,C,W,M,4,5  = 0
 c1307: 0.998 xGS_B,N,N,C,W,L,0,1 - xGS_B,N,N,C,W,L,0,2  = 0
 c1308: 0.998 xGS_B,N,N,C,W,L,0,2 - xGS_B,N,N,C,W,L,0,3  = 0
 c1309: 0.998 xGS_B,N,N,C,W,L,0,3 - xGS_B,N,N,C,W,L,0,4  = 0
 c1310: 0.998 xGS_B,N,N,C,W,L,0,4 - xGS_B,N,N,C,W,L,0,5  = 0
 c1311: 0.998 xGS_B,N,N,C,W,L,1,1 - xGS_B,N,N,C,W,L,1,2  = 0
 c1312: 0.998 xGS_B,N,N,C,W,L,1,2 - xGS_B,N,N,C,W,L,1,3  = 0
 c1313: 0.998 xGS_B,N,N,C,W,L,1,3 - xGS_B,N,N,C,W,L,1,4  = 0
 c1314: 0.998 xGS_B,N,N,C,W,L,1,4 - xGS_B,N,N,C,W,L,1,5  = 0
 c1315: 0.998 xGS_B,N,N,C,W,L,2,1 - xGS_B,N,N,C,W,L,2,2  = 0
 c1316: 0.998 xGS_B,N,N,C,W,L,2,2 - xGS_B,N,N,C,W,L,2,3  = 0
 c1317: 0.998 xGS_B,N,N,C,W,L,2,3 - xGS_B,N,N,C,W,L,2,4  = 0
 c1318: 0.998 xGS_B,N,N,C,W,L,2,4 - xGS_B,N,N,C,W,L,2,5  = 0
 c1319: 0.998 xGS_B,N,N,C,W,L,3,1 - xGS_B,N,N,C,W,L,3,2  = 0
 c1320: 0.998 xGS_B,N,N,C,W,L,3,2 - xGS_B,N,N,C,W,L,3,3  = 0
 c1321: 0.998 xGS_B,N,N,C,W,L,3,3 - xGS_B,N,N,C,W,L,3,4  = 0
 c1322: 0.998 xGS_B,N,N,C,W,L,3,4 - xGS_B,N,N,C,W,L,3,5  = 0
 c1323: 0.998 xGS_B,N,N,C,W,L,4,1 - xGS_B,N,N,C,W,L,4,2  = 0
 c1324: 0.998 xGS_B,N,N,C,W,L,4,2 - xGS_B,N,N,C,W,L,4,3  = 0
 c1325: 0.998 xGS_B,N,N,C,W,L,4,3 - xGS_B,N,N,C,W,L,4,4  = 0
 c1326: 0.998 xGS_B,N,N,C,W,L,4,4 - xGS_B,N,N,C,W,L,4,5  = 0
 c1327: 0.998 xGS_B,N,N,C,C,S,0,1 - xGS_B,N,N,C,C,S,0,2  = 0
 c1328: 0.998 xGS_B,N,N,C,C,S,0,2 - xGS_B,N,N,C,C,S,0,3  = 0
 c1329: 0.998 xGS_B,N,N,C,C,S,0,3 - xGS_B,N,N,C,C,S,0,4  = 0
 c1330: 0.998 xGS_B,N,N,C,C,S,0,4 - xGS_B,N,N,C,C,S,0,5  = 0
 c1331: 0.998 xGS_B,N,N,C,C,S,1,1 - xGS_B,N,N,C,C,S,1,2  = 0
 c1332: 0.998 xGS_B,N,N,C,C,S,1,2 - xGS_B,N,N,C,C,S,1,3  = 0
 c1333: 0.998 xGS_B,N,N,C,C,S,1,3 - xGS_B,N,N,C,C,S,1,4  = 0
 c1334: 0.998 xGS_B,N,N,C,C,S,1,4 - xGS_B,N,N,C,C,S,1,5  = 0
 c1335: 0.998 xGS_B,N,N,C,C,S,2,1 - xGS_B,N,N,C,C,S,2,2  = 0
 c1336: 0.998 xGS_B,N,N,C,C,S,2,2 - xGS_B,N,N,C,C,S,2,3  = 0
 c1337: 0.998 xGS_B,N,N,C,C,S,2,3 - xGS_B,N,N,C,C,S,2,4  = 0
 c1338: 0.998 xGS_B,N,N,C,C,S,2,4 - xGS_B,N,N,C,C,S,2,5  = 0
 c1339: 0.998 xGS_B,N,N,C,C,S,3,1 - xGS_B,N,N,C,C,S,3,2  = 0
 c1340: 0.998 xGS_B,N,N,C,C,S,3,2 - xGS_B,N,N,C,C,S,3,3  = 0
 c1341: 0.998 xGS_B,N,N,C,C,S,3,3 - xGS_B,N,N,C,C,S,3,4  = 0
 c1342: 0.998 xGS_B,N,N,C,C,S,3,4 - xGS_B,N,N,C,C,S,3,5  = 0
 c1343: 0.998 xGS_B,N,N,C,C,S,4,1 - xGS_B,N,N,C,C,S,4,2  = 0
 c1344: 0.998 xGS_B,N,N,C,C,S,4,2 - xGS_B,N,N,C,C,S,4,3  = 0
 c1345: 0.998 xGS_B,N,N,C,C,S,4,3 - xGS_B,N,N,C,C,S,4,4  = 0
 c1346: 0.998 xGS_B,N,N,C,C,S,4,4 - xGS_B,N,N,C,C,S,4,5  = 0
 c1347: 0.998 xGS_B,N,N,C,C,P,0,1 - xGS_B,N,N,C,C,P,0,2  = 0
 c1348: 0.998 xGS_B,N,N,C,C,P,0,2 - xGS_B,N,N,C,C,P,0,3  = 0
 c1349: 0.998 xGS_B,N,N,C,C,P,0,3 - xGS_B,N,N,C,C,P,0,4  = 0
 c1350: 0.998 xGS_B,N,N,C,C,P,0,4 - xGS_B,N,N,C,C,P,0,5  = 0
 c1351: 0.998 xGS_B,N,N,C,C,P,1,1 - xGS_B,N,N,C,C,P,1,2  = 0
 c1352: 0.998 xGS_B,N,N,C,C,P,1,2 - xGS_B,N,N,C,C,P,1,3  = 0
 c1353: 0.998 xGS_B,N,N,C,C,P,1,3 - xGS_B,N,N,C,C,P,1,4  = 0
 c1354: 0.998 xGS_B,N,N,C,C,P,1,4 - xGS_B,N,N,C,C,P,1,5  = 0
 c1355: 0.998 xGS_B,N,N,C,C,P,2,1 - xGS_B,N,N,C,C,P,2,2  = 0
 c1356: 0.998 xGS_B,N,N,C,C,P,2,2 - xGS_B,N,N,C,C,P,2,3  = 0
 c1357: 0.998 xGS_B,N,N,C,C,P,2,3 - xGS_B,N,N,C,C,P,2,4  = 0
 c1358: 0.998 xGS_B,N,N,C,C,P,2,4 - xGS_B,N,N,C,C,P,2,5  = 0
 c1359: 0.998 xGS_B,N,N,C,C,P,3,1 - xGS_B,N,N,C,C,P,3,2  = 0
 c1360: 0.998 xGS_B,N,N,C,C,P,3,2 - xGS_B,N,N,C,C,P,3,3  = 0
 c1361: 0.998 xGS_B,N,N,C,C,P,3,3 - xGS_B,N,N,C,C,P,3,4  = 0
 c1362: 0.998 xGS_B,N,N,C,C,P,3,4 - xGS_B,N,N,C,C,P,3,5  = 0
 c1363: 0.998 xGS_B,N,N,C,C,P,4,1 - xGS_B,N,N,C,C,P,4,2  = 0
 c1364: 0.998 xGS_B,N,N,C,C,P,4,2 - xGS_B,N,N,C,C,P,4,3  = 0
 c1365: 0.998 xGS_B,N,N,C,C,P,4,3 - xGS_B,N,N,C,C,P,4,4  = 0
 c1366: 0.998 xGS_B,N,N,C,C,P,4,4 - xGS_B,N,N,C,C,P,4,5  = 0
 c1367: 0.998 xGS_B,N,N,C,C,M,0,1 - xGS_B,N,N,C,C,M,0,2  = 0
 c1368: 0.998 xGS_B,N,N,C,C,M,0,2 - xGS_B,N,N,C,C,M,0,3  = 0
 c1369: 0.998 xGS_B,N,N,C,C,M,0,3 - xGS_B,N,N,C,C,M,0,4  = 0
 c1370: 0.998 xGS_B,N,N,C,C,M,0,4 - xGS_B,N,N,C,C,M,0,5  = 0
 c1371: 0.998 xGS_B,N,N,C,C,M,1,1 - xGS_B,N,N,C,C,M,1,2  = 0
 c1372: 0.998 xGS_B,N,N,C,C,M,1,2 - xGS_B,N,N,C,C,M,1,3  = 0
 c1373: 0.998 xGS_B,N,N,C,C,M,1,3 - xGS_B,N,N,C,C,M,1,4  = 0
 c1374: 0.998 xGS_B,N,N,C,C,M,1,4 - xGS_B,N,N,C,C,M,1,5  = 0
 c1375: 0.998 xGS_B,N,N,C,C,M,2,1 - xGS_B,N,N,C,C,M,2,2  = 0
 c1376: 0.998 xGS_B,N,N,C,C,M,2,2 - xGS_B,N,N,C,C,M,2,3  = 0
 c1377: 0.998 xGS_B,N,N,C,C,M,2,3 - xGS_B,N,N,C,C,M,2,4  = 0
 c1378: 0.998 xGS_B,N,N,C,C,M,2,4 - xGS_B,N,N,C,C,M,2,5  = 0
 c1379: 0.998 xGS_B,N,N,C,C,M,3,1 - xGS_B,N,N,C,C,M,3,2  = 0
 c1380: 0.998 xGS_B,N,N,C,C,M,3,2 - xGS_B,N,N,C,C,M,3,3  = 0
 c1381: 0.998 xGS_B,N,N,C,C,M,3,3 - xGS_B,N,N,C,C,M,3,4  = 0
 c1382: 0.998 xGS_B,N,N,C,C,M,3,4 - xGS_B,N,N,C,C,M,3,5  = 0
 c1383: 0.998 xGS_B,N,N,C,C,M,4,1 - xGS_B,N,N,C,C,M,4,2  = 0
 c1384: 0.998 xGS_B,N,N,C,C,M,4,2 - xGS_B,N,N,C,C,M,4,3  = 0
 c1385: 0.998 xGS_B,N,N,C,C,M,4,3 - xGS_B,N,N,C,C,M,4,4  = 0
 c1386: 0.998 xGS_B,N,N,C,C,M,4,4 - xGS_B,N,N,C,C,M,4,5  = 0
 c1387: 0.998 xGS_B,N,N,C,C,L,0,1 - xGS_B,N,N,C,C,L,0,2  = 0
 c1388: 0.998 xGS_B,N,N,C,C,L,0,2 - xGS_B,N,N,C,C,L,0,3  = 0
 c1389: 0.998 xGS_B,N,N,C,C,L,0,3 - xGS_B,N,N,C,C,L,0,4  = 0
 c1390: 0.998 xGS_B,N,N,C,C,L,0,4 - xGS_B,N,N,C,C,L,0,5  = 0
 c1391: 0.998 xGS_B,N,N,C,C,L,1,1 - xGS_B,N,N,C,C,L,1,2  = 0
 c1392: 0.998 xGS_B,N,N,C,C,L,1,2 - xGS_B,N,N,C,C,L,1,3  = 0
 c1393: 0.998 xGS_B,N,N,C,C,L,1,3 - xGS_B,N,N,C,C,L,1,4  = 0
 c1394: 0.998 xGS_B,N,N,C,C,L,1,4 - xGS_B,N,N,C,C,L,1,5  = 0
 c1395: 0.998 xGS_B,N,N,C,C,L,2,1 - xGS_B,N,N,C,C,L,2,2  = 0
 c1396: 0.998 xGS_B,N,N,C,C,L,2,2 - xGS_B,N,N,C,C,L,2,3  = 0
 c1397: 0.998 xGS_B,N,N,C,C,L,2,3 - xGS_B,N,N,C,C,L,2,4  = 0
 c1398: 0.998 xGS_B,N,N,C,C,L,2,4 - xGS_B,N,N,C,C,L,2,5  = 0
 c1399: 0.998 xGS_B,N,N,C,C,L,3,1 - xGS_B,N,N,C,C,L,3,2  = 0
 c1400: 0.998 xGS_B,N,N,C,C,L,3,2 - xGS_B,N,N,C,C,L,3,3  = 0
 c1401: 0.998 xGS_B,N,N,C,C,L,3,3 - xGS_B,N,N,C,C,L,3,4  = 0
 c1402: 0.998 xGS_B,N,N,C,C,L,3,4 - xGS_B,N,N,C,C,L,3,5  = 0
 c1403: 0.998 xGS_B,N,N,C,C,L,4,1 - xGS_B,N,N,C,C,L,4,2  = 0
 c1404: 0.998 xGS_B,N,N,C,C,L,4,2 - xGS_B,N,N,C,C,L,4,3  = 0
 c1405: 0.998 xGS_B,N,N,C,C,L,4,3 - xGS_B,N,N,C,C,L,4,4  = 0
 c1406: 0.998 xGS_B,N,N,C,C,L,4,4 - xGS_B,N,N,C,C,L,4,5  = 0
 c1407: 0.998 xGS_B,N,N,C,I,M,0,1 - xGS_B,N,N,C,I,M,0,2  = 0
 c1408: 0.998 xGS_B,N,N,C,I,M,0,2 - xGS_B,N,N,C,I,M,0,3  = 0
 c1409: 0.998 xGS_B,N,N,C,I,M,0,3 - xGS_B,N,N,C,I,M,0,4  = 0
 c1410: 0.998 xGS_B,N,N,C,I,M,0,4 - xGS_B,N,N,C,I,M,0,5  = 0
 c1411: 0.998 xGS_B,N,N,C,I,M,1,1 - xGS_B,N,N,C,I,M,1,2  = 0
 c1412: 0.998 xGS_B,N,N,C,I,M,1,2 - xGS_B,N,N,C,I,M,1,3  = 0
 c1413: 0.998 xGS_B,N,N,C,I,M,1,3 - xGS_B,N,N,C,I,M,1,4  = 0
 c1414: 0.998 xGS_B,N,N,C,I,M,1,4 - xGS_B,N,N,C,I,M,1,5  = 0
 c1415: 0.998 xGS_B,N,N,C,I,M,2,1 - xGS_B,N,N,C,I,M,2,2  = 0
 c1416: 0.998 xGS_B,N,N,C,I,M,2,2 - xGS_B,N,N,C,I,M,2,3  = 0
 c1417: 0.998 xGS_B,N,N,C,I,M,2,3 - xGS_B,N,N,C,I,M,2,4  = 0
 c1418: 0.998 xGS_B,N,N,C,I,M,2,4 - xGS_B,N,N,C,I,M,2,5  = 0
 c1419: 0.998 xGS_B,N,N,C,I,M,3,1 - xGS_B,N,N,C,I,M,3,2  = 0
 c1420: 0.998 xGS_B,N,N,C,I,M,3,2 - xGS_B,N,N,C,I,M,3,3  = 0
 c1421: 0.998 xGS_B,N,N,C,I,M,3,3 - xGS_B,N,N,C,I,M,3,4  = 0
 c1422: 0.998 xGS_B,N,N,C,I,M,3,4 - xGS_B,N,N,C,I,M,3,5  = 0
 c1423: 0.998 xGS_B,N,N,C,I,M,4,1 - xGS_B,N,N,C,I,M,4,2  = 0
 c1424: 0.998 xGS_B,N,N,C,I,M,4,2 - xGS_B,N,N,C,I,M,4,3  = 0
 c1425: 0.998 xGS_B,N,N,C,I,M,4,3 - xGS_B,N,N,C,I,M,4,4  = 0
 c1426: 0.998 xGS_B,N,N,C,I,M,4,4 - xGS_B,N,N,C,I,M,4,5  = 0
 c1427: 0.998 xGS_B,N,N,C,I,L,0,1 - xGS_B,N,N,C,I,L,0,2  = 0
 c1428: 0.998 xGS_B,N,N,C,I,L,0,2 - xGS_B,N,N,C,I,L,0,3  = 0
 c1429: 0.998 xGS_B,N,N,C,I,L,0,3 - xGS_B,N,N,C,I,L,0,4  = 0
 c1430: 0.998 xGS_B,N,N,C,I,L,0,4 - xGS_B,N,N,C,I,L,0,5  = 0
 c1431: 0.998 xGS_B,N,N,C,I,L,1,1 - xGS_B,N,N,C,I,L,1,2  = 0
 c1432: 0.998 xGS_B,N,N,C,I,L,1,2 - xGS_B,N,N,C,I,L,1,3  = 0
 c1433: 0.998 xGS_B,N,N,C,I,L,1,3 - xGS_B,N,N,C,I,L,1,4  = 0
 c1434: 0.998 xGS_B,N,N,C,I,L,1,4 - xGS_B,N,N,C,I,L,1,5  = 0
 c1435: 0.998 xGS_B,N,N,C,I,L,2,1 - xGS_B,N,N,C,I,L,2,2  = 0
 c1436: 0.998 xGS_B,N,N,C,I,L,2,2 - xGS_B,N,N,C,I,L,2,3  = 0
 c1437: 0.998 xGS_B,N,N,C,I,L,2,3 - xGS_B,N,N,C,I,L,2,4  = 0
 c1438: 0.998 xGS_B,N,N,C,I,L,2,4 - xGS_B,N,N,C,I,L,2,5  = 0
 c1439: 0.998 xGS_B,N,N,C,I,L,3,1 - xGS_B,N,N,C,I,L,3,2  = 0
 c1440: 0.998 xGS_B,N,N,C,I,L,3,2 - xGS_B,N,N,C,I,L,3,3  = 0
 c1441: 0.998 xGS_B,N,N,C,I,L,3,3 - xGS_B,N,N,C,I,L,3,4  = 0
 c1442: 0.998 xGS_B,N,N,C,I,L,3,4 - xGS_B,N,N,C,I,L,3,5  = 0
 c1443: 0.998 xGS_B,N,N,C,I,L,4,1 - xGS_B,N,N,C,I,L,4,2  = 0
 c1444: 0.998 xGS_B,N,N,C,I,L,4,2 - xGS_B,N,N,C,I,L,4,3  = 0
 c1445: 0.998 xGS_B,N,N,C,I,L,4,3 - xGS_B,N,N,C,I,L,4,4  = 0
 c1446: 0.998 xGS_B,N,N,C,I,L,4,4 - xGS_B,N,N,C,I,L,4,5  = 0
 c1447: 0.998 xGS_B,N,N,C,A,P,0,1 - xGS_B,N,N,C,A,P,0,2  = 0
 c1448: 0.998 xGS_B,N,N,C,A,P,0,2 - xGS_B,N,N,C,A,P,0,3  = 0
 c1449: 0.998 xGS_B,N,N,C,A,P,0,3 - xGS_B,N,N,C,A,P,0,4  = 0
 c1450: 0.998 xGS_B,N,N,C,A,P,0,4 - xGS_B,N,N,C,A,P,0,5  = 0
 c1451: 0.998 xGS_B,N,N,C,A,P,1,1 - xGS_B,N,N,C,A,P,1,2  = 0
 c1452: 0.998 xGS_B,N,N,C,A,P,1,2 - xGS_B,N,N,C,A,P,1,3  = 0
 c1453: 0.998 xGS_B,N,N,C,A,P,1,3 - xGS_B,N,N,C,A,P,1,4  = 0
 c1454: 0.998 xGS_B,N,N,C,A,P,1,4 - xGS_B,N,N,C,A,P,1,5  = 0
 c1455: 0.998 xGS_B,N,N,C,A,P,2,1 - xGS_B,N,N,C,A,P,2,2  = 0
 c1456: 0.998 xGS_B,N,N,C,A,P,2,2 - xGS_B,N,N,C,A,P,2,3  = 0
 c1457: 0.998 xGS_B,N,N,C,A,P,2,3 - xGS_B,N,N,C,A,P,2,4  = 0
 c1458: 0.998 xGS_B,N,N,C,A,P,2,4 - xGS_B,N,N,C,A,P,2,5  = 0
 c1459: 0.998 xGS_B,N,N,C,A,P,3,1 - xGS_B,N,N,C,A,P,3,2  = 0
 c1460: 0.998 xGS_B,N,N,C,A,P,3,2 - xGS_B,N,N,C,A,P,3,3  = 0
 c1461: 0.998 xGS_B,N,N,C,A,P,3,3 - xGS_B,N,N,C,A,P,3,4  = 0
 c1462: 0.998 xGS_B,N,N,C,A,P,3,4 - xGS_B,N,N,C,A,P,3,5  = 0
 c1463: 0.998 xGS_B,N,N,C,A,P,4,1 - xGS_B,N,N,C,A,P,4,2  = 0
 c1464: 0.998 xGS_B,N,N,C,A,P,4,2 - xGS_B,N,N,C,A,P,4,3  = 0
 c1465: 0.998 xGS_B,N,N,C,A,P,4,3 - xGS_B,N,N,C,A,P,4,4  = 0
 c1466: 0.998 xGS_B,N,N,C,A,P,4,4 - xGS_B,N,N,C,A,P,4,5  = 0
 c1467: 0.998 xGS_B,N,N,C,A,M,0,1 - xGS_B,N,N,C,A,M,0,2  = 0
 c1468: 0.998 xGS_B,N,N,C,A,M,0,2 - xGS_B,N,N,C,A,M,0,3  = 0
 c1469: 0.998 xGS_B,N,N,C,A,M,0,3 - xGS_B,N,N,C,A,M,0,4  = 0
 c1470: 0.998 xGS_B,N,N,C,A,M,0,4 - xGS_B,N,N,C,A,M,0,5  = 0
 c1471: 0.998 xGS_B,N,N,C,A,M,1,1 - xGS_B,N,N,C,A,M,1,2  = 0
 c1472: 0.998 xGS_B,N,N,C,A,M,1,2 - xGS_B,N,N,C,A,M,1,3  = 0
 c1473: 0.998 xGS_B,N,N,C,A,M,1,3 - xGS_B,N,N,C,A,M,1,4  = 0
 c1474: 0.998 xGS_B,N,N,C,A,M,1,4 - xGS_B,N,N,C,A,M,1,5  = 0
 c1475: 0.998 xGS_B,N,N,C,A,M,2,1 - xGS_B,N,N,C,A,M,2,2  = 0
 c1476: 0.998 xGS_B,N,N,C,A,M,2,2 - xGS_B,N,N,C,A,M,2,3  = 0
 c1477: 0.998 xGS_B,N,N,C,A,M,2,3 - xGS_B,N,N,C,A,M,2,4  = 0
 c1478: 0.998 xGS_B,N,N,C,A,M,2,4 - xGS_B,N,N,C,A,M,2,5  = 0
 c1479: 0.998 xGS_B,N,N,C,A,M,3,1 - xGS_B,N,N,C,A,M,3,2  = 0
 c1480: 0.998 xGS_B,N,N,C,A,M,3,2 - xGS_B,N,N,C,A,M,3,3  = 0
 c1481: 0.998 xGS_B,N,N,C,A,M,3,3 - xGS_B,N,N,C,A,M,3,4  = 0
 c1482: 0.998 xGS_B,N,N,C,A,M,3,4 - xGS_B,N,N,C,A,M,3,5  = 0
 c1483: 0.998 xGS_B,N,N,C,A,M,4,1 - xGS_B,N,N,C,A,M,4,2  = 0
 c1484: 0.998 xGS_B,N,N,C,A,M,4,2 - xGS_B,N,N,C,A,M,4,3  = 0
 c1485: 0.998 xGS_B,N,N,C,A,M,4,3 - xGS_B,N,N,C,A,M,4,4  = 0
 c1486: 0.998 xGS_B,N,N,C,A,M,4,4 - xGS_B,N,N,C,A,M,4,5  = 0
 c1487: 0.998 xGS_B,N,N,C,A,L,0,1 - xGS_B,N,N,C,A,L,0,2  = 0
 c1488: 0.998 xGS_B,N,N,C,A,L,0,2 - xGS_B,N,N,C,A,L,0,3  = 0
 c1489: 0.998 xGS_B,N,N,C,A,L,0,3 - xGS_B,N,N,C,A,L,0,4  = 0
 c1490: 0.998 xGS_B,N,N,C,A,L,0,4 - xGS_B,N,N,C,A,L,0,5  = 0
 c1491: 0.998 xGS_B,N,N,C,A,L,1,1 - xGS_B,N,N,C,A,L,1,2  = 0
 c1492: 0.998 xGS_B,N,N,C,A,L,1,2 - xGS_B,N,N,C,A,L,1,3  = 0
 c1493: 0.998 xGS_B,N,N,C,A,L,1,3 - xGS_B,N,N,C,A,L,1,4  = 0
 c1494: 0.998 xGS_B,N,N,C,A,L,1,4 - xGS_B,N,N,C,A,L,1,5  = 0
 c1495: 0.998 xGS_B,N,N,C,A,L,2,1 - xGS_B,N,N,C,A,L,2,2  = 0
 c1496: 0.998 xGS_B,N,N,C,A,L,2,2 - xGS_B,N,N,C,A,L,2,3  = 0
 c1497: 0.998 xGS_B,N,N,C,A,L,2,3 - xGS_B,N,N,C,A,L,2,4  = 0
 c1498: 0.998 xGS_B,N,N,C,A,L,2,4 - xGS_B,N,N,C,A,L,2,5  = 0
 c1499: 0.998 xGS_B,N,N,C,A,L,3,1 - xGS_B,N,N,C,A,L,3,2  = 0
 c1500: 0.998 xGS_B,N,N,C,A,L,3,2 - xGS_B,N,N,C,A,L,3,3  = 0
 c1501: 0.998 xGS_B,N,N,C,A,L,3,3 - xGS_B,N,N,C,A,L,3,4  = 0
 c1502: 0.998 xGS_B,N,N,C,A,L,3,4 - xGS_B,N,N,C,A,L,3,5  = 0
 c1503: 0.998 xGS_B,N,N,C,A,L,4,1 - xGS_B,N,N,C,A,L,4,2  = 0
 c1504: 0.998 xGS_B,N,N,C,A,L,4,2 - xGS_B,N,N,C,A,L,4,3  = 0
 c1505: 0.998 xGS_B,N,N,C,A,L,4,3 - xGS_B,N,N,C,A,L,4,4  = 0
 c1506: 0.998 xGS_B,N,N,C,A,L,4,4 - xGS_B,N,N,C,A,L,4,5  = 0
 c1507: 0.998 xGS_B,N,N,C,L,M,0,1 - xGS_B,N,N,C,L,M,0,2  = 0
 c1508: 0.998 xGS_B,N,N,C,L,M,0,2 - xGS_B,N,N,C,L,M,0,3  = 0
 c1509: 0.998 xGS_B,N,N,C,L,M,0,3 - xGS_B,N,N,C,L,M,0,4  = 0
 c1510: 0.998 xGS_B,N,N,C,L,M,0,4 - xGS_B,N,N,C,L,M,0,5  = 0
 c1511: 0.998 xGS_B,N,N,C,L,M,1,1 - xGS_B,N,N,C,L,M,1,2  = 0
 c1512: 0.998 xGS_B,N,N,C,L,M,1,2 - xGS_B,N,N,C,L,M,1,3  = 0
 c1513: 0.998 xGS_B,N,N,C,L,M,1,3 - xGS_B,N,N,C,L,M,1,4  = 0
 c1514: 0.998 xGS_B,N,N,C,L,M,1,4 - xGS_B,N,N,C,L,M,1,5  = 0
 c1515: 0.998 xGS_B,N,N,C,L,M,2,1 - xGS_B,N,N,C,L,M,2,2  = 0
 c1516: 0.998 xGS_B,N,N,C,L,M,2,2 - xGS_B,N,N,C,L,M,2,3  = 0
 c1517: 0.998 xGS_B,N,N,C,L,M,2,3 - xGS_B,N,N,C,L,M,2,4  = 0
 c1518: 0.998 xGS_B,N,N,C,L,M,2,4 - xGS_B,N,N,C,L,M,2,5  = 0
 c1519: 0.998 xGS_B,N,N,C,L,M,3,1 - xGS_B,N,N,C,L,M,3,2  = 0
 c1520: 0.998 xGS_B,N,N,C,L,M,3,2 - xGS_B,N,N,C,L,M,3,3  = 0
 c1521: 0.998 xGS_B,N,N,C,L,M,3,3 - xGS_B,N,N,C,L,M,3,4  = 0
 c1522: 0.998 xGS_B,N,N,C,L,M,3,4 - xGS_B,N,N,C,L,M,3,5  = 0
 c1523: 0.998 xGS_B,N,N,C,L,M,4,1 - xGS_B,N,N,C,L,M,4,2  = 0
 c1524: 0.998 xGS_B,N,N,C,L,M,4,2 - xGS_B,N,N,C,L,M,4,3  = 0
 c1525: 0.998 xGS_B,N,N,C,L,M,4,3 - xGS_B,N,N,C,L,M,4,4  = 0
 c1526: 0.998 xGS_B,N,N,C,L,M,4,4 - xGS_B,N,N,C,L,M,4,5  = 0
 c1527: 0.998 xGS_B,N,N,C,L,L,0,1 - xGS_B,N,N,C,L,L,0,2  = 0
 c1528: 0.998 xGS_B,N,N,C,L,L,0,2 - xGS_B,N,N,C,L,L,0,3  = 0
 c1529: 0.998 xGS_B,N,N,C,L,L,0,3 - xGS_B,N,N,C,L,L,0,4  = 0
 c1530: 0.998 xGS_B,N,N,C,L,L,0,4 - xGS_B,N,N,C,L,L,0,5  = 0
 c1531: 0.998 xGS_B,N,N,C,L,L,1,1 - xGS_B,N,N,C,L,L,1,2  = 0
 c1532: 0.998 xGS_B,N,N,C,L,L,1,2 - xGS_B,N,N,C,L,L,1,3  = 0
 c1533: 0.998 xGS_B,N,N,C,L,L,1,3 - xGS_B,N,N,C,L,L,1,4  = 0
 c1534: 0.998 xGS_B,N,N,C,L,L,1,4 - xGS_B,N,N,C,L,L,1,5  = 0
 c1535: 0.998 xGS_B,N,N,C,L,L,2,1 - xGS_B,N,N,C,L,L,2,2  = 0
 c1536: 0.998 xGS_B,N,N,C,L,L,2,2 - xGS_B,N,N,C,L,L,2,3  = 0
 c1537: 0.998 xGS_B,N,N,C,L,L,2,3 - xGS_B,N,N,C,L,L,2,4  = 0
 c1538: 0.998 xGS_B,N,N,C,L,L,2,4 - xGS_B,N,N,C,L,L,2,5  = 0
 c1539: 0.998 xGS_B,N,N,C,L,L,3,1 - xGS_B,N,N,C,L,L,3,2  = 0
 c1540: 0.998 xGS_B,N,N,C,L,L,3,2 - xGS_B,N,N,C,L,L,3,3  = 0
 c1541: 0.998 xGS_B,N,N,C,L,L,3,3 - xGS_B,N,N,C,L,L,3,4  = 0
 c1542: 0.998 xGS_B,N,N,C,L,L,3,4 - xGS_B,N,N,C,L,L,3,5  = 0
 c1543: 0.998 xGS_B,N,N,C,L,L,4,1 - xGS_B,N,N,C,L,L,4,2  = 0
 c1544: 0.998 xGS_B,N,N,C,L,L,4,2 - xGS_B,N,N,C,L,L,4,3  = 0
 c1545: 0.998 xGS_B,N,N,C,L,L,4,3 - xGS_B,N,N,C,L,L,4,4  = 0
 c1546: 0.998 xGS_B,N,N,C,L,L,4,4 - xGS_B,N,N,C,L,L,4,5  = 0
 c1547: xGS_B,N,N,C,N,N,0,1 - xGS_B,N,N,C,N,N,0,2  = 0
 c1548: xGS_B,N,N,C,N,N,0,2 - xGS_B,N,N,C,N,N,0,3  = 0
 c1549: xGS_B,N,N,C,N,N,0,3 - xGS_B,N,N,C,N,N,0,4  = 0
 c1550: xGS_B,N,N,C,N,N,0,4 - xGS_B,N,N,C,N,N,0,5  = 0
 c1551: xGS_B,N,N,C,N,N,1,1 - xGS_B,N,N,C,N,N,1,2  = 0
 c1552: xGS_B,N,N,C,N,N,1,2 - xGS_B,N,N,C,N,N,1,3  = 0
 c1553: xGS_B,N,N,C,N,N,1,3 - xGS_B,N,N,C,N,N,1,4  = 0
 c1554: xGS_B,N,N,C,N,N,1,4 - xGS_B,N,N,C,N,N,1,5  = 0
 c1555: xGS_B,N,N,C,N,N,2,1 - xGS_B,N,N,C,N,N,2,2  = 0
 c1556: xGS_B,N,N,C,N,N,2,2 - xGS_B,N,N,C,N,N,2,3  = 0
 c1557: xGS_B,N,N,C,N,N,2,3 - xGS_B,N,N,C,N,N,2,4  = 0
 c1558: xGS_B,N,N,C,N,N,2,4 - xGS_B,N,N,C,N,N,2,5  = 0
 c1559: xGS_B,N,N,C,N,N,3,1 - xGS_B,N,N,C,N,N,3,2  = 0
 c1560: xGS_B,N,N,C,N,N,3,2 - xGS_B,N,N,C,N,N,3,3  = 0
 c1561: xGS_B,N,N,C,N,N,3,3 - xGS_B,N,N,C,N,N,3,4  = 0
 c1562: xGS_B,N,N,C,N,N,3,4 - xGS_B,N,N,C,N,N,3,5  = 0
 c1563: xGS_B,N,N,C,N,N,4,1 - xGS_B,N,N,C,N,N,4,2  = 0
 c1564: xGS_B,N,N,C,N,N,4,2 - xGS_B,N,N,C,N,N,4,3  = 0
 c1565: xGS_B,N,N,C,N,N,4,3 - xGS_B,N,N,C,N,N,4,4  = 0
 c1566: xGS_B,N,N,C,N,N,4,4 - xGS_B,N,N,C,N,N,4,5  = 0
 c1567: x_B,N,N,L,C,M,3 - xEAe_B,N,N,L,C,M,1  = 0
 c1568: x_B,N,N,L,C,L,3 - xEAe_B,N,N,L,C,L,1  = 0
 c1569: x_B,N,N,L,A,S,3 - xEAe_B,N,N,L,A,S,1  = 0
 c1570: x_B,N,N,L,A,M,3 - xEAe_B,N,N,L,A,M,1  = 0
 c1571: x_B,N,N,L,A,L,3 - xEAe_B,N,N,L,A,L,1  = 0
 c1572: x_B,N,N,H,C,L,3 - xEAe_B,N,N,H,C,L,1  = 0
 c1573: x_B,N,N,C,P,P,3 - xEAe_B,N,N,C,P,P,1  = 0
 c1574: x_B,N,N,C,P,M,3 - xEAe_B,N,N,C,P,M,1  = 0
 c1575: x_B,N,N,C,P,L,3 - xEAe_B,N,N,C,P,L,1  = 0
 c1576: x_B,N,N,C,D,S,3 - xEAe_B,N,N,C,D,S,1  = 0
 c1577: x_B,N,N,C,D,P,3 - xEAe_B,N,N,C,D,P,1  = 0
 c1578: x_B,N,N,C,D,M,3 - xEAe_B,N,N,C,D,M,1  = 0
 c1579: x_B,N,N,C,D,L,3 - xEAe_B,N,N,C,D,L,1  = 0
 c1580: x_B,N,N,C,W,S,3 - xEAe_B,N,N,C,W,S,1  = 0
 c1581: x_B,N,N,C,W,P,3 - xEAe_B,N,N,C,W,P,1  = 0
 c1582: x_B,N,N,C,W,M,3 - xEAe_B,N,N,C,W,M,1  = 0
 c1583: x_B,N,N,C,W,L,3 - xEAe_B,N,N,C,W,L,1  = 0
 c1584: x_B,N,N,C,C,S,3 - xEAe_B,N,N,C,C,S,1  = 0
 c1585: x_B,N,N,C,C,P,3 - xEAe_B,N,N,C,C,P,1  = 0
 c1586: x_B,N,N,C,C,M,3 - xEAe_B,N,N,C,C,M,1  = 0
 c1587: x_B,N,N,C,C,L,3 - xEAe_B,N,N,C,C,L,1  = 0
 c1588: x_B,N,N,C,I,M,3 - xEAe_B,N,N,C,I,M,1  = 0
 c1589: x_B,N,N,C,I,L,3 - xEAe_B,N,N,C,I,L,1  = 0
 c1590: x_B,N,N,C,A,P,3 - xEAe_B,N,N,C,A,P,1  = 0
 c1591: x_B,N,N,C,A,M,3 - xEAe_B,N,N,C,A,M,1  = 0
 c1592: x_B,N,N,C,A,L,3 - xEAe_B,N,N,C,A,L,1  = 0
 c1593: x_B,N,N,C,L,M,3 - xEAe_B,N,N,C,L,M,1  = 0
 c1594: x_B,N,N,C,L,L,3 - xEAe_B,N,N,C,L,L,1  = 0
 c1595: x_B,N,N,C,N,N,3 - xEAe_B,N,N,C,N,N,1  = 0
 c1596: 0.998 xEAe_B,N,N,L,C,M,1 - xEAe_B,N,N,L,C,M,2  = 0
 c1597: 0.998 xEAe_B,N,N,L,C,M,2 - xEAe_B,N,N,L,C,M,3  = 0
 c1598: 0.998 xEAe_B,N,N,L,C,M,3 - xEAe_B,N,N,L,C,M,4  = 0
 c1599: 0.998 xEAe_B,N,N,L,C,M,4 - xEAe_B,N,N,L,C,M,5  = 0
 c1600: 0.998 xEAe_B,N,N,L,C,L,1 - xEAe_B,N,N,L,C,L,2  = 0
 c1601: 0.998 xEAe_B,N,N,L,C,L,2 - xEAe_B,N,N,L,C,L,3  = 0
 c1602: 0.998 xEAe_B,N,N,L,C,L,3 - xEAe_B,N,N,L,C,L,4  = 0
 c1603: 0.998 xEAe_B,N,N,L,C,L,4 - xEAe_B,N,N,L,C,L,5  = 0
 c1604: 0.998 xEAe_B,N,N,L,A,S,1 - xEAe_B,N,N,L,A,S,2  = 0
 c1605: 0.998 xEAe_B,N,N,L,A,S,2 - xEAe_B,N,N,L,A,S,3  = 0
 c1606: 0.998 xEAe_B,N,N,L,A,S,3 - xEAe_B,N,N,L,A,S,4  = 0
 c1607: 0.998 xEAe_B,N,N,L,A,S,4 - xEAe_B,N,N,L,A,S,5  = 0
 c1608: 0.998 xEAe_B,N,N,L,A,M,1 - xEAe_B,N,N,L,A,M,2  = 0
 c1609: 0.998 xEAe_B,N,N,L,A,M,2 - xEAe_B,N,N,L,A,M,3  = 0
 c1610: 0.998 xEAe_B,N,N,L,A,M,3 - xEAe_B,N,N,L,A,M,4  = 0
 c1611: 0.998 xEAe_B,N,N,L,A,M,4 - xEAe_B,N,N,L,A,M,5  = 0
 c1612: 0.998 xEAe_B,N,N,L,A,L,1 - xEAe_B,N,N,L,A,L,2  = 0
 c1613: 0.998 xEAe_B,N,N,L,A,L,2 - xEAe_B,N,N,L,A,L,3  = 0
 c1614: 0.998 xEAe_B,N,N,L,A,L,3 - xEAe_B,N,N,L,A,L,4  = 0
 c1615: 0.998 xEAe_B,N,N,L,A,L,4 - xEAe_B,N,N,L,A,L,5  = 0
 c1616: 0.998 xEAe_B,N,N,H,C,L,1 - xEAe_B,N,N,H,C,L,2  = 0
 c1617: 0.998 xEAe_B,N,N,H,C,L,2 - xEAe_B,N,N,H,C,L,3  = 0
 c1618: 0.998 xEAe_B,N,N,H,C,L,3 - xEAe_B,N,N,H,C,L,4  = 0
 c1619: 0.998 xEAe_B,N,N,H,C,L,4 - xEAe_B,N,N,H,C,L,5  = 0
 c1620: 0.998 xEAe_B,N,N,C,P,P,1 - xEAe_B,N,N,C,P,P,2  = 0
 c1621: 0.998 xEAe_B,N,N,C,P,P,2 - xEAe_B,N,N,C,P,P,3  = 0
 c1622: 0.998 xEAe_B,N,N,C,P,P,3 - xEAe_B,N,N,C,P,P,4  = 0
 c1623: 0.998 xEAe_B,N,N,C,P,P,4 - xEAe_B,N,N,C,P,P,5  = 0
 c1624: 0.998 xEAe_B,N,N,C,P,M,1 - xEAe_B,N,N,C,P,M,2  = 0
 c1625: 0.998 xEAe_B,N,N,C,P,M,2 - xEAe_B,N,N,C,P,M,3  = 0
 c1626: 0.998 xEAe_B,N,N,C,P,M,3 - xEAe_B,N,N,C,P,M,4  = 0
 c1627: 0.998 xEAe_B,N,N,C,P,M,4 - xEAe_B,N,N,C,P,M,5  = 0
 c1628: 0.998 xEAe_B,N,N,C,P,L,1 - xEAe_B,N,N,C,P,L,2  = 0
 c1629: 0.998 xEAe_B,N,N,C,P,L,2 - xEAe_B,N,N,C,P,L,3  = 0
 c1630: 0.998 xEAe_B,N,N,C,P,L,3 - xEAe_B,N,N,C,P,L,4  = 0
 c1631: 0.998 xEAe_B,N,N,C,P,L,4 - xEAe_B,N,N,C,P,L,5  = 0
 c1632: 0.998 xEAe_B,N,N,C,D,S,1 - xEAe_B,N,N,C,D,S,2  = 0
 c1633: 0.998 xEAe_B,N,N,C,D,S,2 - xEAe_B,N,N,C,D,S,3  = 0
 c1634: 0.998 xEAe_B,N,N,C,D,S,3 - xEAe_B,N,N,C,D,S,4  = 0
 c1635: 0.998 xEAe_B,N,N,C,D,S,4 - xEAe_B,N,N,C,D,S,5  = 0
 c1636: 0.998 xEAe_B,N,N,C,D,P,1 - xEAe_B,N,N,C,D,P,2  = 0
 c1637: 0.998 xEAe_B,N,N,C,D,P,2 - xEAe_B,N,N,C,D,P,3  = 0
 c1638: 0.998 xEAe_B,N,N,C,D,P,3 - xEAe_B,N,N,C,D,P,4  = 0
 c1639: 0.998 xEAe_B,N,N,C,D,P,4 - xEAe_B,N,N,C,D,P,5  = 0
 c1640: 0.998 xEAe_B,N,N,C,D,M,1 - xEAe_B,N,N,C,D,M,2  = 0
 c1641: 0.998 xEAe_B,N,N,C,D,M,2 - xEAe_B,N,N,C,D,M,3  = 0
 c1642: 0.998 xEAe_B,N,N,C,D,M,3 - xEAe_B,N,N,C,D,M,4  = 0
 c1643: 0.998 xEAe_B,N,N,C,D,M,4 - xEAe_B,N,N,C,D,M,5  = 0
 c1644: 0.998 xEAe_B,N,N,C,D,L,1 - xEAe_B,N,N,C,D,L,2  = 0
 c1645: 0.998 xEAe_B,N,N,C,D,L,2 - xEAe_B,N,N,C,D,L,3  = 0
 c1646: 0.998 xEAe_B,N,N,C,D,L,3 - xEAe_B,N,N,C,D,L,4  = 0
 c1647: 0.998 xEAe_B,N,N,C,D,L,4 - xEAe_B,N,N,C,D,L,5  = 0
 c1648: 0.998 xEAe_B,N,N,C,W,S,1 - xEAe_B,N,N,C,W,S,2  = 0
 c1649: 0.998 xEAe_B,N,N,C,W,S,2 - xEAe_B,N,N,C,W,S,3  = 0
 c1650: 0.998 xEAe_B,N,N,C,W,S,3 - xEAe_B,N,N,C,W,S,4  = 0
 c1651: 0.998 xEAe_B,N,N,C,W,S,4 - xEAe_B,N,N,C,W,S,5  = 0
 c1652: 0.998 xEAe_B,N,N,C,W,P,1 - xEAe_B,N,N,C,W,P,2  = 0
 c1653: 0.998 xEAe_B,N,N,C,W,P,2 - xEAe_B,N,N,C,W,P,3  = 0
 c1654: 0.998 xEAe_B,N,N,C,W,P,3 - xEAe_B,N,N,C,W,P,4  = 0
 c1655: 0.998 xEAe_B,N,N,C,W,P,4 - xEAe_B,N,N,C,W,P,5  = 0
 c1656: 0.998 xEAe_B,N,N,C,W,M,1 - xEAe_B,N,N,C,W,M,2  = 0
 c1657: 0.998 xEAe_B,N,N,C,W,M,2 - xEAe_B,N,N,C,W,M,3  = 0
 c1658: 0.998 xEAe_B,N,N,C,W,M,3 - xEAe_B,N,N,C,W,M,4  = 0
 c1659: 0.998 xEAe_B,N,N,C,W,M,4 - xEAe_B,N,N,C,W,M,5  = 0
 c1660: 0.998 xEAe_B,N,N,C,W,L,1 - xEAe_B,N,N,C,W,L,2  = 0
 c1661: 0.998 xEAe_B,N,N,C,W,L,2 - xEAe_B,N,N,C,W,L,3  = 0
 c1662: 0.998 xEAe_B,N,N,C,W,L,3 - xEAe_B,N,N,C,W,L,4  = 0
 c1663: 0.998 xEAe_B,N,N,C,W,L,4 - xEAe_B,N,N,C,W,L,5  = 0
 c1664: 0.998 xEAe_B,N,N,C,C,S,1 - xEAe_B,N,N,C,C,S,2  = 0
 c1665: 0.998 xEAe_B,N,N,C,C,S,2 - xEAe_B,N,N,C,C,S,3  = 0
 c1666: 0.998 xEAe_B,N,N,C,C,S,3 - xEAe_B,N,N,C,C,S,4  = 0
 c1667: 0.998 xEAe_B,N,N,C,C,S,4 - xEAe_B,N,N,C,C,S,5  = 0
 c1668: 0.998 xEAe_B,N,N,C,C,P,1 - xEAe_B,N,N,C,C,P,2  = 0
 c1669: 0.998 xEAe_B,N,N,C,C,P,2 - xEAe_B,N,N,C,C,P,3  = 0
 c1670: 0.998 xEAe_B,N,N,C,C,P,3 - xEAe_B,N,N,C,C,P,4  = 0
 c1671: 0.998 xEAe_B,N,N,C,C,P,4 - xEAe_B,N,N,C,C,P,5  = 0
 c1672: 0.998 xEAe_B,N,N,C,C,M,1 - xEAe_B,N,N,C,C,M,2  = 0
 c1673: 0.998 xEAe_B,N,N,C,C,M,2 - xEAe_B,N,N,C,C,M,3  = 0
 c1674: 0.998 xEAe_B,N,N,C,C,M,3 - xEAe_B,N,N,C,C,M,4  = 0
 c1675: 0.998 xEAe_B,N,N,C,C,M,4 - xEAe_B,N,N,C,C,M,5  = 0
 c1676: 0.998 xEAe_B,N,N,C,C,L,1 - xEAe_B,N,N,C,C,L,2  = 0
 c1677: 0.998 xEAe_B,N,N,C,C,L,2 - xEAe_B,N,N,C,C,L,3  = 0
 c1678: 0.998 xEAe_B,N,N,C,C,L,3 - xEAe_B,N,N,C,C,L,4  = 0
 c1679: 0.998 xEAe_B,N,N,C,C,L,4 - xEAe_B,N,N,C,C,L,5  = 0
 c1680: 0.998 xEAe_B,N,N,C,I,M,1 - xEAe_B,N,N,C,I,M,2  = 0
 c1681: 0.998 xEAe_B,N,N,C,I,M,2 - xEAe_B,N,N,C,I,M,3  = 0
 c1682: 0.998 xEAe_B,N,N,C,I,M,3 - xEAe_B,N,N,C,I,M,4  = 0
 c1683: 0.998 xEAe_B,N,N,C,I,M,4 - xEAe_B,N,N,C,I,M,5  = 0
 c1684: 0.998 xEAe_B,N,N,C,I,L,1 - xEAe_B,N,N,C,I,L,2  = 0
 c1685: 0.998 xEAe_B,N,N,C,I,L,2 - xEAe_B,N,N,C,I,L,3  = 0
 c1686: 0.998 xEAe_B,N,N,C,I,L,3 - xEAe_B,N,N,C,I,L,4  = 0
 c1687: 0.998 xEAe_B,N,N,C,I,L,4 - xEAe_B,N,N,C,I,L,5  = 0
 c1688: 0.998 xEAe_B,N,N,C,A,P,1 - xEAe_B,N,N,C,A,P,2  = 0
 c1689: 0.998 xEAe_B,N,N,C,A,P,2 - xEAe_B,N,N,C,A,P,3  = 0
 c1690: 0.998 xEAe_B,N,N,C,A,P,3 - xEAe_B,N,N,C,A,P,4  = 0
 c1691: 0.998 xEAe_B,N,N,C,A,P,4 - xEAe_B,N,N,C,A,P,5  = 0
 c1692: 0.998 xEAe_B,N,N,C,A,M,1 - xEAe_B,N,N,C,A,M,2  = 0
 c1693: 0.998 xEAe_B,N,N,C,A,M,2 - xEAe_B,N,N,C,A,M,3  = 0
 c1694: 0.998 xEAe_B,N,N,C,A,M,3 - xEAe_B,N,N,C,A,M,4  = 0
 c1695: 0.998 xEAe_B,N,N,C,A,M,4 - xEAe_B,N,N,C,A,M,5  = 0
 c1696: 0.998 xEAe_B,N,N,C,A,L,1 - xEAe_B,N,N,C,A,L,2  = 0
 c1697: 0.998 xEAe_B,N,N,C,A,L,2 - xEAe_B,N,N,C,A,L,3  = 0
 c1698: 0.998 xEAe_B,N,N,C,A,L,3 - xEAe_B,N,N,C,A,L,4  = 0
 c1699: 0.998 xEAe_B,N,N,C,A,L,4 - xEAe_B,N,N,C,A,L,5  = 0
 c1700: 0.998 xEAe_B,N,N,C,L,M,1 - xEAe_B,N,N,C,L,M,2  = 0
 c1701: 0.998 xEAe_B,N,N,C,L,M,2 - xEAe_B,N,N,C,L,M,3  = 0
 c1702: 0.998 xEAe_B,N,N,C,L,M,3 - xEAe_B,N,N,C,L,M,4  = 0
 c1703: 0.998 xEAe_B,N,N,C,L,M,4 - xEAe_B,N,N,C,L,M,5  = 0
 c1704: 0.998 xEAe_B,N,N,C,L,L,1 - xEAe_B,N,N,C,L,L,2  = 0
 c1705: 0.998 xEAe_B,N,N,C,L,L,2 - xEAe_B,N,N,C,L,L,3  = 0
 c1706: 0.998 xEAe_B,N,N,C,L,L,3 - xEAe_B,N,N,C,L,L,4  = 0
 c1707: 0.998 xEAe_B,N,N,C,L,L,4 - xEAe_B,N,N,C,L,L,5  = 0
 c1708: xEAe_B,N,N,C,N,N,1 - xEAe_B,N,N,C,N,N,2  = 0
 c1709: xEAe_B,N,N,C,N,N,2 - xEAe_B,N,N,C,N,N,3  = 0
 c1710: xEAe_B,N,N,C,N,N,3 - xEAe_B,N,N,C,N,N,4  = 0
 c1711: xEAe_B,N,N,C,N,N,4 - xEAe_B,N,N,C,N,N,5  = 0
 c1712: xEAe_B,N,N,L,C,M,5  = 0
 c1713: xEAe_B,N,N,L,C,L,5  = 0
 c1714: xEAe_B,N,N,L,A,S,5  = 0
 c1715: xEAe_B,N,N,L,A,M,5  = 0
 c1716: xEAe_B,N,N,L,A,L,5  = 0
 c1717: xEAe_B,N,N,H,C,L,5  = 0
 c1718: xEAe_B,N,N,C,P,P,5  = 0
 c1719: xEAe_B,N,N,C,P,M,5  = 0
 c1720: xEAe_B,N,N,C,P,L,5  = 0
 c1721: xEAe_B,N,N,C,D,S,5  = 0
 c1722: xEAe_B,N,N,C,D,P,5  = 0
 c1723: xEAe_B,N,N,C,D,M,5  = 0
 c1724: xEAe_B,N,N,C,D,L,5  = 0
 c1725: xEAe_B,N,N,C,W,S,5  = 0
 c1726: xEAe_B,N,N,C,W,P,5  = 0
 c1727: xEAe_B,N,N,C,W,M,5  = 0
 c1728: xEAe_B,N,N,C,W,L,5  = 0
 c1729: xEAe_B,N,N,C,C,S,5  = 0
 c1730: xEAe_B,N,N,C,C,P,5  = 0
 c1731: xEAe_B,N,N,C,C,M,5  = 0
 c1732: xEAe_B,N,N,C,C,L,5  = 0
 c1733: xEAe_B,N,N,C,I,M,5  = 0
 c1734: xEAe_B,N,N,C,I,L,5  = 0
 c1735: xEAe_B,N,N,C,A,P,5  = 0
 c1736: xEAe_B,N,N,C,A,M,5  = 0
 c1737: xEAe_B,N,N,C,A,L,5  = 0
 c1738: xEAe_B,N,N,C,L,M,5  = 0
 c1739: xEAe_B,N,N,C,L,L,5  = 0
 c1740: xEAe_B,N,N,C,N,N,5  = 0
 c1741: fire_B,N,N,L,P,1,P + fire_B,N,N,L,P,1,D + fire_B,N,N,L,P,1,W
        + fire_B,N,N,L,P,1,C + fire_B,N,N,L,P,1,I + fire_B,N,N,L,P,1,A
        + fire_B,N,N,L,P,1,L + fire_B,N,N,L,P,1,N  = 0
 c1742: - 0.002 xEAr_B,N,N,L,P,2,1 + fire_B,N,N,L,P,2,P + fire_B,N,N,L,P,2,D
        + fire_B,N,N,L,P,2,W + fire_B,N,N,L,P,2,C + fire_B,N,N,L,P,2,I
        + fire_B,N,N,L,P,2,A + fire_B,N,N,L,P,2,L + fire_B,N,N,L,P,2,N  = 0
 c1743: - 0.002 xEAr_B,N,N,L,P,3,1 - 0.002 xEAr_B,N,N,L,P,3,2
        + fire_B,N,N,L,P,3,P + fire_B,N,N,L,P,3,D + fire_B,N,N,L,P,3,W
        + fire_B,N,N,L,P,3,C + fire_B,N,N,L,P,3,I + fire_B,N,N,L,P,3,A
        + fire_B,N,N,L,P,3,L + fire_B,N,N,L,P,3,N  = 0
 c1744: - 0.002 xEAr_B,N,N,L,P,4,1 - 0.002 xEAr_B,N,N,L,P,4,2
        - 0.002 xEAr_B,N,N,L,P,4,3 + fire_B,N,N,L,P,4,P + fire_B,N,N,L,P,4,D
        + fire_B,N,N,L,P,4,W + fire_B,N,N,L,P,4,C + fire_B,N,N,L,P,4,I
        + fire_B,N,N,L,P,4,A + fire_B,N,N,L,P,4,L + fire_B,N,N,L,P,4,N  = 0
 c1745: - 0.002 xEAr_B,N,N,L,P,5,1 - 0.002 xEAr_B,N,N,L,P,5,2
        - 0.002 xEAr_B,N,N,L,P,5,3 - 0.002 xEAr_B,N,N,L,P,5,4
        + fire_B,N,N,L,P,5,P + fire_B,N,N,L,P,5,D + fire_B,N,N,L,P,5,W
        + fire_B,N,N,L,P,5,C + fire_B,N,N,L,P,5,I + fire_B,N,N,L,P,5,A
        + fire_B,N,N,L,P,5,L + fire_B,N,N,L,P,5,N  = 0
 c1746: fire_B,N,N,L,D,1,P + fire_B,N,N,L,D,1,D + fire_B,N,N,L,D,1,W
        + fire_B,N,N,L,D,1,C + fire_B,N,N,L,D,1,I + fire_B,N,N,L,D,1,A
        + fire_B,N,N,L,D,1,L + fire_B,N,N,L,D,1,N  = 0
 c1747: - 0.002 xEAr_B,N,N,L,D,2,1 + fire_B,N,N,L,D,2,P + fire_B,N,N,L,D,2,D
        + fire_B,N,N,L,D,2,W + fire_B,N,N,L,D,2,C + fire_B,N,N,L,D,2,I
        + fire_B,N,N,L,D,2,A + fire_B,N,N,L,D,2,L + fire_B,N,N,L,D,2,N  = 0
 c1748: - 0.002 xEAr_B,N,N,L,D,3,1 - 0.002 xEAr_B,N,N,L,D,3,2
        + fire_B,N,N,L,D,3,P + fire_B,N,N,L,D,3,D + fire_B,N,N,L,D,3,W
        + fire_B,N,N,L,D,3,C + fire_B,N,N,L,D,3,I + fire_B,N,N,L,D,3,A
        + fire_B,N,N,L,D,3,L + fire_B,N,N,L,D,3,N  = 0
 c1749: - 0.002 xEAr_B,N,N,L,D,4,1 - 0.002 xEAr_B,N,N,L,D,4,2
        - 0.002 xEAr_B,N,N,L,D,4,3 + fire_B,N,N,L,D,4,P + fire_B,N,N,L,D,4,D
        + fire_B,N,N,L,D,4,W + fire_B,N,N,L,D,4,C + fire_B,N,N,L,D,4,I
        + fire_B,N,N,L,D,4,A + fire_B,N,N,L,D,4,L + fire_B,N,N,L,D,4,N  = 0
 c1750: - 0.002 xEAr_B,N,N,L,D,5,1 - 0.002 xEAr_B,N,N,L,D,5,2
        - 0.002 xEAr_B,N,N,L,D,5,3 - 0.002 xEAr_B,N,N,L,D,5,4
        + fire_B,N,N,L,D,5,P + fire_B,N,N,L,D,5,D + fire_B,N,N,L,D,5,W
        + fire_B,N,N,L,D,5,C + fire_B,N,N,L,D,5,I + fire_B,N,N,L,D,5,A
        + fire_B,N,N,L,D,5,L + fire_B,N,N,L,D,5,N  = 0
 c1751: fire_B,N,N,L,W,1,P + fire_B,N,N,L,W,1,D + fire_B,N,N,L,W,1,W
        + fire_B,N,N,L,W,1,C + fire_B,N,N,L,W,1,I + fire_B,N,N,L,W,1,A
        + fire_B,N,N,L,W,1,L + fire_B,N,N,L,W,1,N  = 0
 c1752: - 0.002 xEAr_B,N,N,L,W,2,1 + fire_B,N,N,L,W,2,P + fire_B,N,N,L,W,2,D
        + fire_B,N,N,L,W,2,W + fire_B,N,N,L,W,2,C + fire_B,N,N,L,W,2,I
        + fire_B,N,N,L,W,2,A + fire_B,N,N,L,W,2,L + fire_B,N,N,L,W,2,N  = 0
 c1753: - 0.002 xEAr_B,N,N,L,W,3,1 - 0.002 xEAr_B,N,N,L,W,3,2
        + fire_B,N,N,L,W,3,P + fire_B,N,N,L,W,3,D + fire_B,N,N,L,W,3,W
        + fire_B,N,N,L,W,3,C + fire_B,N,N,L,W,3,I + fire_B,N,N,L,W,3,A
        + fire_B,N,N,L,W,3,L + fire_B,N,N,L,W,3,N  = 0
 c1754: - 0.002 xEAr_B,N,N,L,W,4,1 - 0.002 xEAr_B,N,N,L,W,4,2
        - 0.002 xEAr_B,N,N,L,W,4,3 + fire_B,N,N,L,W,4,P + fire_B,N,N,L,W,4,D
        + fire_B,N,N,L,W,4,W + fire_B,N,N,L,W,4,C + fire_B,N,N,L,W,4,I
        + fire_B,N,N,L,W,4,A + fire_B,N,N,L,W,4,L + fire_B,N,N,L,W,4,N  = 0
 c1755: - 0.002 xEAr_B,N,N,L,W,5,1 - 0.002 xEAr_B,N,N,L,W,5,2
        - 0.002 xEAr_B,N,N,L,W,5,3 - 0.002 xEAr_B,N,N,L,W,5,4
        + fire_B,N,N,L,W,5,P + fire_B,N,N,L,W,5,D + fire_B,N,N,L,W,5,W
        + fire_B,N,N,L,W,5,C + fire_B,N,N,L,W,5,I + fire_B,N,N,L,W,5,A
        + fire_B,N,N,L,W,5,L + fire_B,N,N,L,W,5,N  = 0
 c1756: - 0.002 xNG_B,N,N,L,C,M,1 - 0.002 xNG_B,N,N,L,C,L,1
        - 0.002 xPB_B,N,N,L,C,M,0,1 - 0.002 xPB_B,N,N,L,C,M,1,1
        - 0.002 xPB_B,N,N,L,C,M,2,1 - 0.002 xPB_B,N,N,L,C,M,3,1
        - 0.002 xPB_B,N,N,L,C,M,4,1 - 0.002 xPB_B,N,N,L,C,L,0,1
        - 0.002 xPB_B,N,N,L,C,L,1,1 - 0.002 xPB_B,N,N,L,C,L,2,1
        - 0.002 xPB_B,N,N,L,C,L,3,1 - 0.002 xPB_B,N,N,L,C,L,4,1
        - 0.002 xGS_B,N,N,L,C,M,0,1 - 0.002 xGS_B,N,N,L,C,M,1,1
        - 0.002 xGS_B,N,N,L,C,M,2,1 - 0.002 xGS_B,N,N,L,C,M,3,1
        - 0.002 xGS_B,N,N,L,C,M,4,1 - 0.002 xGS_B,N,N,L,C,L,0,1
        - 0.002 xGS_B,N,N,L,C,L,1,1 - 0.002 xGS_B,N,N,L,C,L,2,1
        - 0.002 xGS_B,N,N,L,C,L,3,1 - 0.002 xGS_B,N,N,L,C,L,4,1
        - 0.002 xEAe_B,N,N,L,C,M,1 - 0.002 xEAe_B,N,N,L,C,L,1
        + fire_B,N,N,L,C,1,P + fire_B,N,N,L,C,1,D + fire_B,N,N,L,C,1,W
        + fire_B,N,N,L,C,1,C + fire_B,N,N,L,C,1,I + fire_B,N,N,L,C,1,A
        + fire_B,N,N,L,C,1,L + fire_B,N,N,L,C,1,N  = 0
 c1757: - 0.002 xNG_B,N,N,L,C,M,2 - 0.002 xNG_B,N,N,L,C,L,2
        - 0.002 xPB_B,N,N,L,C,M,0,2 - 0.002 xPB_B,N,N,L,C,M,1,2
        - 0.002 xPB_B,N,N,L,C,M,2,2 - 0.002 xPB_B,N,N,L,C,M,3,2
        - 0.002 xPB_B,N,N,L,C,M,4,2 - 0.002 xPB_B,N,N,L,C,L,0,2
        - 0.002 xPB_B,N,N,L,C,L,1,2 - 0.002 xPB_B,N,N,L,C,L,2,2
        - 0.002 xPB_B,N,N,L,C,L,3,2 - 0.002 xPB_B,N,N,L,C,L,4,2
        - 0.002 xGS_B,N,N,L,C,M,0,2 - 0.002 xGS_B,N,N,L,C,M,1,2
        - 0.002 xGS_B,N,N,L,C,M,2,2 - 0.002 xGS_B,N,N,L,C,M,3,2
        - 0.002 xGS_B,N,N,L,C,M,4,2 - 0.002 xGS_B,N,N,L,C,L,0,2
        - 0.002 xGS_B,N,N,L,C,L,1,2 - 0.002 xGS_B,N,N,L,C,L,2,2
        - 0.002 xGS_B,N,N,L,C,L,3,2 - 0.002 xGS_B,N,N,L,C,L,4,2
        - 0.002 xEAe_B,N,N,L,C,M,2 - 0.002 xEAe_B,N,N,L,C,L,2
        - 0.002 xEAr_B,N,N,L,C,2,1 + fire_B,N,N,L,C,2,P + fire_B,N,N,L,C,2,D
        + fire_B,N,N,L,C,2,W + fire_B,N,N,L,C,2,C + fire_B,N,N,L,C,2,I
        + fire_B,N,N,L,C,2,A + fire_B,N,N,L,C,2,L + fire_B,N,N,L,C,2,N  = 0
 c1758: - 0.002 xNG_B,N,N,L,C,M,3 - 0.002 xNG_B,N,N,L,C,L,3
        - 0.002 xPB_B,N,N,L,C,M,0,3 - 0.002 xPB_B,N,N,L,C,M,1,3
        - 0.002 xPB_B,N,N,L,C,M,2,3 - 0.002 xPB_B,N,N,L,C,M,3,3
        - 0.002 xPB_B,N,N,L,C,M,4,3 - 0.002 xPB_B,N,N,L,C,L,0,3
        - 0.002 xPB_B,N,N,L,C,L,1,3 - 0.002 xPB_B,N,N,L,C,L,2,3
        - 0.002 xPB_B,N,N,L,C,L,3,3 - 0.002 xPB_B,N,N,L,C,L,4,3
        - 0.002 xGS_B,N,N,L,C,M,0,3 - 0.002 xGS_B,N,N,L,C,M,1,3
        - 0.002 xGS_B,N,N,L,C,M,2,3 - 0.002 xGS_B,N,N,L,C,M,3,3
        - 0.002 xGS_B,N,N,L,C,M,4,3 - 0.002 xGS_B,N,N,L,C,L,0,3
        - 0.002 xGS_B,N,N,L,C,L,1,3 - 0.002 xGS_B,N,N,L,C,L,2,3
        - 0.002 xGS_B,N,N,L,C,L,3,3 - 0.002 xGS_B,N,N,L,C,L,4,3
        - 0.002 xEAe_B,N,N,L,C,M,3 - 0.002 xEAe_B,N,N,L,C,L,3
        - 0.002 xEAr_B,N,N,L,C,3,1 - 0.002 xEAr_B,N,N,L,C,3,2
        + fire_B,N,N,L,C,3,P + fire_B,N,N,L,C,3,D + fire_B,N,N,L,C,3,W
        + fire_B,N,N,L,C,3,C + fire_B,N,N,L,C,3,I + fire_B,N,N,L,C,3,A
        + fire_B,N,N,L,C,3,L + fire_B,N,N,L,C,3,N  = 0
 c1759: - 0.002 xNG_B,N,N,L,C,M,4 - 0.002 xNG_B,N,N,L,C,L,4
        - 0.002 xPB_B,N,N,L,C,M,0,4 - 0.002 xPB_B,N,N,L,C,M,1,4
        - 0.002 xPB_B,N,N,L,C,M,2,4 - 0.002 xPB_B,N,N,L,C,M,3,4
        - 0.002 xPB_B,N,N,L,C,M,4,4 - 0.002 xPB_B,N,N,L,C,L,0,4
        - 0.002 xPB_B,N,N,L,C,L,1,4 - 0.002 xPB_B,N,N,L,C,L,2,4
        - 0.002 xPB_B,N,N,L,C,L,3,4 - 0.002 xPB_B,N,N,L,C,L,4,4
        - 0.002 xGS_B,N,N,L,C,M,0,4 - 0.002 xGS_B,N,N,L,C,M,1,4
        - 0.002 xGS_B,N,N,L,C,M,2,4 - 0.002 xGS_B,N,N,L,C,M,3,4
        - 0.002 xGS_B,N,N,L,C,M,4,4 - 0.002 xGS_B,N,N,L,C,L,0,4
        - 0.002 xGS_B,N,N,L,C,L,1,4 - 0.002 xGS_B,N,N,L,C,L,2,4
        - 0.002 xGS_B,N,N,L,C,L,3,4 - 0.002 xGS_B,N,N,L,C,L,4,4
        - 0.002 xEAe_B,N,N,L,C,M,4 - 0.002 xEAe_B,N,N,L,C,L,4
        - 0.002 xEAr_B,N,N,L,C,4,1 - 0.002 xEAr_B,N,N,L,C,4,2
        - 0.002 xEAr_B,N,N,L,C,4,3 + fire_B,N,N,L,C,4,P + fire_B,N,N,L,C,4,D
        + fire_B,N,N,L,C,4,W + fire_B,N,N,L,C,4,C + fire_B,N,N,L,C,4,I
        + fire_B,N,N,L,C,4,A + fire_B,N,N,L,C,4,L + fire_B,N,N,L,C,4,N  = 0
 c1760: - 0.002 xNG_B,N,N,L,C,M,5 - 0.002 xNG_B,N,N,L,C,L,5
        - 0.002 xPB_B,N,N,L,C,M,0,5 - 0.002 xPB_B,N,N,L,C,M,1,5
        - 0.002 xPB_B,N,N,L,C,M,2,5 - 0.002 xPB_B,N,N,L,C,M,3,5
        - 0.002 xPB_B,N,N,L,C,M,4,5 - 0.002 xPB_B,N,N,L,C,L,0,5
        - 0.002 xPB_B,N,N,L,C,L,1,5 - 0.002 xPB_B,N,N,L,C,L,2,5
        - 0.002 xPB_B,N,N,L,C,L,3,5 - 0.002 xPB_B,N,N,L,C,L,4,5
        - 0.002 xGS_B,N,N,L,C,M,0,5 - 0.002 xGS_B,N,N,L,C,M,1,5
        - 0.002 xGS_B,N,N,L,C,M,2,5 - 0.002 xGS_B,N,N,L,C,M,3,5
        - 0.002 xGS_B,N,N,L,C,M,4,5 - 0.002 xGS_B,N,N,L,C,L,0,5
        - 0.002 xGS_B,N,N,L,C,L,1,5 - 0.002 xGS_B,N,N,L,C,L,2,5
        - 0.002 xGS_B,N,N,L,C,L,3,5 - 0.002 xGS_B,N,N,L,C,L,4,5
        - 0.002 xEAe_B,N,N,L,C,M,5 - 0.002 xEAe_B,N,N,L,C,L,5
        - 0.002 xEAr_B,N,N,L,C,5,1 - 0.002 xEAr_B,N,N,L,C,5,2
        - 0.002 xEAr_B,N,N,L,C,5,3 - 0.002 xEAr_B,N,N,L,C,5,4
        + fire_B,N,N,L,C,5,P + fire_B,N,N,L,C,5,D + fire_B,N,N,L,C,5,W
        + fire_B,N,N,L,C,5,C + fire_B,N,N,L,C,5,I + fire_B,N,N,L,C,5,A
        + fire_B,N,N,L,C,5,L + fire_B,N,N,L,C,5,N  = 0
 c1761: fire_B,N,N,L,I,1,P + fire_B,N,N,L,I,1,D + fire_B,N,N,L,I,1,W
        + fire_B,N,N,L,I,1,C + fire_B,N,N,L,I,1,I + fire_B,N,N,L,I,1,A
        + fire_B,N,N,L,I,1,L + fire_B,N,N,L,I,1,N  = 0
 c1762: - 0.002 xEAr_B,N,N,L,I,2,1 + fire_B,N,N,L,I,2,P + fire_B,N,N,L,I,2,D
        + fire_B,N,N,L,I,2,W + fire_B,N,N,L,I,2,C + fire_B,N,N,L,I,2,I
        + fire_B,N,N,L,I,2,A + fire_B,N,N,L,I,2,L + fire_B,N,N,L,I,2,N  = 0
 c1763: - 0.002 xEAr_B,N,N,L,I,3,1 - 0.002 xEAr_B,N,N,L,I,3,2
        + fire_B,N,N,L,I,3,P + fire_B,N,N,L,I,3,D + fire_B,N,N,L,I,3,W
        + fire_B,N,N,L,I,3,C + fire_B,N,N,L,I,3,I + fire_B,N,N,L,I,3,A
        + fire_B,N,N,L,I,3,L + fire_B,N,N,L,I,3,N  = 0
 c1764: - 0.002 xEAr_B,N,N,L,I,4,1 - 0.002 xEAr_B,N,N,L,I,4,2
        - 0.002 xEAr_B,N,N,L,I,4,3 + fire_B,N,N,L,I,4,P + fire_B,N,N,L,I,4,D
        + fire_B,N,N,L,I,4,W + fire_B,N,N,L,I,4,C + fire_B,N,N,L,I,4,I
        + fire_B,N,N,L,I,4,A + fire_B,N,N,L,I,4,L + fire_B,N,N,L,I,4,N  = 0
 c1765: - 0.002 xEAr_B,N,N,L,I,5,1 - 0.002 xEAr_B,N,N,L,I,5,2
        - 0.002 xEAr_B,N,N,L,I,5,3 - 0.002 xEAr_B,N,N,L,I,5,4
        + fire_B,N,N,L,I,5,P + fire_B,N,N,L,I,5,D + fire_B,N,N,L,I,5,W
        + fire_B,N,N,L,I,5,C + fire_B,N,N,L,I,5,I + fire_B,N,N,L,I,5,A
        + fire_B,N,N,L,I,5,L + fire_B,N,N,L,I,5,N  = 0
 c1766: - 0.002 xNG_B,N,N,L,A,S,1 - 0.002 xNG_B,N,N,L,A,M,1
        - 0.002 xNG_B,N,N,L,A,L,1 - 0.002 xPB_B,N,N,L,A,S,0,1
        - 0.002 xPB_B,N,N,L,A,S,1,1 - 0.002 xPB_B,N,N,L,A,S,2,1
        - 0.002 xPB_B,N,N,L,A,S,3,1 - 0.002 xPB_B,N,N,L,A,S,4,1
        - 0.002 xPB_B,N,N,L,A,M,0,1 - 0.002 xPB_B,N,N,L,A,M,1,1
        - 0.002 xPB_B,N,N,L,A,M,2,1 - 0.002 xPB_B,N,N,L,A,M,3,1
        - 0.002 xPB_B,N,N,L,A,M,4,1 - 0.002 xPB_B,N,N,L,A,L,0,1
        - 0.002 xPB_B,N,N,L,A,L,1,1 - 0.002 xPB_B,N,N,L,A,L,2,1
        - 0.002 xPB_B,N,N,L,A,L,3,1 - 0.002 xPB_B,N,N,L,A,L,4,1
        - 0.002 xGS_B,N,N,L,A,S,0,1 - 0.002 xGS_B,N,N,L,A,S,1,1
        - 0.002 xGS_B,N,N,L,A,S,2,1 - 0.002 xGS_B,N,N,L,A,S,3,1
        - 0.002 xGS_B,N,N,L,A,S,4,1 - 0.002 xGS_B,N,N,L,A,M,0,1
        - 0.002 xGS_B,N,N,L,A,M,1,1 - 0.002 xGS_B,N,N,L,A,M,2,1
        - 0.002 xGS_B,N,N,L,A,M,3,1 - 0.002 xGS_B,N,N,L,A,M,4,1
        - 0.002 xGS_B,N,N,L,A,L,0,1 - 0.002 xGS_B,N,N,L,A,L,1,1
        - 0.002 xGS_B,N,N,L,A,L,2,1 - 0.002 xGS_B,N,N,L,A,L,3,1
        - 0.002 xGS_B,N,N,L,A,L,4,1 - 0.002 xEAe_B,N,N,L,A,S,1
        - 0.002 xEAe_B,N,N,L,A,M,1 - 0.002 xEAe_B,N,N,L,A,L,1
        + fire_B,N,N,L,A,1,P + fire_B,N,N,L,A,1,D + fire_B,N,N,L,A,1,W
        + fire_B,N,N,L,A,1,C + fire_B,N,N,L,A,1,I + fire_B,N,N,L,A,1,A
        + fire_B,N,N,L,A,1,L + fire_B,N,N,L,A,1,N  = 0
 c1767: - 0.002 xNG_B,N,N,L,A,S,2 - 0.002 xNG_B,N,N,L,A,M,2
        - 0.002 xNG_B,N,N,L,A,L,2 - 0.002 xPB_B,N,N,L,A,S,0,2
        - 0.002 xPB_B,N,N,L,A,S,1,2 - 0.002 xPB_B,N,N,L,A,S,2,2
        - 0.002 xPB_B,N,N,L,A,S,3,2 - 0.002 xPB_B,N,N,L,A,S,4,2
        - 0.002 xPB_B,N,N,L,A,M,0,2 - 0.002 xPB_B,N,N,L,A,M,1,2
        - 0.002 xPB_B,N,N,L,A,M,2,2 - 0.002 xPB_B,N,N,L,A,M,3,2
        - 0.002 xPB_B,N,N,L,A,M,4,2 - 0.002 xPB_B,N,N,L,A,L,0,2
        - 0.002 xPB_B,N,N,L,A,L,1,2 - 0.002 xPB_B,N,N,L,A,L,2,2
        - 0.002 xPB_B,N,N,L,A,L,3,2 - 0.002 xPB_B,N,N,L,A,L,4,2
        - 0.002 xGS_B,N,N,L,A,S,0,2 - 0.002 xGS_B,N,N,L,A,S,1,2
        - 0.002 xGS_B,N,N,L,A,S,2,2 - 0.002 xGS_B,N,N,L,A,S,3,2
        - 0.002 xGS_B,N,N,L,A,S,4,2 - 0.002 xGS_B,N,N,L,A,M,0,2
        - 0.002 xGS_B,N,N,L,A,M,1,2 - 0.002 xGS_B,N,N,L,A,M,2,2
        - 0.002 xGS_B,N,N,L,A,M,3,2 - 0.002 xGS_B,N,N,L,A,M,4,2
        - 0.002 xGS_B,N,N,L,A,L,0,2 - 0.002 xGS_B,N,N,L,A,L,1,2
        - 0.002 xGS_B,N,N,L,A,L,2,2 - 0.002 xGS_B,N,N,L,A,L,3,2
        - 0.002 xGS_B,N,N,L,A,L,4,2 - 0.002 xEAe_B,N,N,L,A,S,2
        - 0.002 xEAe_B,N,N,L,A,M,2 - 0.002 xEAe_B,N,N,L,A,L,2
        - 0.002 xEAr_B,N,N,L,A,2,1 + fire_B,N,N,L,A,2,P + fire_B,N,N,L,A,2,D
        + fire_B,N,N,L,A,2,W + fire_B,N,N,L,A,2,C + fire_B,N,N,L,A,2,I
        + fire_B,N,N,L,A,2,A + fire_B,N,N,L,A,2,L + fire_B,N,N,L,A,2,N  = 0
 c1768: - 0.002 xNG_B,N,N,L,A,S,3 - 0.002 xNG_B,N,N,L,A,M,3
        - 0.002 xNG_B,N,N,L,A,L,3 - 0.002 xPB_B,N,N,L,A,S,0,3
        - 0.002 xPB_B,N,N,L,A,S,1,3 - 0.002 xPB_B,N,N,L,A,S,2,3
        - 0.002 xPB_B,N,N,L,A,S,3,3 - 0.002 xPB_B,N,N,L,A,S,4,3
        - 0.002 xPB_B,N,N,L,A,M,0,3 - 0.002 xPB_B,N,N,L,A,M,1,3
        - 0.002 xPB_B,N,N,L,A,M,2,3 - 0.002 xPB_B,N,N,L,A,M,3,3
        - 0.002 xPB_B,N,N,L,A,M,4,3 - 0.002 xPB_B,N,N,L,A,L,0,3
        - 0.002 xPB_B,N,N,L,A,L,1,3 - 0.002 xPB_B,N,N,L,A,L,2,3
        - 0.002 xPB_B,N,N,L,A,L,3,3 - 0.002 xPB_B,N,N,L,A,L,4,3
        - 0.002 xGS_B,N,N,L,A,S,0,3 - 0.002 xGS_B,N,N,L,A,S,1,3
        - 0.002 xGS_B,N,N,L,A,S,2,3 - 0.002 xGS_B,N,N,L,A,S,3,3
        - 0.002 xGS_B,N,N,L,A,S,4,3 - 0.002 xGS_B,N,N,L,A,M,0,3
        - 0.002 xGS_B,N,N,L,A,M,1,3 - 0.002 xGS_B,N,N,L,A,M,2,3
        - 0.002 xGS_B,N,N,L,A,M,3,3 - 0.002 xGS_B,N,N,L,A,M,4,3
        - 0.002 xGS_B,N,N,L,A,L,0,3 - 0.002 xGS_B,N,N,L,A,L,1,3
        - 0.002 xGS_B,N,N,L,A,L,2,3 - 0.002 xGS_B,N,N,L,A,L,3,3
        - 0.002 xGS_B,N,N,L,A,L,4,3 - 0.002 xEAe_B,N,N,L,A,S,3
        - 0.002 xEAe_B,N,N,L,A,M,3 - 0.002 xEAe_B,N,N,L,A,L,3
        - 0.002 xEAr_B,N,N,L,A,3,1 - 0.002 xEAr_B,N,N,L,A,3,2
        + fire_B,N,N,L,A,3,P + fire_B,N,N,L,A,3,D + fire_B,N,N,L,A,3,W
        + fire_B,N,N,L,A,3,C + fire_B,N,N,L,A,3,I + fire_B,N,N,L,A,3,A
        + fire_B,N,N,L,A,3,L + fire_B,N,N,L,A,3,N  = 0
 c1769: - 0.002 xNG_B,N,N,L,A,S,4 - 0.002 xNG_B,N,N,L,A,M,4
        - 0.002 xNG_B,N,N,L,A,L,4 - 0.002 xPB_B,N,N,L,A,S,0,4
        - 0.002 xPB_B,N,N,L,A,S,1,4 - 0.002 xPB_B,N,N,L,A,S,2,4
        - 0.002 xPB_B,N,N,L,A,S,3,4 - 0.002 xPB_B,N,N,L,A,S,4,4
        - 0.002 xPB_B,N,N,L,A,M,0,4 - 0.002 xPB_B,N,N,L,A,M,1,4
        - 0.002 xPB_B,N,N,L,A,M,2,4 - 0.002 xPB_B,N,N,L,A,M,3,4
        - 0.002 xPB_B,N,N,L,A,M,4,4 - 0.002 xPB_B,N,N,L,A,L,0,4
        - 0.002 xPB_B,N,N,L,A,L,1,4 - 0.002 xPB_B,N,N,L,A,L,2,4
        - 0.002 xPB_B,N,N,L,A,L,3,4 - 0.002 xPB_B,N,N,L,A,L,4,4
        - 0.002 xGS_B,N,N,L,A,S,0,4 - 0.002 xGS_B,N,N,L,A,S,1,4
        - 0.002 xGS_B,N,N,L,A,S,2,4 - 0.002 xGS_B,N,N,L,A,S,3,4
        - 0.002 xGS_B,N,N,L,A,S,4,4 - 0.002 xGS_B,N,N,L,A,M,0,4
        - 0.002 xGS_B,N,N,L,A,M,1,4 - 0.002 xGS_B,N,N,L,A,M,2,4
        - 0.002 xGS_B,N,N,L,A,M,3,4 - 0.002 xGS_B,N,N,L,A,M,4,4
        - 0.002 xGS_B,N,N,L,A,L,0,4 - 0.002 xGS_B,N,N,L,A,L,1,4
        - 0.002 xGS_B,N,N,L,A,L,2,4 - 0.002 xGS_B,N,N,L,A,L,3,4
        - 0.002 xGS_B,N,N,L,A,L,4,4 - 0.002 xEAe_B,N,N,L,A,S,4
        - 0.002 xEAe_B,N,N,L,A,M,4 - 0.002 xEAe_B,N,N,L,A,L,4
        - 0.002 xEAr_B,N,N,L,A,4,1 - 0.002 xEAr_B,N,N,L,A,4,2
        - 0.002 xEAr_B,N,N,L,A,4,3 + fire_B,N,N,L,A,4,P + fire_B,N,N,L,A,4,D
        + fire_B,N,N,L,A,4,W + fire_B,N,N,L,A,4,C + fire_B,N,N,L,A,4,I
        + fire_B,N,N,L,A,4,A + fire_B,N,N,L,A,4,L + fire_B,N,N,L,A,4,N  = 0
 c1770: - 0.002 xNG_B,N,N,L,A,S,5 - 0.002 xNG_B,N,N,L,A,M,5
        - 0.002 xNG_B,N,N,L,A,L,5 - 0.002 xPB_B,N,N,L,A,S,0,5
        - 0.002 xPB_B,N,N,L,A,S,1,5 - 0.002 xPB_B,N,N,L,A,S,2,5
        - 0.002 xPB_B,N,N,L,A,S,3,5 - 0.002 xPB_B,N,N,L,A,S,4,5
        - 0.002 xPB_B,N,N,L,A,M,0,5 - 0.002 xPB_B,N,N,L,A,M,1,5
        - 0.002 xPB_B,N,N,L,A,M,2,5 - 0.002 xPB_B,N,N,L,A,M,3,5
        - 0.002 xPB_B,N,N,L,A,M,4,5 - 0.002 xPB_B,N,N,L,A,L,0,5
        - 0.002 xPB_B,N,N,L,A,L,1,5 - 0.002 xPB_B,N,N,L,A,L,2,5
        - 0.002 xPB_B,N,N,L,A,L,3,5 - 0.002 xPB_B,N,N,L,A,L,4,5
        - 0.002 xGS_B,N,N,L,A,S,0,5 - 0.002 xGS_B,N,N,L,A,S,1,5
        - 0.002 xGS_B,N,N,L,A,S,2,5 - 0.002 xGS_B,N,N,L,A,S,3,5
        - 0.002 xGS_B,N,N,L,A,S,4,5 - 0.002 xGS_B,N,N,L,A,M,0,5
        - 0.002 xGS_B,N,N,L,A,M,1,5 - 0.002 xGS_B,N,N,L,A,M,2,5
        - 0.002 xGS_B,N,N,L,A,M,3,5 - 0.002 xGS_B,N,N,L,A,M,4,5
        - 0.002 xGS_B,N,N,L,A,L,0,5 - 0.002 xGS_B,N,N,L,A,L,1,5
        - 0.002 xGS_B,N,N,L,A,L,2,5 - 0.002 xGS_B,N,N,L,A,L,3,5
        - 0.002 xGS_B,N,N,L,A,L,4,5 - 0.002 xEAe_B,N,N,L,A,S,5
        - 0.002 xEAe_B,N,N,L,A,M,5 - 0.002 xEAe_B,N,N,L,A,L,5
        - 0.002 xEAr_B,N,N,L,A,5,1 - 0.002 xEAr_B,N,N,L,A,5,2
        - 0.002 xEAr_B,N,N,L,A,5,3 - 0.002 xEAr_B,N,N,L,A,5,4
        + fire_B,N,N,L,A,5,P + fire_B,N,N,L,A,5,D + fire_B,N,N,L,A,5,W
        + fire_B,N,N,L,A,5,C + fire_B,N,N,L,A,5,I + fire_B,N,N,L,A,5,A
        + fire_B,N,N,L,A,5,L + fire_B,N,N,L,A,5,N  = 0
 c1771: fire_B,N,N,L,L,1,P + fire_B,N,N,L,L,1,D + fire_B,N,N,L,L,1,W
        + fire_B,N,N,L,L,1,C + fire_B,N,N,L,L,1,I + fire_B,N,N,L,L,1,A
        + fire_B,N,N,L,L,1,L + fire_B,N,N,L,L,1,N  = 0
 c1772: - 0.002 xEAr_B,N,N,L,L,2,1 + fire_B,N,N,L,L,2,P + fire_B,N,N,L,L,2,D
        + fire_B,N,N,L,L,2,W + fire_B,N,N,L,L,2,C + fire_B,N,N,L,L,2,I
        + fire_B,N,N,L,L,2,A + fire_B,N,N,L,L,2,L + fire_B,N,N,L,L,2,N  = 0
 c1773: - 0.002 xEAr_B,N,N,L,L,3,1 - 0.002 xEAr_B,N,N,L,L,3,2
        + fire_B,N,N,L,L,3,P + fire_B,N,N,L,L,3,D + fire_B,N,N,L,L,3,W
        + fire_B,N,N,L,L,3,C + fire_B,N,N,L,L,3,I + fire_B,N,N,L,L,3,A
        + fire_B,N,N,L,L,3,L + fire_B,N,N,L,L,3,N  = 0
 c1774: - 0.002 xEAr_B,N,N,L,L,4,1 - 0.002 xEAr_B,N,N,L,L,4,2
        - 0.002 xEAr_B,N,N,L,L,4,3 + fire_B,N,N,L,L,4,P + fire_B,N,N,L,L,4,D
        + fire_B,N,N,L,L,4,W + fire_B,N,N,L,L,4,C + fire_B,N,N,L,L,4,I
        + fire_B,N,N,L,L,4,A + fire_B,N,N,L,L,4,L + fire_B,N,N,L,L,4,N  = 0
 c1775: - 0.002 xEAr_B,N,N,L,L,5,1 - 0.002 xEAr_B,N,N,L,L,5,2
        - 0.002 xEAr_B,N,N,L,L,5,3 - 0.002 xEAr_B,N,N,L,L,5,4
        + fire_B,N,N,L,L,5,P + fire_B,N,N,L,L,5,D + fire_B,N,N,L,L,5,W
        + fire_B,N,N,L,L,5,C + fire_B,N,N,L,L,5,I + fire_B,N,N,L,L,5,A
        + fire_B,N,N,L,L,5,L + fire_B,N,N,L,L,5,N  = 0
 c1776: fire_B,N,N,L,N,1,P + fire_B,N,N,L,N,1,D + fire_B,N,N,L,N,1,W
        + fire_B,N,N,L,N,1,C + fire_B,N,N,L,N,1,I + fire_B,N,N,L,N,1,A
        + fire_B,N,N,L,N,1,L + fire_B,N,N,L,N,1,N  = 0
 c1777: fire_B,N,N,L,N,2,P + fire_B,N,N,L,N,2,D + fire_B,N,N,L,N,2,W
        + fire_B,N,N,L,N,2,C + fire_B,N,N,L,N,2,I + fire_B,N,N,L,N,2,A
        + fire_B,N,N,L,N,2,L + fire_B,N,N,L,N,2,N  = 0
 c1778: fire_B,N,N,L,N,3,P + fire_B,N,N,L,N,3,D + fire_B,N,N,L,N,3,W
        + fire_B,N,N,L,N,3,C + fire_B,N,N,L,N,3,I + fire_B,N,N,L,N,3,A
        + fire_B,N,N,L,N,3,L + fire_B,N,N,L,N,3,N  = 0
 c1779: fire_B,N,N,L,N,4,P + fire_B,N,N,L,N,4,D + fire_B,N,N,L,N,4,W
        + fire_B,N,N,L,N,4,C + fire_B,N,N,L,N,4,I + fire_B,N,N,L,N,4,A
        + fire_B,N,N,L,N,4,L + fire_B,N,N,L,N,4,N  = 0
 c1780: fire_B,N,N,L,N,5,P + fire_B,N,N,L,N,5,D + fire_B,N,N,L,N,5,W
        + fire_B,N,N,L,N,5,C + fire_B,N,N,L,N,5,I + fire_B,N,N,L,N,5,A
        + fire_B,N,N,L,N,5,L + fire_B,N,N,L,N,5,N  = 0
 c1781: fire_B,N,N,H,P,1,P + fire_B,N,N,H,P,1,D + fire_B,N,N,H,P,1,W
        + fire_B,N,N,H,P,1,C + fire_B,N,N,H,P,1,I + fire_B,N,N,H,P,1,A
        + fire_B,N,N,H,P,1,L + fire_B,N,N,H,P,1,N  = 0
 c1782: - 0.002 xEAr_B,N,N,H,P,2,1 + fire_B,N,N,H,P,2,P + fire_B,N,N,H,P,2,D
        + fire_B,N,N,H,P,2,W + fire_B,N,N,H,P,2,C + fire_B,N,N,H,P,2,I
        + fire_B,N,N,H,P,2,A + fire_B,N,N,H,P,2,L + fire_B,N,N,H,P,2,N  = 0
 c1783: - 0.002 xEAr_B,N,N,H,P,3,1 - 0.002 xEAr_B,N,N,H,P,3,2
        + fire_B,N,N,H,P,3,P + fire_B,N,N,H,P,3,D + fire_B,N,N,H,P,3,W
        + fire_B,N,N,H,P,3,C + fire_B,N,N,H,P,3,I + fire_B,N,N,H,P,3,A
        + fire_B,N,N,H,P,3,L + fire_B,N,N,H,P,3,N  = 0
 c1784: - 0.002 xEAr_B,N,N,H,P,4,1 - 0.002 xEAr_B,N,N,H,P,4,2
        - 0.002 xEAr_B,N,N,H,P,4,3 + fire_B,N,N,H,P,4,P + fire_B,N,N,H,P,4,D
        + fire_B,N,N,H,P,4,W + fire_B,N,N,H,P,4,C + fire_B,N,N,H,P,4,I
        + fire_B,N,N,H,P,4,A + fire_B,N,N,H,P,4,L + fire_B,N,N,H,P,4,N  = 0
 c1785: - 0.002 xEAr_B,N,N,H,P,5,1 - 0.002 xEAr_B,N,N,H,P,5,2
        - 0.002 xEAr_B,N,N,H,P,5,3 - 0.002 xEAr_B,N,N,H,P,5,4
        + fire_B,N,N,H,P,5,P + fire_B,N,N,H,P,5,D + fire_B,N,N,H,P,5,W
        + fire_B,N,N,H,P,5,C + fire_B,N,N,H,P,5,I + fire_B,N,N,H,P,5,A
        + fire_B,N,N,H,P,5,L + fire_B,N,N,H,P,5,N  = 0
 c1786: fire_B,N,N,H,D,1,P + fire_B,N,N,H,D,1,D + fire_B,N,N,H,D,1,W
        + fire_B,N,N,H,D,1,C + fire_B,N,N,H,D,1,I + fire_B,N,N,H,D,1,A
        + fire_B,N,N,H,D,1,L + fire_B,N,N,H,D,1,N  = 0
 c1787: - 0.002 xEAr_B,N,N,H,D,2,1 + fire_B,N,N,H,D,2,P + fire_B,N,N,H,D,2,D
        + fire_B,N,N,H,D,2,W + fire_B,N,N,H,D,2,C + fire_B,N,N,H,D,2,I
        + fire_B,N,N,H,D,2,A + fire_B,N,N,H,D,2,L + fire_B,N,N,H,D,2,N  = 0
 c1788: - 0.002 xEAr_B,N,N,H,D,3,1 - 0.002 xEAr_B,N,N,H,D,3,2
        + fire_B,N,N,H,D,3,P + fire_B,N,N,H,D,3,D + fire_B,N,N,H,D,3,W
        + fire_B,N,N,H,D,3,C + fire_B,N,N,H,D,3,I + fire_B,N,N,H,D,3,A
        + fire_B,N,N,H,D,3,L + fire_B,N,N,H,D,3,N  = 0
 c1789: - 0.002 xEAr_B,N,N,H,D,4,1 - 0.002 xEAr_B,N,N,H,D,4,2
        - 0.002 xEAr_B,N,N,H,D,4,3 + fire_B,N,N,H,D,4,P + fire_B,N,N,H,D,4,D
        + fire_B,N,N,H,D,4,W + fire_B,N,N,H,D,4,C + fire_B,N,N,H,D,4,I
        + fire_B,N,N,H,D,4,A + fire_B,N,N,H,D,4,L + fire_B,N,N,H,D,4,N  = 0
 c1790: - 0.002 xEAr_B,N,N,H,D,5,1 - 0.002 xEAr_B,N,N,H,D,5,2
        - 0.002 xEAr_B,N,N,H,D,5,3 - 0.002 xEAr_B,N,N,H,D,5,4
        + fire_B,N,N,H,D,5,P + fire_B,N,N,H,D,5,D + fire_B,N,N,H,D,5,W
        + fire_B,N,N,H,D,5,C + fire_B,N,N,H,D,5,I + fire_B,N,N,H,D,5,A
        + fire_B,N,N,H,D,5,L + fire_B,N,N,H,D,5,N  = 0
 c1791: fire_B,N,N,H,W,1,P + fire_B,N,N,H,W,1,D + fire_B,N,N,H,W,1,W
        + fire_B,N,N,H,W,1,C + fire_B,N,N,H,W,1,I + fire_B,N,N,H,W,1,A
        + fire_B,N,N,H,W,1,L + fire_B,N,N,H,W,1,N  = 0
 c1792: - 0.002 xEAr_B,N,N,H,W,2,1 + fire_B,N,N,H,W,2,P + fire_B,N,N,H,W,2,D
        + fire_B,N,N,H,W,2,W + fire_B,N,N,H,W,2,C + fire_B,N,N,H,W,2,I
        + fire_B,N,N,H,W,2,A + fire_B,N,N,H,W,2,L + fire_B,N,N,H,W,2,N  = 0
 c1793: - 0.002 xEAr_B,N,N,H,W,3,1 - 0.002 xEAr_B,N,N,H,W,3,2
        + fire_B,N,N,H,W,3,P + fire_B,N,N,H,W,3,D + fire_B,N,N,H,W,3,W
        + fire_B,N,N,H,W,3,C + fire_B,N,N,H,W,3,I + fire_B,N,N,H,W,3,A
        + fire_B,N,N,H,W,3,L + fire_B,N,N,H,W,3,N  = 0
 c1794: - 0.002 xEAr_B,N,N,H,W,4,1 - 0.002 xEAr_B,N,N,H,W,4,2
        - 0.002 xEAr_B,N,N,H,W,4,3 + fire_B,N,N,H,W,4,P + fire_B,N,N,H,W,4,D
        + fire_B,N,N,H,W,4,W + fire_B,N,N,H,W,4,C + fire_B,N,N,H,W,4,I
        + fire_B,N,N,H,W,4,A + fire_B,N,N,H,W,4,L + fire_B,N,N,H,W,4,N  = 0
 c1795: - 0.002 xEAr_B,N,N,H,W,5,1 - 0.002 xEAr_B,N,N,H,W,5,2
        - 0.002 xEAr_B,N,N,H,W,5,3 - 0.002 xEAr_B,N,N,H,W,5,4
        + fire_B,N,N,H,W,5,P + fire_B,N,N,H,W,5,D + fire_B,N,N,H,W,5,W
        + fire_B,N,N,H,W,5,C + fire_B,N,N,H,W,5,I + fire_B,N,N,H,W,5,A
        + fire_B,N,N,H,W,5,L + fire_B,N,N,H,W,5,N  = 0
 c1796: - 0.002 xNG_B,N,N,H,C,L,1 - 0.002 xPB_B,N,N,H,C,L,0,1
        - 0.002 xPB_B,N,N,H,C,L,1,1 - 0.002 xPB_B,N,N,H,C,L,2,1
        - 0.002 xPB_B,N,N,H,C,L,3,1 - 0.002 xPB_B,N,N,H,C,L,4,1
        - 0.002 xGS_B,N,N,H,C,L,0,1 - 0.002 xGS_B,N,N,H,C,L,1,1
        - 0.002 xGS_B,N,N,H,C,L,2,1 - 0.002 xGS_B,N,N,H,C,L,3,1
        - 0.002 xGS_B,N,N,H,C,L,4,1 - 0.002 xEAe_B,N,N,H,C,L,1
        + fire_B,N,N,H,C,1,P + fire_B,N,N,H,C,1,D + fire_B,N,N,H,C,1,W
        + fire_B,N,N,H,C,1,C + fire_B,N,N,H,C,1,I + fire_B,N,N,H,C,1,A
        + fire_B,N,N,H,C,1,L + fire_B,N,N,H,C,1,N  = 0
 c1797: - 0.002 xNG_B,N,N,H,C,L,2 - 0.002 xPB_B,N,N,H,C,L,0,2
        - 0.002 xPB_B,N,N,H,C,L,1,2 - 0.002 xPB_B,N,N,H,C,L,2,2
        - 0.002 xPB_B,N,N,H,C,L,3,2 - 0.002 xPB_B,N,N,H,C,L,4,2
        - 0.002 xGS_B,N,N,H,C,L,0,2 - 0.002 xGS_B,N,N,H,C,L,1,2
        - 0.002 xGS_B,N,N,H,C,L,2,2 - 0.002 xGS_B,N,N,H,C,L,3,2
        - 0.002 xGS_B,N,N,H,C,L,4,2 - 0.002 xEAe_B,N,N,H,C,L,2
        - 0.002 xEAr_B,N,N,H,C,2,1 + fire_B,N,N,H,C,2,P + fire_B,N,N,H,C,2,D
        + fire_B,N,N,H,C,2,W + fire_B,N,N,H,C,2,C + fire_B,N,N,H,C,2,I
        + fire_B,N,N,H,C,2,A + fire_B,N,N,H,C,2,L + fire_B,N,N,H,C,2,N  = 0
 c1798: - 0.002 xNG_B,N,N,H,C,L,3 - 0.002 xPB_B,N,N,H,C,L,0,3
        - 0.002 xPB_B,N,N,H,C,L,1,3 - 0.002 xPB_B,N,N,H,C,L,2,3
        - 0.002 xPB_B,N,N,H,C,L,3,3 - 0.002 xPB_B,N,N,H,C,L,4,3
        - 0.002 xGS_B,N,N,H,C,L,0,3 - 0.002 xGS_B,N,N,H,C,L,1,3
        - 0.002 xGS_B,N,N,H,C,L,2,3 - 0.002 xGS_B,N,N,H,C,L,3,3
        - 0.002 xGS_B,N,N,H,C,L,4,3 - 0.002 xEAe_B,N,N,H,C,L,3
        - 0.002 xEAr_B,N,N,H,C,3,1 - 0.002 xEAr_B,N,N,H,C,3,2
        + fire_B,N,N,H,C,3,P + fire_B,N,N,H,C,3,D + fire_B,N,N,H,C,3,W
        + fire_B,N,N,H,C,3,C + fire_B,N,N,H,C,3,I + fire_B,N,N,H,C,3,A
        + fire_B,N,N,H,C,3,L + fire_B,N,N,H,C,3,N  = 0
 c1799: - 0.002 xNG_B,N,N,H,C,L,4 - 0.002 xPB_B,N,N,H,C,L,0,4
        - 0.002 xPB_B,N,N,H,C,L,1,4 - 0.002 xPB_B,N,N,H,C,L,2,4
        - 0.002 xPB_B,N,N,H,C,L,3,4 - 0.002 xPB_B,N,N,H,C,L,4,4
        - 0.002 xGS_B,N,N,H,C,L,0,4 - 0.002 xGS_B,N,N,H,C,L,1,4
        - 0.002 xGS_B,N,N,H,C,L,2,4 - 0.002 xGS_B,N,N,H,C,L,3,4
        - 0.002 xGS_B,N,N,H,C,L,4,4 - 0.002 xEAe_B,N,N,H,C,L,4
        - 0.002 xEAr_B,N,N,H,C,4,1 - 0.002 xEAr_B,N,N,H,C,4,2
        - 0.002 xEAr_B,N,N,H,C,4,3 + fire_B,N,N,H,C,4,P + fire_B,N,N,H,C,4,D
        + fire_B,N,N,H,C,4,W + fire_B,N,N,H,C,4,C + fire_B,N,N,H,C,4,I
        + fire_B,N,N,H,C,4,A + fire_B,N,N,H,C,4,L + fire_B,N,N,H,C,4,N  = 0
 c1800: - 0.002 xNG_B,N,N,H,C,L,5 - 0.002 xPB_B,N,N,H,C,L,0,5
        - 0.002 xPB_B,N,N,H,C,L,1,5 - 0.002 xPB_B,N,N,H,C,L,2,5
        - 0.002 xPB_B,N,N,H,C,L,3,5 - 0.002 xPB_B,N,N,H,C,L,4,5
        - 0.002 xGS_B,N,N,H,C,L,0,5 - 0.002 xGS_B,N,N,H,C,L,1,5
        - 0.002 xGS_B,N,N,H,C,L,2,5 - 0.002 xGS_B,N,N,H,C,L,3,5
        - 0.002 xGS_B,N,N,H,C,L,4,5 - 0.002 xEAe_B,N,N,H,C,L,5
        - 0.002 xEAr_B,N,N,H,C,5,1 - 0.002 xEAr_B,N,N,H,C,5,2
        - 0.002 xEAr_B,N,N,H,C,5,3 - 0.002 xEAr_B,N,N,H,C,5,4
        + fire_B,N,N,H,C,5,P + fire_B,N,N,H,C,5,D + fire_B,N,N,H,C,5,W
        + fire_B,N,N,H,C,5,C + fire_B,N,N,H,C,5,I + fire_B,N,N,H,C,5,A
        + fire_B,N,N,H,C,5,L + fire_B,N,N,H,C,5,N  = 0
 c1801: fire_B,N,N,H,I,1,P + fire_B,N,N,H,I,1,D + fire_B,N,N,H,I,1,W
        + fire_B,N,N,H,I,1,C + fire_B,N,N,H,I,1,I + fire_B,N,N,H,I,1,A
        + fire_B,N,N,H,I,1,L + fire_B,N,N,H,I,1,N  = 0
 c1802: - 0.002 xEAr_B,N,N,H,I,2,1 + fire_B,N,N,H,I,2,P + fire_B,N,N,H,I,2,D
        + fire_B,N,N,H,I,2,W + fire_B,N,N,H,I,2,C + fire_B,N,N,H,I,2,I
        + fire_B,N,N,H,I,2,A + fire_B,N,N,H,I,2,L + fire_B,N,N,H,I,2,N  = 0
 c1803: - 0.002 xEAr_B,N,N,H,I,3,1 - 0.002 xEAr_B,N,N,H,I,3,2
        + fire_B,N,N,H,I,3,P + fire_B,N,N,H,I,3,D + fire_B,N,N,H,I,3,W
        + fire_B,N,N,H,I,3,C + fire_B,N,N,H,I,3,I + fire_B,N,N,H,I,3,A
        + fire_B,N,N,H,I,3,L + fire_B,N,N,H,I,3,N  = 0
 c1804: - 0.002 xEAr_B,N,N,H,I,4,1 - 0.002 xEAr_B,N,N,H,I,4,2
        - 0.002 xEAr_B,N,N,H,I,4,3 + fire_B,N,N,H,I,4,P + fire_B,N,N,H,I,4,D
        + fire_B,N,N,H,I,4,W + fire_B,N,N,H,I,4,C + fire_B,N,N,H,I,4,I
        + fire_B,N,N,H,I,4,A + fire_B,N,N,H,I,4,L + fire_B,N,N,H,I,4,N  = 0
 c1805: - 0.002 xEAr_B,N,N,H,I,5,1 - 0.002 xEAr_B,N,N,H,I,5,2
        - 0.002 xEAr_B,N,N,H,I,5,3 - 0.002 xEAr_B,N,N,H,I,5,4
        + fire_B,N,N,H,I,5,P + fire_B,N,N,H,I,5,D + fire_B,N,N,H,I,5,W
        + fire_B,N,N,H,I,5,C + fire_B,N,N,H,I,5,I + fire_B,N,N,H,I,5,A
        + fire_B,N,N,H,I,5,L + fire_B,N,N,H,I,5,N  = 0
 c1806: fire_B,N,N,H,A,1,P + fire_B,N,N,H,A,1,D + fire_B,N,N,H,A,1,W
        + fire_B,N,N,H,A,1,C + fire_B,N,N,H,A,1,I + fire_B,N,N,H,A,1,A
        + fire_B,N,N,H,A,1,L + fire_B,N,N,H,A,1,N  = 0
 c1807: - 0.002 xEAr_B,N,N,H,A,2,1 + fire_B,N,N,H,A,2,P + fire_B,N,N,H,A,2,D
        + fire_B,N,N,H,A,2,W + fire_B,N,N,H,A,2,C + fire_B,N,N,H,A,2,I
        + fire_B,N,N,H,A,2,A + fire_B,N,N,H,A,2,L + fire_B,N,N,H,A,2,N  = 0
 c1808: - 0.002 xEAr_B,N,N,H,A,3,1 - 0.002 xEAr_B,N,N,H,A,3,2
        + fire_B,N,N,H,A,3,P + fire_B,N,N,H,A,3,D + fire_B,N,N,H,A,3,W
        + fire_B,N,N,H,A,3,C + fire_B,N,N,H,A,3,I + fire_B,N,N,H,A,3,A
        + fire_B,N,N,H,A,3,L + fire_B,N,N,H,A,3,N  = 0
 c1809: - 0.002 xEAr_B,N,N,H,A,4,1 - 0.002 xEAr_B,N,N,H,A,4,2
        - 0.002 xEAr_B,N,N,H,A,4,3 + fire_B,N,N,H,A,4,P + fire_B,N,N,H,A,4,D
        + fire_B,N,N,H,A,4,W + fire_B,N,N,H,A,4,C + fire_B,N,N,H,A,4,I
        + fire_B,N,N,H,A,4,A + fire_B,N,N,H,A,4,L + fire_B,N,N,H,A,4,N  = 0
 c1810: - 0.002 xEAr_B,N,N,H,A,5,1 - 0.002 xEAr_B,N,N,H,A,5,2
        - 0.002 xEAr_B,N,N,H,A,5,3 - 0.002 xEAr_B,N,N,H,A,5,4
        + fire_B,N,N,H,A,5,P + fire_B,N,N,H,A,5,D + fire_B,N,N,H,A,5,W
        + fire_B,N,N,H,A,5,C + fire_B,N,N,H,A,5,I + fire_B,N,N,H,A,5,A
        + fire_B,N,N,H,A,5,L + fire_B,N,N,H,A,5,N  = 0
 c1811: fire_B,N,N,H,L,1,P + fire_B,N,N,H,L,1,D + fire_B,N,N,H,L,1,W
        + fire_B,N,N,H,L,1,C + fire_B,N,N,H,L,1,I + fire_B,N,N,H,L,1,A
        + fire_B,N,N,H,L,1,L + fire_B,N,N,H,L,1,N  = 0
 c1812: - 0.002 xEAr_B,N,N,H,L,2,1 + fire_B,N,N,H,L,2,P + fire_B,N,N,H,L,2,D
        + fire_B,N,N,H,L,2,W + fire_B,N,N,H,L,2,C + fire_B,N,N,H,L,2,I
        + fire_B,N,N,H,L,2,A + fire_B,N,N,H,L,2,L + fire_B,N,N,H,L,2,N  = 0
 c1813: - 0.002 xEAr_B,N,N,H,L,3,1 - 0.002 xEAr_B,N,N,H,L,3,2
        + fire_B,N,N,H,L,3,P + fire_B,N,N,H,L,3,D + fire_B,N,N,H,L,3,W
        + fire_B,N,N,H,L,3,C + fire_B,N,N,H,L,3,I + fire_B,N,N,H,L,3,A
        + fire_B,N,N,H,L,3,L + fire_B,N,N,H,L,3,N  = 0
 c1814: - 0.002 xEAr_B,N,N,H,L,4,1 - 0.002 xEAr_B,N,N,H,L,4,2
        - 0.002 xEAr_B,N,N,H,L,4,3 + fire_B,N,N,H,L,4,P + fire_B,N,N,H,L,4,D
        + fire_B,N,N,H,L,4,W + fire_B,N,N,H,L,4,C + fire_B,N,N,H,L,4,I
        + fire_B,N,N,H,L,4,A + fire_B,N,N,H,L,4,L + fire_B,N,N,H,L,4,N  = 0
 c1815: - 0.002 xEAr_B,N,N,H,L,5,1 - 0.002 xEAr_B,N,N,H,L,5,2
        - 0.002 xEAr_B,N,N,H,L,5,3 - 0.002 xEAr_B,N,N,H,L,5,4
        + fire_B,N,N,H,L,5,P + fire_B,N,N,H,L,5,D + fire_B,N,N,H,L,5,W
        + fire_B,N,N,H,L,5,C + fire_B,N,N,H,L,5,I + fire_B,N,N,H,L,5,A
        + fire_B,N,N,H,L,5,L + fire_B,N,N,H,L,5,N  = 0
 c1816: fire_B,N,N,H,N,1,P + fire_B,N,N,H,N,1,D + fire_B,N,N,H,N,1,W
        + fire_B,N,N,H,N,1,C + fire_B,N,N,H,N,1,I + fire_B,N,N,H,N,1,A
        + fire_B,N,N,H,N,1,L + fire_B,N,N,H,N,1,N  = 0
 c1817: fire_B,N,N,H,N,2,P + fire_B,N,N,H,N,2,D + fire_B,N,N,H,N,2,W
        + fire_B,N,N,H,N,2,C + fire_B,N,N,H,N,2,I + fire_B,N,N,H,N,2,A
        + fire_B,N,N,H,N,2,L + fire_B,N,N,H,N,2,N  = 0
 c1818: fire_B,N,N,H,N,3,P + fire_B,N,N,H,N,3,D + fire_B,N,N,H,N,3,W
        + fire_B,N,N,H,N,3,C + fire_B,N,N,H,N,3,I + fire_B,N,N,H,N,3,A
        + fire_B,N,N,H,N,3,L + fire_B,N,N,H,N,3,N  = 0
 c1819: fire_B,N,N,H,N,4,P + fire_B,N,N,H,N,4,D + fire_B,N,N,H,N,4,W
        + fire_B,N,N,H,N,4,C + fire_B,N,N,H,N,4,I + fire_B,N,N,H,N,4,A
        + fire_B,N,N,H,N,4,L + fire_B,N,N,H,N,4,N  = 0
 c1820: fire_B,N,N,H,N,5,P + fire_B,N,N,H,N,5,D + fire_B,N,N,H,N,5,W
        + fire_B,N,N,H,N,5,C + fire_B,N,N,H,N,5,I + fire_B,N,N,H,N,5,A
        + fire_B,N,N,H,N,5,L + fire_B,N,N,H,N,5,N  = 0
 c1821: - 0.002 xNG_B,N,N,C,P,P,1 - 0.002 xNG_B,N,N,C,P,M,1
        - 0.002 xNG_B,N,N,C,P,L,1 - 0.002 xPB_B,N,N,C,P,P,0,1
        - 0.002 xPB_B,N,N,C,P,P,1,1 - 0.002 xPB_B,N,N,C,P,P,2,1
        - 0.002 xPB_B,N,N,C,P,P,3,1 - 0.002 xPB_B,N,N,C,P,P,4,1
        - 0.002 xPB_B,N,N,C,P,M,0,1 - 0.002 xPB_B,N,N,C,P,M,1,1
        - 0.002 xPB_B,N,N,C,P,M,2,1 - 0.002 xPB_B,N,N,C,P,M,3,1
        - 0.002 xPB_B,N,N,C,P,M,4,1 - 0.002 xPB_B,N,N,C,P,L,0,1
        - 0.002 xPB_B,N,N,C,P,L,1,1 - 0.002 xPB_B,N,N,C,P,L,2,1
        - 0.002 xPB_B,N,N,C,P,L,3,1 - 0.002 xPB_B,N,N,C,P,L,4,1
        - 0.002 xGS_B,N,N,C,P,P,0,1 - 0.002 xGS_B,N,N,C,P,P,1,1
        - 0.002 xGS_B,N,N,C,P,P,2,1 - 0.002 xGS_B,N,N,C,P,P,3,1
        - 0.002 xGS_B,N,N,C,P,P,4,1 - 0.002 xGS_B,N,N,C,P,M,0,1
        - 0.002 xGS_B,N,N,C,P,M,1,1 - 0.002 xGS_B,N,N,C,P,M,2,1
        - 0.002 xGS_B,N,N,C,P,M,3,1 - 0.002 xGS_B,N,N,C,P,M,4,1
        - 0.002 xGS_B,N,N,C,P,L,0,1 - 0.002 xGS_B,N,N,C,P,L,1,1
        - 0.002 xGS_B,N,N,C,P,L,2,1 - 0.002 xGS_B,N,N,C,P,L,3,1
        - 0.002 xGS_B,N,N,C,P,L,4,1 - 0.002 xEAe_B,N,N,C,P,P,1
        - 0.002 xEAe_B,N,N,C,P,M,1 - 0.002 xEAe_B,N,N,C,P,L,1
        + fire_B,N,N,C,P,1,P + fire_B,N,N,C,P,1,D + fire_B,N,N,C,P,1,W
        + fire_B,N,N,C,P,1,C + fire_B,N,N,C,P,1,I + fire_B,N,N,C,P,1,A
        + fire_B,N,N,C,P,1,L + fire_B,N,N,C,P,1,N  = 0
 c1822: - 0.002 xNG_B,N,N,C,P,P,2 - 0.002 xNG_B,N,N,C,P,M,2
        - 0.002 xNG_B,N,N,C,P,L,2 - 0.002 xPB_B,N,N,C,P,P,0,2
        - 0.002 xPB_B,N,N,C,P,P,1,2 - 0.002 xPB_B,N,N,C,P,P,2,2
        - 0.002 xPB_B,N,N,C,P,P,3,2 - 0.002 xPB_B,N,N,C,P,P,4,2
        - 0.002 xPB_B,N,N,C,P,M,0,2 - 0.002 xPB_B,N,N,C,P,M,1,2
        - 0.002 xPB_B,N,N,C,P,M,2,2 - 0.002 xPB_B,N,N,C,P,M,3,2
        - 0.002 xPB_B,N,N,C,P,M,4,2 - 0.002 xPB_B,N,N,C,P,L,0,2
        - 0.002 xPB_B,N,N,C,P,L,1,2 - 0.002 xPB_B,N,N,C,P,L,2,2
        - 0.002 xPB_B,N,N,C,P,L,3,2 - 0.002 xPB_B,N,N,C,P,L,4,2
        - 0.002 xGS_B,N,N,C,P,P,0,2 - 0.002 xGS_B,N,N,C,P,P,1,2
        - 0.002 xGS_B,N,N,C,P,P,2,2 - 0.002 xGS_B,N,N,C,P,P,3,2
        - 0.002 xGS_B,N,N,C,P,P,4,2 - 0.002 xGS_B,N,N,C,P,M,0,2
        - 0.002 xGS_B,N,N,C,P,M,1,2 - 0.002 xGS_B,N,N,C,P,M,2,2
        - 0.002 xGS_B,N,N,C,P,M,3,2 - 0.002 xGS_B,N,N,C,P,M,4,2
        - 0.002 xGS_B,N,N,C,P,L,0,2 - 0.002 xGS_B,N,N,C,P,L,1,2
        - 0.002 xGS_B,N,N,C,P,L,2,2 - 0.002 xGS_B,N,N,C,P,L,3,2
        - 0.002 xGS_B,N,N,C,P,L,4,2 - 0.002 xEAe_B,N,N,C,P,P,2
        - 0.002 xEAe_B,N,N,C,P,M,2 - 0.002 xEAe_B,N,N,C,P,L,2
        - 0.002 xEAr_B,N,N,C,P,2,1 + fire_B,N,N,C,P,2,P + fire_B,N,N,C,P,2,D
        + fire_B,N,N,C,P,2,W + fire_B,N,N,C,P,2,C + fire_B,N,N,C,P,2,I
        + fire_B,N,N,C,P,2,A + fire_B,N,N,C,P,2,L + fire_B,N,N,C,P,2,N  = 0
 c1823: - 0.002 xNG_B,N,N,C,P,P,3 - 0.002 xNG_B,N,N,C,P,M,3
        - 0.002 xNG_B,N,N,C,P,L,3 - 0.002 xPB_B,N,N,C,P,P,0,3
        - 0.002 xPB_B,N,N,C,P,P,1,3 - 0.002 xPB_B,N,N,C,P,P,2,3
        - 0.002 xPB_B,N,N,C,P,P,3,3 - 0.002 xPB_B,N,N,C,P,P,4,3
        - 0.002 xPB_B,N,N,C,P,M,0,3 - 0.002 xPB_B,N,N,C,P,M,1,3
        - 0.002 xPB_B,N,N,C,P,M,2,3 - 0.002 xPB_B,N,N,C,P,M,3,3
        - 0.002 xPB_B,N,N,C,P,M,4,3 - 0.002 xPB_B,N,N,C,P,L,0,3
        - 0.002 xPB_B,N,N,C,P,L,1,3 - 0.002 xPB_B,N,N,C,P,L,2,3
        - 0.002 xPB_B,N,N,C,P,L,3,3 - 0.002 xPB_B,N,N,C,P,L,4,3
        - 0.002 xGS_B,N,N,C,P,P,0,3 - 0.002 xGS_B,N,N,C,P,P,1,3
        - 0.002 xGS_B,N,N,C,P,P,2,3 - 0.002 xGS_B,N,N,C,P,P,3,3
        - 0.002 xGS_B,N,N,C,P,P,4,3 - 0.002 xGS_B,N,N,C,P,M,0,3
        - 0.002 xGS_B,N,N,C,P,M,1,3 - 0.002 xGS_B,N,N,C,P,M,2,3
        - 0.002 xGS_B,N,N,C,P,M,3,3 - 0.002 xGS_B,N,N,C,P,M,4,3
        - 0.002 xGS_B,N,N,C,P,L,0,3 - 0.002 xGS_B,N,N,C,P,L,1,3
        - 0.002 xGS_B,N,N,C,P,L,2,3 - 0.002 xGS_B,N,N,C,P,L,3,3
        - 0.002 xGS_B,N,N,C,P,L,4,3 - 0.002 xEAe_B,N,N,C,P,P,3
        - 0.002 xEAe_B,N,N,C,P,M,3 - 0.002 xEAe_B,N,N,C,P,L,3
        - 0.002 xEAr_B,N,N,C,P,3,1 - 0.002 xEAr_B,N,N,C,P,3,2
        + fire_B,N,N,C,P,3,P + fire_B,N,N,C,P,3,D + fire_B,N,N,C,P,3,W
        + fire_B,N,N,C,P,3,C + fire_B,N,N,C,P,3,I + fire_B,N,N,C,P,3,A
        + fire_B,N,N,C,P,3,L + fire_B,N,N,C,P,3,N  = 0
 c1824: - 0.002 xNG_B,N,N,C,P,P,4 - 0.002 xNG_B,N,N,C,P,M,4
        - 0.002 xNG_B,N,N,C,P,L,4 - 0.002 xPB_B,N,N,C,P,P,0,4
        - 0.002 xPB_B,N,N,C,P,P,1,4 - 0.002 xPB_B,N,N,C,P,P,2,4
        - 0.002 xPB_B,N,N,C,P,P,3,4 - 0.002 xPB_B,N,N,C,P,P,4,4
        - 0.002 xPB_B,N,N,C,P,M,0,4 - 0.002 xPB_B,N,N,C,P,M,1,4
        - 0.002 xPB_B,N,N,C,P,M,2,4 - 0.002 xPB_B,N,N,C,P,M,3,4
        - 0.002 xPB_B,N,N,C,P,M,4,4 - 0.002 xPB_B,N,N,C,P,L,0,4
        - 0.002 xPB_B,N,N,C,P,L,1,4 - 0.002 xPB_B,N,N,C,P,L,2,4
        - 0.002 xPB_B,N,N,C,P,L,3,4 - 0.002 xPB_B,N,N,C,P,L,4,4
        - 0.002 xGS_B,N,N,C,P,P,0,4 - 0.002 xGS_B,N,N,C,P,P,1,4
        - 0.002 xGS_B,N,N,C,P,P,2,4 - 0.002 xGS_B,N,N,C,P,P,3,4
        - 0.002 xGS_B,N,N,C,P,P,4,4 - 0.002 xGS_B,N,N,C,P,M,0,4
        - 0.002 xGS_B,N,N,C,P,M,1,4 - 0.002 xGS_B,N,N,C,P,M,2,4
        - 0.002 xGS_B,N,N,C,P,M,3,4 - 0.002 xGS_B,N,N,C,P,M,4,4
        - 0.002 xGS_B,N,N,C,P,L,0,4 - 0.002 xGS_B,N,N,C,P,L,1,4
        - 0.002 xGS_B,N,N,C,P,L,2,4 - 0.002 xGS_B,N,N,C,P,L,3,4
        - 0.002 xGS_B,N,N,C,P,L,4,4 - 0.002 xEAe_B,N,N,C,P,P,4
        - 0.002 xEAe_B,N,N,C,P,M,4 - 0.002 xEAe_B,N,N,C,P,L,4
        - 0.002 xEAr_B,N,N,C,P,4,1 - 0.002 xEAr_B,N,N,C,P,4,2
        - 0.002 xEAr_B,N,N,C,P,4,3 + fire_B,N,N,C,P,4,P + fire_B,N,N,C,P,4,D
        + fire_B,N,N,C,P,4,W + fire_B,N,N,C,P,4,C + fire_B,N,N,C,P,4,I
        + fire_B,N,N,C,P,4,A + fire_B,N,N,C,P,4,L + fire_B,N,N,C,P,4,N  = 0
 c1825: - 0.002 xNG_B,N,N,C,P,P,5 - 0.002 xNG_B,N,N,C,P,M,5
        - 0.002 xNG_B,N,N,C,P,L,5 - 0.002 xPB_B,N,N,C,P,P,0,5
        - 0.002 xPB_B,N,N,C,P,P,1,5 - 0.002 xPB_B,N,N,C,P,P,2,5
        - 0.002 xPB_B,N,N,C,P,P,3,5 - 0.002 xPB_B,N,N,C,P,P,4,5
        - 0.002 xPB_B,N,N,C,P,M,0,5 - 0.002 xPB_B,N,N,C,P,M,1,5
        - 0.002 xPB_B,N,N,C,P,M,2,5 - 0.002 xPB_B,N,N,C,P,M,3,5
        - 0.002 xPB_B,N,N,C,P,M,4,5 - 0.002 xPB_B,N,N,C,P,L,0,5
        - 0.002 xPB_B,N,N,C,P,L,1,5 - 0.002 xPB_B,N,N,C,P,L,2,5
        - 0.002 xPB_B,N,N,C,P,L,3,5 - 0.002 xPB_B,N,N,C,P,L,4,5
        - 0.002 xGS_B,N,N,C,P,P,0,5 - 0.002 xGS_B,N,N,C,P,P,1,5
        - 0.002 xGS_B,N,N,C,P,P,2,5 - 0.002 xGS_B,N,N,C,P,P,3,5
        - 0.002 xGS_B,N,N,C,P,P,4,5 - 0.002 xGS_B,N,N,C,P,M,0,5
        - 0.002 xGS_B,N,N,C,P,M,1,5 - 0.002 xGS_B,N,N,C,P,M,2,5
        - 0.002 xGS_B,N,N,C,P,M,3,5 - 0.002 xGS_B,N,N,C,P,M,4,5
        - 0.002 xGS_B,N,N,C,P,L,0,5 - 0.002 xGS_B,N,N,C,P,L,1,5
        - 0.002 xGS_B,N,N,C,P,L,2,5 - 0.002 xGS_B,N,N,C,P,L,3,5
        - 0.002 xGS_B,N,N,C,P,L,4,5 - 0.002 xEAe_B,N,N,C,P,P,5
        - 0.002 xEAe_B,N,N,C,P,M,5 - 0.002 xEAe_B,N,N,C,P,L,5
        - 0.002 xEAr_B,N,N,C,P,5,1 - 0.002 xEAr_B,N,N,C,P,5,2
        - 0.002 xEAr_B,N,N,C,P,5,3 - 0.002 xEAr_B,N,N,C,P,5,4
        + fire_B,N,N,C,P,5,P + fire_B,N,N,C,P,5,D + fire_B,N,N,C,P,5,W
        + fire_B,N,N,C,P,5,C + fire_B,N,N,C,P,5,I + fire_B,N,N,C,P,5,A
        + fire_B,N,N,C,P,5,L + fire_B,N,N,C,P,5,N  = 0
 c1826: - 0.002 xNG_B,N,N,C,D,S,1 - 0.002 xNG_B,N,N,C,D,P,1
        - 0.002 xNG_B,N,N,C,D,M,1 - 0.002 xNG_B,N,N,C,D,L,1
        - 0.002 xPB_B,N,N,C,D,S,0,1 - 0.002 xPB_B,N,N,C,D,S,1,1
        - 0.002 xPB_B,N,N,C,D,S,2,1 - 0.002 xPB_B,N,N,C,D,S,3,1
        - 0.002 xPB_B,N,N,C,D,S,4,1 - 0.002 xPB_B,N,N,C,D,P,0,1
        - 0.002 xPB_B,N,N,C,D,P,1,1 - 0.002 xPB_B,N,N,C,D,P,2,1
        - 0.002 xPB_B,N,N,C,D,P,3,1 - 0.002 xPB_B,N,N,C,D,P,4,1
        - 0.002 xPB_B,N,N,C,D,M,0,1 - 0.002 xPB_B,N,N,C,D,M,1,1
        - 0.002 xPB_B,N,N,C,D,M,2,1 - 0.002 xPB_B,N,N,C,D,M,3,1
        - 0.002 xPB_B,N,N,C,D,M,4,1 - 0.002 xPB_B,N,N,C,D,L,0,1
        - 0.002 xPB_B,N,N,C,D,L,1,1 - 0.002 xPB_B,N,N,C,D,L,2,1
        - 0.002 xPB_B,N,N,C,D,L,3,1 - 0.002 xPB_B,N,N,C,D,L,4,1
        - 0.002 xGS_B,N,N,C,D,S,0,1 - 0.002 xGS_B,N,N,C,D,S,1,1
        - 0.002 xGS_B,N,N,C,D,S,2,1 - 0.002 xGS_B,N,N,C,D,S,3,1
        - 0.002 xGS_B,N,N,C,D,S,4,1 - 0.002 xGS_B,N,N,C,D,P,0,1
        - 0.002 xGS_B,N,N,C,D,P,1,1 - 0.002 xGS_B,N,N,C,D,P,2,1
        - 0.002 xGS_B,N,N,C,D,P,3,1 - 0.002 xGS_B,N,N,C,D,P,4,1
        - 0.002 xGS_B,N,N,C,D,M,0,1 - 0.002 xGS_B,N,N,C,D,M,1,1
        - 0.002 xGS_B,N,N,C,D,M,2,1 - 0.002 xGS_B,N,N,C,D,M,3,1
        - 0.002 xGS_B,N,N,C,D,M,4,1 - 0.002 xGS_B,N,N,C,D,L,0,1
        - 0.002 xGS_B,N,N,C,D,L,1,1 - 0.002 xGS_B,N,N,C,D,L,2,1
        - 0.002 xGS_B,N,N,C,D,L,3,1 - 0.002 xGS_B,N,N,C,D,L,4,1
        - 0.002 xEAe_B,N,N,C,D,S,1 - 0.002 xEAe_B,N,N,C,D,P,1
        - 0.002 xEAe_B,N,N,C,D,M,1 - 0.002 xEAe_B,N,N,C,D,L,1
        + fire_B,N,N,C,D,1,P + fire_B,N,N,C,D,1,D + fire_B,N,N,C,D,1,W
        + fire_B,N,N,C,D,1,C + fire_B,N,N,C,D,1,I + fire_B,N,N,C,D,1,A
        + fire_B,N,N,C,D,1,L + fire_B,N,N,C,D,1,N  = 0
 c1827: - 0.002 xNG_B,N,N,C,D,S,2 - 0.002 xNG_B,N,N,C,D,P,2
        - 0.002 xNG_B,N,N,C,D,M,2 - 0.002 xNG_B,N,N,C,D,L,2
        - 0.002 xPB_B,N,N,C,D,S,0,2 - 0.002 xPB_B,N,N,C,D,S,1,2
        - 0.002 xPB_B,N,N,C,D,S,2,2 - 0.002 xPB_B,N,N,C,D,S,3,2
        - 0.002 xPB_B,N,N,C,D,S,4,2 - 0.002 xPB_B,N,N,C,D,P,0,2
        - 0.002 xPB_B,N,N,C,D,P,1,2 - 0.002 xPB_B,N,N,C,D,P,2,2
        - 0.002 xPB_B,N,N,C,D,P,3,2 - 0.002 xPB_B,N,N,C,D,P,4,2
        - 0.002 xPB_B,N,N,C,D,M,0,2 - 0.002 xPB_B,N,N,C,D,M,1,2
        - 0.002 xPB_B,N,N,C,D,M,2,2 - 0.002 xPB_B,N,N,C,D,M,3,2
        - 0.002 xPB_B,N,N,C,D,M,4,2 - 0.002 xPB_B,N,N,C,D,L,0,2
        - 0.002 xPB_B,N,N,C,D,L,1,2 - 0.002 xPB_B,N,N,C,D,L,2,2
        - 0.002 xPB_B,N,N,C,D,L,3,2 - 0.002 xPB_B,N,N,C,D,L,4,2
        - 0.002 xGS_B,N,N,C,D,S,0,2 - 0.002 xGS_B,N,N,C,D,S,1,2
        - 0.002 xGS_B,N,N,C,D,S,2,2 - 0.002 xGS_B,N,N,C,D,S,3,2
        - 0.002 xGS_B,N,N,C,D,S,4,2 - 0.002 xGS_B,N,N,C,D,P,0,2
        - 0.002 xGS_B,N,N,C,D,P,1,2 - 0.002 xGS_B,N,N,C,D,P,2,2
        - 0.002 xGS_B,N,N,C,D,P,3,2 - 0.002 xGS_B,N,N,C,D,P,4,2
        - 0.002 xGS_B,N,N,C,D,M,0,2 - 0.002 xGS_B,N,N,C,D,M,1,2
        - 0.002 xGS_B,N,N,C,D,M,2,2 - 0.002 xGS_B,N,N,C,D,M,3,2
        - 0.002 xGS_B,N,N,C,D,M,4,2 - 0.002 xGS_B,N,N,C,D,L,0,2
        - 0.002 xGS_B,N,N,C,D,L,1,2 - 0.002 xGS_B,N,N,C,D,L,2,2
        - 0.002 xGS_B,N,N,C,D,L,3,2 - 0.002 xGS_B,N,N,C,D,L,4,2
        - 0.002 xEAe_B,N,N,C,D,S,2 - 0.002 xEAe_B,N,N,C,D,P,2
        - 0.002 xEAe_B,N,N,C,D,M,2 - 0.002 xEAe_B,N,N,C,D,L,2
        - 0.002 xEAr_B,N,N,C,D,2,1 + fire_B,N,N,C,D,2,P + fire_B,N,N,C,D,2,D
        + fire_B,N,N,C,D,2,W + fire_B,N,N,C,D,2,C + fire_B,N,N,C,D,2,I
        + fire_B,N,N,C,D,2,A + fire_B,N,N,C,D,2,L + fire_B,N,N,C,D,2,N  = 0
 c1828: - 0.002 xNG_B,N,N,C,D,S,3 - 0.002 xNG_B,N,N,C,D,P,3
        - 0.002 xNG_B,N,N,C,D,M,3 - 0.002 xNG_B,N,N,C,D,L,3
        - 0.002 xPB_B,N,N,C,D,S,0,3 - 0.002 xPB_B,N,N,C,D,S,1,3
        - 0.002 xPB_B,N,N,C,D,S,2,3 - 0.002 xPB_B,N,N,C,D,S,3,3
        - 0.002 xPB_B,N,N,C,D,S,4,3 - 0.002 xPB_B,N,N,C,D,P,0,3
        - 0.002 xPB_B,N,N,C,D,P,1,3 - 0.002 xPB_B,N,N,C,D,P,2,3
        - 0.002 xPB_B,N,N,C,D,P,3,3 - 0.002 xPB_B,N,N,C,D,P,4,3
        - 0.002 xPB_B,N,N,C,D,M,0,3 - 0.002 xPB_B,N,N,C,D,M,1,3
        - 0.002 xPB_B,N,N,C,D,M,2,3 - 0.002 xPB_B,N,N,C,D,M,3,3
        - 0.002 xPB_B,N,N,C,D,M,4,3 - 0.002 xPB_B,N,N,C,D,L,0,3
        - 0.002 xPB_B,N,N,C,D,L,1,3 - 0.002 xPB_B,N,N,C,D,L,2,3
        - 0.002 xPB_B,N,N,C,D,L,3,3 - 0.002 xPB_B,N,N,C,D,L,4,3
        - 0.002 xGS_B,N,N,C,D,S,0,3 - 0.002 xGS_B,N,N,C,D,S,1,3
        - 0.002 xGS_B,N,N,C,D,S,2,3 - 0.002 xGS_B,N,N,C,D,S,3,3
        - 0.002 xGS_B,N,N,C,D,S,4,3 - 0.002 xGS_B,N,N,C,D,P,0,3
        - 0.002 xGS_B,N,N,C,D,P,1,3 - 0.002 xGS_B,N,N,C,D,P,2,3
        - 0.002 xGS_B,N,N,C,D,P,3,3 - 0.002 xGS_B,N,N,C,D,P,4,3
        - 0.002 xGS_B,N,N,C,D,M,0,3 - 0.002 xGS_B,N,N,C,D,M,1,3
        - 0.002 xGS_B,N,N,C,D,M,2,3 - 0.002 xGS_B,N,N,C,D,M,3,3
        - 0.002 xGS_B,N,N,C,D,M,4,3 - 0.002 xGS_B,N,N,C,D,L,0,3
        - 0.002 xGS_B,N,N,C,D,L,1,3 - 0.002 xGS_B,N,N,C,D,L,2,3
        - 0.002 xGS_B,N,N,C,D,L,3,3 - 0.002 xGS_B,N,N,C,D,L,4,3
        - 0.002 xEAe_B,N,N,C,D,S,3 - 0.002 xEAe_B,N,N,C,D,P,3
        - 0.002 xEAe_B,N,N,C,D,M,3 - 0.002 xEAe_B,N,N,C,D,L,3
        - 0.002 xEAr_B,N,N,C,D,3,1 - 0.002 xEAr_B,N,N,C,D,3,2
        + fire_B,N,N,C,D,3,P + fire_B,N,N,C,D,3,D + fire_B,N,N,C,D,3,W
        + fire_B,N,N,C,D,3,C + fire_B,N,N,C,D,3,I + fire_B,N,N,C,D,3,A
        + fire_B,N,N,C,D,3,L + fire_B,N,N,C,D,3,N  = 0
 c1829: - 0.002 xNG_B,N,N,C,D,S,4 - 0.002 xNG_B,N,N,C,D,P,4
        - 0.002 xNG_B,N,N,C,D,M,4 - 0.002 xNG_B,N,N,C,D,L,4
        - 0.002 xPB_B,N,N,C,D,S,0,4 - 0.002 xPB_B,N,N,C,D,S,1,4
        - 0.002 xPB_B,N,N,C,D,S,2,4 - 0.002 xPB_B,N,N,C,D,S,3,4
        - 0.002 xPB_B,N,N,C,D,S,4,4 - 0.002 xPB_B,N,N,C,D,P,0,4
        - 0.002 xPB_B,N,N,C,D,P,1,4 - 0.002 xPB_B,N,N,C,D,P,2,4
        - 0.002 xPB_B,N,N,C,D,P,3,4 - 0.002 xPB_B,N,N,C,D,P,4,4
        - 0.002 xPB_B,N,N,C,D,M,0,4 - 0.002 xPB_B,N,N,C,D,M,1,4
        - 0.002 xPB_B,N,N,C,D,M,2,4 - 0.002 xPB_B,N,N,C,D,M,3,4
        - 0.002 xPB_B,N,N,C,D,M,4,4 - 0.002 xPB_B,N,N,C,D,L,0,4
        - 0.002 xPB_B,N,N,C,D,L,1,4 - 0.002 xPB_B,N,N,C,D,L,2,4
        - 0.002 xPB_B,N,N,C,D,L,3,4 - 0.002 xPB_B,N,N,C,D,L,4,4
        - 0.002 xGS_B,N,N,C,D,S,0,4 - 0.002 xGS_B,N,N,C,D,S,1,4
        - 0.002 xGS_B,N,N,C,D,S,2,4 - 0.002 xGS_B,N,N,C,D,S,3,4
        - 0.002 xGS_B,N,N,C,D,S,4,4 - 0.002 xGS_B,N,N,C,D,P,0,4
        - 0.002 xGS_B,N,N,C,D,P,1,4 - 0.002 xGS_B,N,N,C,D,P,2,4
        - 0.002 xGS_B,N,N,C,D,P,3,4 - 0.002 xGS_B,N,N,C,D,P,4,4
        - 0.002 xGS_B,N,N,C,D,M,0,4 - 0.002 xGS_B,N,N,C,D,M,1,4
        - 0.002 xGS_B,N,N,C,D,M,2,4 - 0.002 xGS_B,N,N,C,D,M,3,4
        - 0.002 xGS_B,N,N,C,D,M,4,4 - 0.002 xGS_B,N,N,C,D,L,0,4
        - 0.002 xGS_B,N,N,C,D,L,1,4 - 0.002 xGS_B,N,N,C,D,L,2,4
        - 0.002 xGS_B,N,N,C,D,L,3,4 - 0.002 xGS_B,N,N,C,D,L,4,4
        - 0.002 xEAe_B,N,N,C,D,S,4 - 0.002 xEAe_B,N,N,C,D,P,4
        - 0.002 xEAe_B,N,N,C,D,M,4 - 0.002 xEAe_B,N,N,C,D,L,4
        - 0.002 xEAr_B,N,N,C,D,4,1 - 0.002 xEAr_B,N,N,C,D,4,2
        - 0.002 xEAr_B,N,N,C,D,4,3 + fire_B,N,N,C,D,4,P + fire_B,N,N,C,D,4,D
        + fire_B,N,N,C,D,4,W + fire_B,N,N,C,D,4,C + fire_B,N,N,C,D,4,I
        + fire_B,N,N,C,D,4,A + fire_B,N,N,C,D,4,L + fire_B,N,N,C,D,4,N  = 0
 c1830: - 0.002 xNG_B,N,N,C,D,S,5 - 0.002 xNG_B,N,N,C,D,P,5
        - 0.002 xNG_B,N,N,C,D,M,5 - 0.002 xNG_B,N,N,C,D,L,5
        - 0.002 xPB_B,N,N,C,D,S,0,5 - 0.002 xPB_B,N,N,C,D,S,1,5
        - 0.002 xPB_B,N,N,C,D,S,2,5 - 0.002 xPB_B,N,N,C,D,S,3,5
        - 0.002 xPB_B,N,N,C,D,S,4,5 - 0.002 xPB_B,N,N,C,D,P,0,5
        - 0.002 xPB_B,N,N,C,D,P,1,5 - 0.002 xPB_B,N,N,C,D,P,2,5
        - 0.002 xPB_B,N,N,C,D,P,3,5 - 0.002 xPB_B,N,N,C,D,P,4,5
        - 0.002 xPB_B,N,N,C,D,M,0,5 - 0.002 xPB_B,N,N,C,D,M,1,5
        - 0.002 xPB_B,N,N,C,D,M,2,5 - 0.002 xPB_B,N,N,C,D,M,3,5
        - 0.002 xPB_B,N,N,C,D,M,4,5 - 0.002 xPB_B,N,N,C,D,L,0,5
        - 0.002 xPB_B,N,N,C,D,L,1,5 - 0.002 xPB_B,N,N,C,D,L,2,5
        - 0.002 xPB_B,N,N,C,D,L,3,5 - 0.002 xPB_B,N,N,C,D,L,4,5
        - 0.002 xGS_B,N,N,C,D,S,0,5 - 0.002 xGS_B,N,N,C,D,S,1,5
        - 0.002 xGS_B,N,N,C,D,S,2,5 - 0.002 xGS_B,N,N,C,D,S,3,5
        - 0.002 xGS_B,N,N,C,D,S,4,5 - 0.002 xGS_B,N,N,C,D,P,0,5
        - 0.002 xGS_B,N,N,C,D,P,1,5 - 0.002 xGS_B,N,N,C,D,P,2,5
        - 0.002 xGS_B,N,N,C,D,P,3,5 - 0.002 xGS_B,N,N,C,D,P,4,5
        - 0.002 xGS_B,N,N,C,D,M,0,5 - 0.002 xGS_B,N,N,C,D,M,1,5
        - 0.002 xGS_B,N,N,C,D,M,2,5 - 0.002 xGS_B,N,N,C,D,M,3,5
        - 0.002 xGS_B,N,N,C,D,M,4,5 - 0.002 xGS_B,N,N,C,D,L,0,5
        - 0.002 xGS_B,N,N,C,D,L,1,5 - 0.002 xGS_B,N,N,C,D,L,2,5
        - 0.002 xGS_B,N,N,C,D,L,3,5 - 0.002 xGS_B,N,N,C,D,L,4,5
        - 0.002 xEAe_B,N,N,C,D,S,5 - 0.002 xEAe_B,N,N,C,D,P,5
        - 0.002 xEAe_B,N,N,C,D,M,5 - 0.002 xEAe_B,N,N,C,D,L,5
        - 0.002 xEAr_B,N,N,C,D,5,1 - 0.002 xEAr_B,N,N,C,D,5,2
        - 0.002 xEAr_B,N,N,C,D,5,3 - 0.002 xEAr_B,N,N,C,D,5,4
        + fire_B,N,N,C,D,5,P + fire_B,N,N,C,D,5,D + fire_B,N,N,C,D,5,W
        + fire_B,N,N,C,D,5,C + fire_B,N,N,C,D,5,I + fire_B,N,N,C,D,5,A
        + fire_B,N,N,C,D,5,L + fire_B,N,N,C,D,5,N  = 0
 c1831: - 0.002 xNG_B,N,N,C,W,S,1 - 0.002 xNG_B,N,N,C,W,P,1
        - 0.002 xNG_B,N,N,C,W,M,1 - 0.002 xNG_B,N,N,C,W,L,1
        - 0.002 xPB_B,N,N,C,W,S,0,1 - 0.002 xPB_B,N,N,C,W,S,1,1
        - 0.002 xPB_B,N,N,C,W,S,2,1 - 0.002 xPB_B,N,N,C,W,S,3,1
        - 0.002 xPB_B,N,N,C,W,S,4,1 - 0.002 xPB_B,N,N,C,W,P,0,1
        - 0.002 xPB_B,N,N,C,W,P,1,1 - 0.002 xPB_B,N,N,C,W,P,2,1
        - 0.002 xPB_B,N,N,C,W,P,3,1 - 0.002 xPB_B,N,N,C,W,P,4,1
        - 0.002 xPB_B,N,N,C,W,M,0,1 - 0.002 xPB_B,N,N,C,W,M,1,1
        - 0.002 xPB_B,N,N,C,W,M,2,1 - 0.002 xPB_B,N,N,C,W,M,3,1
        - 0.002 xPB_B,N,N,C,W,M,4,1 - 0.002 xPB_B,N,N,C,W,L,0,1
        - 0.002 xPB_B,N,N,C,W,L,1,1 - 0.002 xPB_B,N,N,C,W,L,2,1
        - 0.002 xPB_B,N,N,C,W,L,3,1 - 0.002 xPB_B,N,N,C,W,L,4,1
        - 0.002 xGS_B,N,N,C,W,S,0,1 - 0.002 xGS_B,N,N,C,W,S,1,1
        - 0.002 xGS_B,N,N,C,W,S,2,1 - 0.002 xGS_B,N,N,C,W,S,3,1
        - 0.002 xGS_B,N,N,C,W,S,4,1 - 0.002 xGS_B,N,N,C,W,P,0,1
        - 0.002 xGS_B,N,N,C,W,P,1,1 - 0.002 xGS_B,N,N,C,W,P,2,1
        - 0.002 xGS_B,N,N,C,W,P,3,1 - 0.002 xGS_B,N,N,C,W,P,4,1
        - 0.002 xGS_B,N,N,C,W,M,0,1 - 0.002 xGS_B,N,N,C,W,M,1,1
        - 0.002 xGS_B,N,N,C,W,M,2,1 - 0.002 xGS_B,N,N,C,W,M,3,1
        - 0.002 xGS_B,N,N,C,W,M,4,1 - 0.002 xGS_B,N,N,C,W,L,0,1
        - 0.002 xGS_B,N,N,C,W,L,1,1 - 0.002 xGS_B,N,N,C,W,L,2,1
        - 0.002 xGS_B,N,N,C,W,L,3,1 - 0.002 xGS_B,N,N,C,W,L,4,1
        - 0.002 xEAe_B,N,N,C,W,S,1 - 0.002 xEAe_B,N,N,C,W,P,1
        - 0.002 xEAe_B,N,N,C,W,M,1 - 0.002 xEAe_B,N,N,C,W,L,1
        + fire_B,N,N,C,W,1,P + fire_B,N,N,C,W,1,D + fire_B,N,N,C,W,1,W
        + fire_B,N,N,C,W,1,C + fire_B,N,N,C,W,1,I + fire_B,N,N,C,W,1,A
        + fire_B,N,N,C,W,1,L + fire_B,N,N,C,W,1,N  = 0
 c1832: - 0.002 xNG_B,N,N,C,W,S,2 - 0.002 xNG_B,N,N,C,W,P,2
        - 0.002 xNG_B,N,N,C,W,M,2 - 0.002 xNG_B,N,N,C,W,L,2
        - 0.002 xPB_B,N,N,C,W,S,0,2 - 0.002 xPB_B,N,N,C,W,S,1,2
        - 0.002 xPB_B,N,N,C,W,S,2,2 - 0.002 xPB_B,N,N,C,W,S,3,2
        - 0.002 xPB_B,N,N,C,W,S,4,2 - 0.002 xPB_B,N,N,C,W,P,0,2
        - 0.002 xPB_B,N,N,C,W,P,1,2 - 0.002 xPB_B,N,N,C,W,P,2,2
        - 0.002 xPB_B,N,N,C,W,P,3,2 - 0.002 xPB_B,N,N,C,W,P,4,2
        - 0.002 xPB_B,N,N,C,W,M,0,2 - 0.002 xPB_B,N,N,C,W,M,1,2
        - 0.002 xPB_B,N,N,C,W,M,2,2 - 0.002 xPB_B,N,N,C,W,M,3,2
        - 0.002 xPB_B,N,N,C,W,M,4,2 - 0.002 xPB_B,N,N,C,W,L,0,2
        - 0.002 xPB_B,N,N,C,W,L,1,2 - 0.002 xPB_B,N,N,C,W,L,2,2
        - 0.002 xPB_B,N,N,C,W,L,3,2 - 0.002 xPB_B,N,N,C,W,L,4,2
        - 0.002 xGS_B,N,N,C,W,S,0,2 - 0.002 xGS_B,N,N,C,W,S,1,2
        - 0.002 xGS_B,N,N,C,W,S,2,2 - 0.002 xGS_B,N,N,C,W,S,3,2
        - 0.002 xGS_B,N,N,C,W,S,4,2 - 0.002 xGS_B,N,N,C,W,P,0,2
        - 0.002 xGS_B,N,N,C,W,P,1,2 - 0.002 xGS_B,N,N,C,W,P,2,2
        - 0.002 xGS_B,N,N,C,W,P,3,2 - 0.002 xGS_B,N,N,C,W,P,4,2
        - 0.002 xGS_B,N,N,C,W,M,0,2 - 0.002 xGS_B,N,N,C,W,M,1,2
        - 0.002 xGS_B,N,N,C,W,M,2,2 - 0.002 xGS_B,N,N,C,W,M,3,2
        - 0.002 xGS_B,N,N,C,W,M,4,2 - 0.002 xGS_B,N,N,C,W,L,0,2
        - 0.002 xGS_B,N,N,C,W,L,1,2 - 0.002 xGS_B,N,N,C,W,L,2,2
        - 0.002 xGS_B,N,N,C,W,L,3,2 - 0.002 xGS_B,N,N,C,W,L,4,2
        - 0.002 xEAe_B,N,N,C,W,S,2 - 0.002 xEAe_B,N,N,C,W,P,2
        - 0.002 xEAe_B,N,N,C,W,M,2 - 0.002 xEAe_B,N,N,C,W,L,2
        - 0.002 xEAr_B,N,N,C,W,2,1 + fire_B,N,N,C,W,2,P + fire_B,N,N,C,W,2,D
        + fire_B,N,N,C,W,2,W + fire_B,N,N,C,W,2,C + fire_B,N,N,C,W,2,I
        + fire_B,N,N,C,W,2,A + fire_B,N,N,C,W,2,L + fire_B,N,N,C,W,2,N  = 0
 c1833: - 0.002 xNG_B,N,N,C,W,S,3 - 0.002 xNG_B,N,N,C,W,P,3
        - 0.002 xNG_B,N,N,C,W,M,3 - 0.002 xNG_B,N,N,C,W,L,3
        - 0.002 xPB_B,N,N,C,W,S,0,3 - 0.002 xPB_B,N,N,C,W,S,1,3
        - 0.002 xPB_B,N,N,C,W,S,2,3 - 0.002 xPB_B,N,N,C,W,S,3,3
        - 0.002 xPB_B,N,N,C,W,S,4,3 - 0.002 xPB_B,N,N,C,W,P,0,3
        - 0.002 xPB_B,N,N,C,W,P,1,3 - 0.002 xPB_B,N,N,C,W,P,2,3
        - 0.002 xPB_B,N,N,C,W,P,3,3 - 0.002 xPB_B,N,N,C,W,P,4,3
        - 0.002 xPB_B,N,N,C,W,M,0,3 - 0.002 xPB_B,N,N,C,W,M,1,3
        - 0.002 xPB_B,N,N,C,W,M,2,3 - 0.002 xPB_B,N,N,C,W,M,3,3
        - 0.002 xPB_B,N,N,C,W,M,4,3 - 0.002 xPB_B,N,N,C,W,L,0,3
        - 0.002 xPB_B,N,N,C,W,L,1,3 - 0.002 xPB_B,N,N,C,W,L,2,3
        - 0.002 xPB_B,N,N,C,W,L,3,3 - 0.002 xPB_B,N,N,C,W,L,4,3
        - 0.002 xGS_B,N,N,C,W,S,0,3 - 0.002 xGS_B,N,N,C,W,S,1,3
        - 0.002 xGS_B,N,N,C,W,S,2,3 - 0.002 xGS_B,N,N,C,W,S,3,3
        - 0.002 xGS_B,N,N,C,W,S,4,3 - 0.002 xGS_B,N,N,C,W,P,0,3
        - 0.002 xGS_B,N,N,C,W,P,1,3 - 0.002 xGS_B,N,N,C,W,P,2,3
        - 0.002 xGS_B,N,N,C,W,P,3,3 - 0.002 xGS_B,N,N,C,W,P,4,3
        - 0.002 xGS_B,N,N,C,W,M,0,3 - 0.002 xGS_B,N,N,C,W,M,1,3
        - 0.002 xGS_B,N,N,C,W,M,2,3 - 0.002 xGS_B,N,N,C,W,M,3,3
        - 0.002 xGS_B,N,N,C,W,M,4,3 - 0.002 xGS_B,N,N,C,W,L,0,3
        - 0.002 xGS_B,N,N,C,W,L,1,3 - 0.002 xGS_B,N,N,C,W,L,2,3
        - 0.002 xGS_B,N,N,C,W,L,3,3 - 0.002 xGS_B,N,N,C,W,L,4,3
        - 0.002 xEAe_B,N,N,C,W,S,3 - 0.002 xEAe_B,N,N,C,W,P,3
        - 0.002 xEAe_B,N,N,C,W,M,3 - 0.002 xEAe_B,N,N,C,W,L,3
        - 0.002 xEAr_B,N,N,C,W,3,1 - 0.002 xEAr_B,N,N,C,W,3,2
        + fire_B,N,N,C,W,3,P + fire_B,N,N,C,W,3,D + fire_B,N,N,C,W,3,W
        + fire_B,N,N,C,W,3,C + fire_B,N,N,C,W,3,I + fire_B,N,N,C,W,3,A
        + fire_B,N,N,C,W,3,L + fire_B,N,N,C,W,3,N  = 0
 c1834: - 0.002 xNG_B,N,N,C,W,S,4 - 0.002 xNG_B,N,N,C,W,P,4
        - 0.002 xNG_B,N,N,C,W,M,4 - 0.002 xNG_B,N,N,C,W,L,4
        - 0.002 xPB_B,N,N,C,W,S,0,4 - 0.002 xPB_B,N,N,C,W,S,1,4
        - 0.002 xPB_B,N,N,C,W,S,2,4 - 0.002 xPB_B,N,N,C,W,S,3,4
        - 0.002 xPB_B,N,N,C,W,S,4,4 - 0.002 xPB_B,N,N,C,W,P,0,4
        - 0.002 xPB_B,N,N,C,W,P,1,4 - 0.002 xPB_B,N,N,C,W,P,2,4
        - 0.002 xPB_B,N,N,C,W,P,3,4 - 0.002 xPB_B,N,N,C,W,P,4,4
        - 0.002 xPB_B,N,N,C,W,M,0,4 - 0.002 xPB_B,N,N,C,W,M,1,4
        - 0.002 xPB_B,N,N,C,W,M,2,4 - 0.002 xPB_B,N,N,C,W,M,3,4
        - 0.002 xPB_B,N,N,C,W,M,4,4 - 0.002 xPB_B,N,N,C,W,L,0,4
        - 0.002 xPB_B,N,N,C,W,L,1,4 - 0.002 xPB_B,N,N,C,W,L,2,4
        - 0.002 xPB_B,N,N,C,W,L,3,4 - 0.002 xPB_B,N,N,C,W,L,4,4
        - 0.002 xGS_B,N,N,C,W,S,0,4 - 0.002 xGS_B,N,N,C,W,S,1,4
        - 0.002 xGS_B,N,N,C,W,S,2,4 - 0.002 xGS_B,N,N,C,W,S,3,4
        - 0.002 xGS_B,N,N,C,W,S,4,4 - 0.002 xGS_B,N,N,C,W,P,0,4
        - 0.002 xGS_B,N,N,C,W,P,1,4 - 0.002 xGS_B,N,N,C,W,P,2,4
        - 0.002 xGS_B,N,N,C,W,P,3,4 - 0.002 xGS_B,N,N,C,W,P,4,4
        - 0.002 xGS_B,N,N,C,W,M,0,4 - 0.002 xGS_B,N,N,C,W,M,1,4
        - 0.002 xGS_B,N,N,C,W,M,2,4 - 0.002 xGS_B,N,N,C,W,M,3,4
        - 0.002 xGS_B,N,N,C,W,M,4,4 - 0.002 xGS_B,N,N,C,W,L,0,4
        - 0.002 xGS_B,N,N,C,W,L,1,4 - 0.002 xGS_B,N,N,C,W,L,2,4
        - 0.002 xGS_B,N,N,C,W,L,3,4 - 0.002 xGS_B,N,N,C,W,L,4,4
        - 0.002 xEAe_B,N,N,C,W,S,4 - 0.002 xEAe_B,N,N,C,W,P,4
        - 0.002 xEAe_B,N,N,C,W,M,4 - 0.002 xEAe_B,N,N,C,W,L,4
        - 0.002 xEAr_B,N,N,C,W,4,1 - 0.002 xEAr_B,N,N,C,W,4,2
        - 0.002 xEAr_B,N,N,C,W,4,3 + fire_B,N,N,C,W,4,P + fire_B,N,N,C,W,4,D
        + fire_B,N,N,C,W,4,W + fire_B,N,N,C,W,4,C + fire_B,N,N,C,W,4,I
        + fire_B,N,N,C,W,4,A + fire_B,N,N,C,W,4,L + fire_B,N,N,C,W,4,N  = 0
 c1835: - 0.002 xNG_B,N,N,C,W,S,5 - 0.002 xNG_B,N,N,C,W,P,5
        - 0.002 xNG_B,N,N,C,W,M,5 - 0.002 xNG_B,N,N,C,W,L,5
        - 0.002 xPB_B,N,N,C,W,S,0,5 - 0.002 xPB_B,N,N,C,W,S,1,5
        - 0.002 xPB_B,N,N,C,W,S,2,5 - 0.002 xPB_B,N,N,C,W,S,3,5
        - 0.002 xPB_B,N,N,C,W,S,4,5 - 0.002 xPB_B,N,N,C,W,P,0,5
        - 0.002 xPB_B,N,N,C,W,P,1,5 - 0.002 xPB_B,N,N,C,W,P,2,5
        - 0.002 xPB_B,N,N,C,W,P,3,5 - 0.002 xPB_B,N,N,C,W,P,4,5
        - 0.002 xPB_B,N,N,C,W,M,0,5 - 0.002 xPB_B,N,N,C,W,M,1,5
        - 0.002 xPB_B,N,N,C,W,M,2,5 - 0.002 xPB_B,N,N,C,W,M,3,5
        - 0.002 xPB_B,N,N,C,W,M,4,5 - 0.002 xPB_B,N,N,C,W,L,0,5
        - 0.002 xPB_B,N,N,C,W,L,1,5 - 0.002 xPB_B,N,N,C,W,L,2,5
        - 0.002 xPB_B,N,N,C,W,L,3,5 - 0.002 xPB_B,N,N,C,W,L,4,5
        - 0.002 xGS_B,N,N,C,W,S,0,5 - 0.002 xGS_B,N,N,C,W,S,1,5
        - 0.002 xGS_B,N,N,C,W,S,2,5 - 0.002 xGS_B,N,N,C,W,S,3,5
        - 0.002 xGS_B,N,N,C,W,S,4,5 - 0.002 xGS_B,N,N,C,W,P,0,5
        - 0.002 xGS_B,N,N,C,W,P,1,5 - 0.002 xGS_B,N,N,C,W,P,2,5
        - 0.002 xGS_B,N,N,C,W,P,3,5 - 0.002 xGS_B,N,N,C,W,P,4,5
        - 0.002 xGS_B,N,N,C,W,M,0,5 - 0.002 xGS_B,N,N,C,W,M,1,5
        - 0.002 xGS_B,N,N,C,W,M,2,5 - 0.002 xGS_B,N,N,C,W,M,3,5
        - 0.002 xGS_B,N,N,C,W,M,4,5 - 0.002 xGS_B,N,N,C,W,L,0,5
        - 0.002 xGS_B,N,N,C,W,L,1,5 - 0.002 xGS_B,N,N,C,W,L,2,5
        - 0.002 xGS_B,N,N,C,W,L,3,5 - 0.002 xGS_B,N,N,C,W,L,4,5
        - 0.002 xEAe_B,N,N,C,W,S,5 - 0.002 xEAe_B,N,N,C,W,P,5
        - 0.002 xEAe_B,N,N,C,W,M,5 - 0.002 xEAe_B,N,N,C,W,L,5
        - 0.002 xEAr_B,N,N,C,W,5,1 - 0.002 xEAr_B,N,N,C,W,5,2
        - 0.002 xEAr_B,N,N,C,W,5,3 - 0.002 xEAr_B,N,N,C,W,5,4
        + fire_B,N,N,C,W,5,P + fire_B,N,N,C,W,5,D + fire_B,N,N,C,W,5,W
        + fire_B,N,N,C,W,5,C + fire_B,N,N,C,W,5,I + fire_B,N,N,C,W,5,A
        + fire_B,N,N,C,W,5,L + fire_B,N,N,C,W,5,N  = 0
 c1836: - 0.002 xNG_B,N,N,C,C,S,1 - 0.002 xNG_B,N,N,C,C,P,1
        - 0.002 xNG_B,N,N,C,C,M,1 - 0.002 xNG_B,N,N,C,C,L,1
        - 0.002 xPB_B,N,N,C,C,S,0,1 - 0.002 xPB_B,N,N,C,C,S,1,1
        - 0.002 xPB_B,N,N,C,C,S,2,1 - 0.002 xPB_B,N,N,C,C,S,3,1
        - 0.002 xPB_B,N,N,C,C,S,4,1 - 0.002 xPB_B,N,N,C,C,P,0,1
        - 0.002 xPB_B,N,N,C,C,P,1,1 - 0.002 xPB_B,N,N,C,C,P,2,1
        - 0.002 xPB_B,N,N,C,C,P,3,1 - 0.002 xPB_B,N,N,C,C,P,4,1
        - 0.002 xPB_B,N,N,C,C,M,0,1 - 0.002 xPB_B,N,N,C,C,M,1,1
        - 0.002 xPB_B,N,N,C,C,M,2,1 - 0.002 xPB_B,N,N,C,C,M,3,1
        - 0.002 xPB_B,N,N,C,C,M,4,1 - 0.002 xPB_B,N,N,C,C,L,0,1
        - 0.002 xPB_B,N,N,C,C,L,1,1 - 0.002 xPB_B,N,N,C,C,L,2,1
        - 0.002 xPB_B,N,N,C,C,L,3,1 - 0.002 xPB_B,N,N,C,C,L,4,1
        - 0.002 xGS_B,N,N,C,C,S,0,1 - 0.002 xGS_B,N,N,C,C,S,1,1
        - 0.002 xGS_B,N,N,C,C,S,2,1 - 0.002 xGS_B,N,N,C,C,S,3,1
        - 0.002 xGS_B,N,N,C,C,S,4,1 - 0.002 xGS_B,N,N,C,C,P,0,1
        - 0.002 xGS_B,N,N,C,C,P,1,1 - 0.002 xGS_B,N,N,C,C,P,2,1
        - 0.002 xGS_B,N,N,C,C,P,3,1 - 0.002 xGS_B,N,N,C,C,P,4,1
        - 0.002 xGS_B,N,N,C,C,M,0,1 - 0.002 xGS_B,N,N,C,C,M,1,1
        - 0.002 xGS_B,N,N,C,C,M,2,1 - 0.002 xGS_B,N,N,C,C,M,3,1
        - 0.002 xGS_B,N,N,C,C,M,4,1 - 0.002 xGS_B,N,N,C,C,L,0,1
        - 0.002 xGS_B,N,N,C,C,L,1,1 - 0.002 xGS_B,N,N,C,C,L,2,1
        - 0.002 xGS_B,N,N,C,C,L,3,1 - 0.002 xGS_B,N,N,C,C,L,4,1
        - 0.002 xEAe_B,N,N,C,C,S,1 - 0.002 xEAe_B,N,N,C,C,P,1
        - 0.002 xEAe_B,N,N,C,C,M,1 - 0.002 xEAe_B,N,N,C,C,L,1
        + fire_B,N,N,C,C,1,P + fire_B,N,N,C,C,1,D + fire_B,N,N,C,C,1,W
        + fire_B,N,N,C,C,1,C + fire_B,N,N,C,C,1,I + fire_B,N,N,C,C,1,A
        + fire_B,N,N,C,C,1,L + fire_B,N,N,C,C,1,N  = 0
 c1837: - 0.002 xNG_B,N,N,C,C,S,2 - 0.002 xNG_B,N,N,C,C,P,2
        - 0.002 xNG_B,N,N,C,C,M,2 - 0.002 xNG_B,N,N,C,C,L,2
        - 0.002 xPB_B,N,N,C,C,S,0,2 - 0.002 xPB_B,N,N,C,C,S,1,2
        - 0.002 xPB_B,N,N,C,C,S,2,2 - 0.002 xPB_B,N,N,C,C,S,3,2
        - 0.002 xPB_B,N,N,C,C,S,4,2 - 0.002 xPB_B,N,N,C,C,P,0,2
        - 0.002 xPB_B,N,N,C,C,P,1,2 - 0.002 xPB_B,N,N,C,C,P,2,2
        - 0.002 xPB_B,N,N,C,C,P,3,2 - 0.002 xPB_B,N,N,C,C,P,4,2
        - 0.002 xPB_B,N,N,C,C,M,0,2 - 0.002 xPB_B,N,N,C,C,M,1,2
        - 0.002 xPB_B,N,N,C,C,M,2,2 - 0.002 xPB_B,N,N,C,C,M,3,2
        - 0.002 xPB_B,N,N,C,C,M,4,2 - 0.002 xPB_B,N,N,C,C,L,0,2
        - 0.002 xPB_B,N,N,C,C,L,1,2 - 0.002 xPB_B,N,N,C,C,L,2,2
        - 0.002 xPB_B,N,N,C,C,L,3,2 - 0.002 xPB_B,N,N,C,C,L,4,2
        - 0.002 xGS_B,N,N,C,C,S,0,2 - 0.002 xGS_B,N,N,C,C,S,1,2
        - 0.002 xGS_B,N,N,C,C,S,2,2 - 0.002 xGS_B,N,N,C,C,S,3,2
        - 0.002 xGS_B,N,N,C,C,S,4,2 - 0.002 xGS_B,N,N,C,C,P,0,2
        - 0.002 xGS_B,N,N,C,C,P,1,2 - 0.002 xGS_B,N,N,C,C,P,2,2
        - 0.002 xGS_B,N,N,C,C,P,3,2 - 0.002 xGS_B,N,N,C,C,P,4,2
        - 0.002 xGS_B,N,N,C,C,M,0,2 - 0.002 xGS_B,N,N,C,C,M,1,2
        - 0.002 xGS_B,N,N,C,C,M,2,2 - 0.002 xGS_B,N,N,C,C,M,3,2
        - 0.002 xGS_B,N,N,C,C,M,4,2 - 0.002 xGS_B,N,N,C,C,L,0,2
        - 0.002 xGS_B,N,N,C,C,L,1,2 - 0.002 xGS_B,N,N,C,C,L,2,2
        - 0.002 xGS_B,N,N,C,C,L,3,2 - 0.002 xGS_B,N,N,C,C,L,4,2
        - 0.002 xEAe_B,N,N,C,C,S,2 - 0.002 xEAe_B,N,N,C,C,P,2
        - 0.002 xEAe_B,N,N,C,C,M,2 - 0.002 xEAe_B,N,N,C,C,L,2
        - 0.002 xEAr_B,N,N,C,C,2,1 + fire_B,N,N,C,C,2,P + fire_B,N,N,C,C,2,D
        + fire_B,N,N,C,C,2,W + fire_B,N,N,C,C,2,C + fire_B,N,N,C,C,2,I
        + fire_B,N,N,C,C,2,A + fire_B,N,N,C,C,2,L + fire_B,N,N,C,C,2,N  = 0
 c1838: - 0.002 xNG_B,N,N,C,C,S,3 - 0.002 xNG_B,N,N,C,C,P,3
        - 0.002 xNG_B,N,N,C,C,M,3 - 0.002 xNG_B,N,N,C,C,L,3
        - 0.002 xPB_B,N,N,C,C,S,0,3 - 0.002 xPB_B,N,N,C,C,S,1,3
        - 0.002 xPB_B,N,N,C,C,S,2,3 - 0.002 xPB_B,N,N,C,C,S,3,3
        - 0.002 xPB_B,N,N,C,C,S,4,3 - 0.002 xPB_B,N,N,C,C,P,0,3
        - 0.002 xPB_B,N,N,C,C,P,1,3 - 0.002 xPB_B,N,N,C,C,P,2,3
        - 0.002 xPB_B,N,N,C,C,P,3,3 - 0.002 xPB_B,N,N,C,C,P,4,3
        - 0.002 xPB_B,N,N,C,C,M,0,3 - 0.002 xPB_B,N,N,C,C,M,1,3
        - 0.002 xPB_B,N,N,C,C,M,2,3 - 0.002 xPB_B,N,N,C,C,M,3,3
        - 0.002 xPB_B,N,N,C,C,M,4,3 - 0.002 xPB_B,N,N,C,C,L,0,3
        - 0.002 xPB_B,N,N,C,C,L,1,3 - 0.002 xPB_B,N,N,C,C,L,2,3
        - 0.002 xPB_B,N,N,C,C,L,3,3 - 0.002 xPB_B,N,N,C,C,L,4,3
        - 0.002 xGS_B,N,N,C,C,S,0,3 - 0.002 xGS_B,N,N,C,C,S,1,3
        - 0.002 xGS_B,N,N,C,C,S,2,3 - 0.002 xGS_B,N,N,C,C,S,3,3
        - 0.002 xGS_B,N,N,C,C,S,4,3 - 0.002 xGS_B,N,N,C,C,P,0,3
        - 0.002 xGS_B,N,N,C,C,P,1,3 - 0.002 xGS_B,N,N,C,C,P,2,3
        - 0.002 xGS_B,N,N,C,C,P,3,3 - 0.002 xGS_B,N,N,C,C,P,4,3
        - 0.002 xGS_B,N,N,C,C,M,0,3 - 0.002 xGS_B,N,N,C,C,M,1,3
        - 0.002 xGS_B,N,N,C,C,M,2,3 - 0.002 xGS_B,N,N,C,C,M,3,3
        - 0.002 xGS_B,N,N,C,C,M,4,3 - 0.002 xGS_B,N,N,C,C,L,0,3
        - 0.002 xGS_B,N,N,C,C,L,1,3 - 0.002 xGS_B,N,N,C,C,L,2,3
        - 0.002 xGS_B,N,N,C,C,L,3,3 - 0.002 xGS_B,N,N,C,C,L,4,3
        - 0.002 xEAe_B,N,N,C,C,S,3 - 0.002 xEAe_B,N,N,C,C,P,3
        - 0.002 xEAe_B,N,N,C,C,M,3 - 0.002 xEAe_B,N,N,C,C,L,3
        - 0.002 xEAr_B,N,N,C,C,3,1 - 0.002 xEAr_B,N,N,C,C,3,2
        + fire_B,N,N,C,C,3,P + fire_B,N,N,C,C,3,D + fire_B,N,N,C,C,3,W
        + fire_B,N,N,C,C,3,C + fire_B,N,N,C,C,3,I + fire_B,N,N,C,C,3,A
        + fire_B,N,N,C,C,3,L + fire_B,N,N,C,C,3,N  = 0
 c1839: - 0.002 xNG_B,N,N,C,C,S,4 - 0.002 xNG_B,N,N,C,C,P,4
        - 0.002 xNG_B,N,N,C,C,M,4 - 0.002 xNG_B,N,N,C,C,L,4
        - 0.002 xPB_B,N,N,C,C,S,0,4 - 0.002 xPB_B,N,N,C,C,S,1,4
        - 0.002 xPB_B,N,N,C,C,S,2,4 - 0.002 xPB_B,N,N,C,C,S,3,4
        - 0.002 xPB_B,N,N,C,C,S,4,4 - 0.002 xPB_B,N,N,C,C,P,0,4
        - 0.002 xPB_B,N,N,C,C,P,1,4 - 0.002 xPB_B,N,N,C,C,P,2,4
        - 0.002 xPB_B,N,N,C,C,P,3,4 - 0.002 xPB_B,N,N,C,C,P,4,4
        - 0.002 xPB_B,N,N,C,C,M,0,4 - 0.002 xPB_B,N,N,C,C,M,1,4
        - 0.002 xPB_B,N,N,C,C,M,2,4 - 0.002 xPB_B,N,N,C,C,M,3,4
        - 0.002 xPB_B,N,N,C,C,M,4,4 - 0.002 xPB_B,N,N,C,C,L,0,4
        - 0.002 xPB_B,N,N,C,C,L,1,4 - 0.002 xPB_B,N,N,C,C,L,2,4
        - 0.002 xPB_B,N,N,C,C,L,3,4 - 0.002 xPB_B,N,N,C,C,L,4,4
        - 0.002 xGS_B,N,N,C,C,S,0,4 - 0.002 xGS_B,N,N,C,C,S,1,4
        - 0.002 xGS_B,N,N,C,C,S,2,4 - 0.002 xGS_B,N,N,C,C,S,3,4
        - 0.002 xGS_B,N,N,C,C,S,4,4 - 0.002 xGS_B,N,N,C,C,P,0,4
        - 0.002 xGS_B,N,N,C,C,P,1,4 - 0.002 xGS_B,N,N,C,C,P,2,4
        - 0.002 xGS_B,N,N,C,C,P,3,4 - 0.002 xGS_B,N,N,C,C,P,4,4
        - 0.002 xGS_B,N,N,C,C,M,0,4 - 0.002 xGS_B,N,N,C,C,M,1,4
        - 0.002 xGS_B,N,N,C,C,M,2,4 - 0.002 xGS_B,N,N,C,C,M,3,4
        - 0.002 xGS_B,N,N,C,C,M,4,4 - 0.002 xGS_B,N,N,C,C,L,0,4
        - 0.002 xGS_B,N,N,C,C,L,1,4 - 0.002 xGS_B,N,N,C,C,L,2,4
        - 0.002 xGS_B,N,N,C,C,L,3,4 - 0.002 xGS_B,N,N,C,C,L,4,4
        - 0.002 xEAe_B,N,N,C,C,S,4 - 0.002 xEAe_B,N,N,C,C,P,4
        - 0.002 xEAe_B,N,N,C,C,M,4 - 0.002 xEAe_B,N,N,C,C,L,4
        - 0.002 xEAr_B,N,N,C,C,4,1 - 0.002 xEAr_B,N,N,C,C,4,2
        - 0.002 xEAr_B,N,N,C,C,4,3 + fire_B,N,N,C,C,4,P + fire_B,N,N,C,C,4,D
        + fire_B,N,N,C,C,4,W + fire_B,N,N,C,C,4,C + fire_B,N,N,C,C,4,I
        + fire_B,N,N,C,C,4,A + fire_B,N,N,C,C,4,L + fire_B,N,N,C,C,4,N  = 0
 c1840: - 0.002 xNG_B,N,N,C,C,S,5 - 0.002 xNG_B,N,N,C,C,P,5
        - 0.002 xNG_B,N,N,C,C,M,5 - 0.002 xNG_B,N,N,C,C,L,5
        - 0.002 xPB_B,N,N,C,C,S,0,5 - 0.002 xPB_B,N,N,C,C,S,1,5
        - 0.002 xPB_B,N,N,C,C,S,2,5 - 0.002 xPB_B,N,N,C,C,S,3,5
        - 0.002 xPB_B,N,N,C,C,S,4,5 - 0.002 xPB_B,N,N,C,C,P,0,5
        - 0.002 xPB_B,N,N,C,C,P,1,5 - 0.002 xPB_B,N,N,C,C,P,2,5
        - 0.002 xPB_B,N,N,C,C,P,3,5 - 0.002 xPB_B,N,N,C,C,P,4,5
        - 0.002 xPB_B,N,N,C,C,M,0,5 - 0.002 xPB_B,N,N,C,C,M,1,5
        - 0.002 xPB_B,N,N,C,C,M,2,5 - 0.002 xPB_B,N,N,C,C,M,3,5
        - 0.002 xPB_B,N,N,C,C,M,4,5 - 0.002 xPB_B,N,N,C,C,L,0,5
        - 0.002 xPB_B,N,N,C,C,L,1,5 - 0.002 xPB_B,N,N,C,C,L,2,5
        - 0.002 xPB_B,N,N,C,C,L,3,5 - 0.002 xPB_B,N,N,C,C,L,4,5
        - 0.002 xGS_B,N,N,C,C,S,0,5 - 0.002 xGS_B,N,N,C,C,S,1,5
        - 0.002 xGS_B,N,N,C,C,S,2,5 - 0.002 xGS_B,N,N,C,C,S,3,5
        - 0.002 xGS_B,N,N,C,C,S,4,5 - 0.002 xGS_B,N,N,C,C,P,0,5
        - 0.002 xGS_B,N,N,C,C,P,1,5 - 0.002 xGS_B,N,N,C,C,P,2,5
        - 0.002 xGS_B,N,N,C,C,P,3,5 - 0.002 xGS_B,N,N,C,C,P,4,5
        - 0.002 xGS_B,N,N,C,C,M,0,5 - 0.002 xGS_B,N,N,C,C,M,1,5
        - 0.002 xGS_B,N,N,C,C,M,2,5 - 0.002 xGS_B,N,N,C,C,M,3,5
        - 0.002 xGS_B,N,N,C,C,M,4,5 - 0.002 xGS_B,N,N,C,C,L,0,5
        - 0.002 xGS_B,N,N,C,C,L,1,5 - 0.002 xGS_B,N,N,C,C,L,2,5
        - 0.002 xGS_B,N,N,C,C,L,3,5 - 0.002 xGS_B,N,N,C,C,L,4,5
        - 0.002 xEAe_B,N,N,C,C,S,5 - 0.002 xEAe_B,N,N,C,C,P,5
        - 0.002 xEAe_B,N,N,C,C,M,5 - 0.002 xEAe_B,N,N,C,C,L,5
        - 0.002 xEAr_B,N,N,C,C,5,1 - 0.002 xEAr_B,N,N,C,C,5,2
        - 0.002 xEAr_B,N,N,C,C,5,3 - 0.002 xEAr_B,N,N,C,C,5,4
        + fire_B,N,N,C,C,5,P + fire_B,N,N,C,C,5,D + fire_B,N,N,C,C,5,W
        + fire_B,N,N,C,C,5,C + fire_B,N,N,C,C,5,I + fire_B,N,N,C,C,5,A
        + fire_B,N,N,C,C,5,L + fire_B,N,N,C,C,5,N  = 0
 c1841: - 0.002 xNG_B,N,N,C,I,M,1 - 0.002 xNG_B,N,N,C,I,L,1
        - 0.002 xPB_B,N,N,C,I,M,0,1 - 0.002 xPB_B,N,N,C,I,M,1,1
        - 0.002 xPB_B,N,N,C,I,M,2,1 - 0.002 xPB_B,N,N,C,I,M,3,1
        - 0.002 xPB_B,N,N,C,I,M,4,1 - 0.002 xPB_B,N,N,C,I,L,0,1
        - 0.002 xPB_B,N,N,C,I,L,1,1 - 0.002 xPB_B,N,N,C,I,L,2,1
        - 0.002 xPB_B,N,N,C,I,L,3,1 - 0.002 xPB_B,N,N,C,I,L,4,1
        - 0.002 xGS_B,N,N,C,I,M,0,1 - 0.002 xGS_B,N,N,C,I,M,1,1
        - 0.002 xGS_B,N,N,C,I,M,2,1 - 0.002 xGS_B,N,N,C,I,M,3,1
        - 0.002 xGS_B,N,N,C,I,M,4,1 - 0.002 xGS_B,N,N,C,I,L,0,1
        - 0.002 xGS_B,N,N,C,I,L,1,1 - 0.002 xGS_B,N,N,C,I,L,2,1
        - 0.002 xGS_B,N,N,C,I,L,3,1 - 0.002 xGS_B,N,N,C,I,L,4,1
        - 0.002 xEAe_B,N,N,C,I,M,1 - 0.002 xEAe_B,N,N,C,I,L,1
        + fire_B,N,N,C,I,1,P + fire_B,N,N,C,I,1,D + fire_B,N,N,C,I,1,W
        + fire_B,N,N,C,I,1,C + fire_B,N,N,C,I,1,I + fire_B,N,N,C,I,1,A
        + fire_B,N,N,C,I,1,L + fire_B,N,N,C,I,1,N  = 0
 c1842: - 0.002 xNG_B,N,N,C,I,M,2 - 0.002 xNG_B,N,N,C,I,L,2
        - 0.002 xPB_B,N,N,C,I,M,0,2 - 0.002 xPB_B,N,N,C,I,M,1,2
        - 0.002 xPB_B,N,N,C,I,M,2,2 - 0.002 xPB_B,N,N,C,I,M,3,2
        - 0.002 xPB_B,N,N,C,I,M,4,2 - 0.002 xPB_B,N,N,C,I,L,0,2
        - 0.002 xPB_B,N,N,C,I,L,1,2 - 0.002 xPB_B,N,N,C,I,L,2,2
        - 0.002 xPB_B,N,N,C,I,L,3,2 - 0.002 xPB_B,N,N,C,I,L,4,2
        - 0.002 xGS_B,N,N,C,I,M,0,2 - 0.002 xGS_B,N,N,C,I,M,1,2
        - 0.002 xGS_B,N,N,C,I,M,2,2 - 0.002 xGS_B,N,N,C,I,M,3,2
        - 0.002 xGS_B,N,N,C,I,M,4,2 - 0.002 xGS_B,N,N,C,I,L,0,2
        - 0.002 xGS_B,N,N,C,I,L,1,2 - 0.002 xGS_B,N,N,C,I,L,2,2
        - 0.002 xGS_B,N,N,C,I,L,3,2 - 0.002 xGS_B,N,N,C,I,L,4,2
        - 0.002 xEAe_B,N,N,C,I,M,2 - 0.002 xEAe_B,N,N,C,I,L,2
        - 0.002 xEAr_B,N,N,C,I,2,1 + fire_B,N,N,C,I,2,P + fire_B,N,N,C,I,2,D
        + fire_B,N,N,C,I,2,W + fire_B,N,N,C,I,2,C + fire_B,N,N,C,I,2,I
        + fire_B,N,N,C,I,2,A + fire_B,N,N,C,I,2,L + fire_B,N,N,C,I,2,N  = 0
 c1843: - 0.002 xNG_B,N,N,C,I,M,3 - 0.002 xNG_B,N,N,C,I,L,3
        - 0.002 xPB_B,N,N,C,I,M,0,3 - 0.002 xPB_B,N,N,C,I,M,1,3
        - 0.002 xPB_B,N,N,C,I,M,2,3 - 0.002 xPB_B,N,N,C,I,M,3,3
        - 0.002 xPB_B,N,N,C,I,M,4,3 - 0.002 xPB_B,N,N,C,I,L,0,3
        - 0.002 xPB_B,N,N,C,I,L,1,3 - 0.002 xPB_B,N,N,C,I,L,2,3
        - 0.002 xPB_B,N,N,C,I,L,3,3 - 0.002 xPB_B,N,N,C,I,L,4,3
        - 0.002 xGS_B,N,N,C,I,M,0,3 - 0.002 xGS_B,N,N,C,I,M,1,3
        - 0.002 xGS_B,N,N,C,I,M,2,3 - 0.002 xGS_B,N,N,C,I,M,3,3
        - 0.002 xGS_B,N,N,C,I,M,4,3 - 0.002 xGS_B,N,N,C,I,L,0,3
        - 0.002 xGS_B,N,N,C,I,L,1,3 - 0.002 xGS_B,N,N,C,I,L,2,3
        - 0.002 xGS_B,N,N,C,I,L,3,3 - 0.002 xGS_B,N,N,C,I,L,4,3
        - 0.002 xEAe_B,N,N,C,I,M,3 - 0.002 xEAe_B,N,N,C,I,L,3
        - 0.002 xEAr_B,N,N,C,I,3,1 - 0.002 xEAr_B,N,N,C,I,3,2
        + fire_B,N,N,C,I,3,P + fire_B,N,N,C,I,3,D + fire_B,N,N,C,I,3,W
        + fire_B,N,N,C,I,3,C + fire_B,N,N,C,I,3,I + fire_B,N,N,C,I,3,A
        + fire_B,N,N,C,I,3,L + fire_B,N,N,C,I,3,N  = 0
 c1844: - 0.002 xNG_B,N,N,C,I,M,4 - 0.002 xNG_B,N,N,C,I,L,4
        - 0.002 xPB_B,N,N,C,I,M,0,4 - 0.002 xPB_B,N,N,C,I,M,1,4
        - 0.002 xPB_B,N,N,C,I,M,2,4 - 0.002 xPB_B,N,N,C,I,M,3,4
        - 0.002 xPB_B,N,N,C,I,M,4,4 - 0.002 xPB_B,N,N,C,I,L,0,4
        - 0.002 xPB_B,N,N,C,I,L,1,4 - 0.002 xPB_B,N,N,C,I,L,2,4
        - 0.002 xPB_B,N,N,C,I,L,3,4 - 0.002 xPB_B,N,N,C,I,L,4,4
        - 0.002 xGS_B,N,N,C,I,M,0,4 - 0.002 xGS_B,N,N,C,I,M,1,4
        - 0.002 xGS_B,N,N,C,I,M,2,4 - 0.002 xGS_B,N,N,C,I,M,3,4
        - 0.002 xGS_B,N,N,C,I,M,4,4 - 0.002 xGS_B,N,N,C,I,L,0,4
        - 0.002 xGS_B,N,N,C,I,L,1,4 - 0.002 xGS_B,N,N,C,I,L,2,4
        - 0.002 xGS_B,N,N,C,I,L,3,4 - 0.002 xGS_B,N,N,C,I,L,4,4
        - 0.002 xEAe_B,N,N,C,I,M,4 - 0.002 xEAe_B,N,N,C,I,L,4
        - 0.002 xEAr_B,N,N,C,I,4,1 - 0.002 xEAr_B,N,N,C,I,4,2
        - 0.002 xEAr_B,N,N,C,I,4,3 + fire_B,N,N,C,I,4,P + fire_B,N,N,C,I,4,D
        + fire_B,N,N,C,I,4,W + fire_B,N,N,C,I,4,C + fire_B,N,N,C,I,4,I
        + fire_B,N,N,C,I,4,A + fire_B,N,N,C,I,4,L + fire_B,N,N,C,I,4,N  = 0
 c1845: - 0.002 xNG_B,N,N,C,I,M,5 - 0.002 xNG_B,N,N,C,I,L,5
        - 0.002 xPB_B,N,N,C,I,M,0,5 - 0.002 xPB_B,N,N,C,I,M,1,5
        - 0.002 xPB_B,N,N,C,I,M,2,5 - 0.002 xPB_B,N,N,C,I,M,3,5
        - 0.002 xPB_B,N,N,C,I,M,4,5 - 0.002 xPB_B,N,N,C,I,L,0,5
        - 0.002 xPB_B,N,N,C,I,L,1,5 - 0.002 xPB_B,N,N,C,I,L,2,5
        - 0.002 xPB_B,N,N,C,I,L,3,5 - 0.002 xPB_B,N,N,C,I,L,4,5
        - 0.002 xGS_B,N,N,C,I,M,0,5 - 0.002 xGS_B,N,N,C,I,M,1,5
        - 0.002 xGS_B,N,N,C,I,M,2,5 - 0.002 xGS_B,N,N,C,I,M,3,5
        - 0.002 xGS_B,N,N,C,I,M,4,5 - 0.002 xGS_B,N,N,C,I,L,0,5
        - 0.002 xGS_B,N,N,C,I,L,1,5 - 0.002 xGS_B,N,N,C,I,L,2,5
        - 0.002 xGS_B,N,N,C,I,L,3,5 - 0.002 xGS_B,N,N,C,I,L,4,5
        - 0.002 xEAe_B,N,N,C,I,M,5 - 0.002 xEAe_B,N,N,C,I,L,5
        - 0.002 xEAr_B,N,N,C,I,5,1 - 0.002 xEAr_B,N,N,C,I,5,2
        - 0.002 xEAr_B,N,N,C,I,5,3 - 0.002 xEAr_B,N,N,C,I,5,4
        + fire_B,N,N,C,I,5,P + fire_B,N,N,C,I,5,D + fire_B,N,N,C,I,5,W
        + fire_B,N,N,C,I,5,C + fire_B,N,N,C,I,5,I + fire_B,N,N,C,I,5,A
        + fire_B,N,N,C,I,5,L + fire_B,N,N,C,I,5,N  = 0
 c1846: - 0.002 xNG_B,N,N,C,A,P,1 - 0.002 xNG_B,N,N,C,A,M,1
        - 0.002 xNG_B,N,N,C,A,L,1 - 0.002 xPB_B,N,N,C,A,P,0,1
        - 0.002 xPB_B,N,N,C,A,P,1,1 - 0.002 xPB_B,N,N,C,A,P,2,1
        - 0.002 xPB_B,N,N,C,A,P,3,1 - 0.002 xPB_B,N,N,C,A,P,4,1
        - 0.002 xPB_B,N,N,C,A,M,0,1 - 0.002 xPB_B,N,N,C,A,M,1,1
        - 0.002 xPB_B,N,N,C,A,M,2,1 - 0.002 xPB_B,N,N,C,A,M,3,1
        - 0.002 xPB_B,N,N,C,A,M,4,1 - 0.002 xPB_B,N,N,C,A,L,0,1
        - 0.002 xPB_B,N,N,C,A,L,1,1 - 0.002 xPB_B,N,N,C,A,L,2,1
        - 0.002 xPB_B,N,N,C,A,L,3,1 - 0.002 xPB_B,N,N,C,A,L,4,1
        - 0.002 xGS_B,N,N,C,A,P,0,1 - 0.002 xGS_B,N,N,C,A,P,1,1
        - 0.002 xGS_B,N,N,C,A,P,2,1 - 0.002 xGS_B,N,N,C,A,P,3,1
        - 0.002 xGS_B,N,N,C,A,P,4,1 - 0.002 xGS_B,N,N,C,A,M,0,1
        - 0.002 xGS_B,N,N,C,A,M,1,1 - 0.002 xGS_B,N,N,C,A,M,2,1
        - 0.002 xGS_B,N,N,C,A,M,3,1 - 0.002 xGS_B,N,N,C,A,M,4,1
        - 0.002 xGS_B,N,N,C,A,L,0,1 - 0.002 xGS_B,N,N,C,A,L,1,1
        - 0.002 xGS_B,N,N,C,A,L,2,1 - 0.002 xGS_B,N,N,C,A,L,3,1
        - 0.002 xGS_B,N,N,C,A,L,4,1 - 0.002 xEAe_B,N,N,C,A,P,1
        - 0.002 xEAe_B,N,N,C,A,M,1 - 0.002 xEAe_B,N,N,C,A,L,1
        + fire_B,N,N,C,A,1,P + fire_B,N,N,C,A,1,D + fire_B,N,N,C,A,1,W
        + fire_B,N,N,C,A,1,C + fire_B,N,N,C,A,1,I + fire_B,N,N,C,A,1,A
        + fire_B,N,N,C,A,1,L + fire_B,N,N,C,A,1,N  = 0
 c1847: - 0.002 xNG_B,N,N,C,A,P,2 - 0.002 xNG_B,N,N,C,A,M,2
        - 0.002 xNG_B,N,N,C,A,L,2 - 0.002 xPB_B,N,N,C,A,P,0,2
        - 0.002 xPB_B,N,N,C,A,P,1,2 - 0.002 xPB_B,N,N,C,A,P,2,2
        - 0.002 xPB_B,N,N,C,A,P,3,2 - 0.002 xPB_B,N,N,C,A,P,4,2
        - 0.002 xPB_B,N,N,C,A,M,0,2 - 0.002 xPB_B,N,N,C,A,M,1,2
        - 0.002 xPB_B,N,N,C,A,M,2,2 - 0.002 xPB_B,N,N,C,A,M,3,2
        - 0.002 xPB_B,N,N,C,A,M,4,2 - 0.002 xPB_B,N,N,C,A,L,0,2
        - 0.002 xPB_B,N,N,C,A,L,1,2 - 0.002 xPB_B,N,N,C,A,L,2,2
        - 0.002 xPB_B,N,N,C,A,L,3,2 - 0.002 xPB_B,N,N,C,A,L,4,2
        - 0.002 xGS_B,N,N,C,A,P,0,2 - 0.002 xGS_B,N,N,C,A,P,1,2
        - 0.002 xGS_B,N,N,C,A,P,2,2 - 0.002 xGS_B,N,N,C,A,P,3,2
        - 0.002 xGS_B,N,N,C,A,P,4,2 - 0.002 xGS_B,N,N,C,A,M,0,2
        - 0.002 xGS_B,N,N,C,A,M,1,2 - 0.002 xGS_B,N,N,C,A,M,2,2
        - 0.002 xGS_B,N,N,C,A,M,3,2 - 0.002 xGS_B,N,N,C,A,M,4,2
        - 0.002 xGS_B,N,N,C,A,L,0,2 - 0.002 xGS_B,N,N,C,A,L,1,2
        - 0.002 xGS_B,N,N,C,A,L,2,2 - 0.002 xGS_B,N,N,C,A,L,3,2
        - 0.002 xGS_B,N,N,C,A,L,4,2 - 0.002 xEAe_B,N,N,C,A,P,2
        - 0.002 xEAe_B,N,N,C,A,M,2 - 0.002 xEAe_B,N,N,C,A,L,2
        - 0.002 xEAr_B,N,N,C,A,2,1 + fire_B,N,N,C,A,2,P + fire_B,N,N,C,A,2,D
        + fire_B,N,N,C,A,2,W + fire_B,N,N,C,A,2,C + fire_B,N,N,C,A,2,I
        + fire_B,N,N,C,A,2,A + fire_B,N,N,C,A,2,L + fire_B,N,N,C,A,2,N  = 0
 c1848: - 0.002 xNG_B,N,N,C,A,P,3 - 0.002 xNG_B,N,N,C,A,M,3
        - 0.002 xNG_B,N,N,C,A,L,3 - 0.002 xPB_B,N,N,C,A,P,0,3
        - 0.002 xPB_B,N,N,C,A,P,1,3 - 0.002 xPB_B,N,N,C,A,P,2,3
        - 0.002 xPB_B,N,N,C,A,P,3,3 - 0.002 xPB_B,N,N,C,A,P,4,3
        - 0.002 xPB_B,N,N,C,A,M,0,3 - 0.002 xPB_B,N,N,C,A,M,1,3
        - 0.002 xPB_B,N,N,C,A,M,2,3 - 0.002 xPB_B,N,N,C,A,M,3,3
        - 0.002 xPB_B,N,N,C,A,M,4,3 - 0.002 xPB_B,N,N,C,A,L,0,3
        - 0.002 xPB_B,N,N,C,A,L,1,3 - 0.002 xPB_B,N,N,C,A,L,2,3
        - 0.002 xPB_B,N,N,C,A,L,3,3 - 0.002 xPB_B,N,N,C,A,L,4,3
        - 0.002 xGS_B,N,N,C,A,P,0,3 - 0.002 xGS_B,N,N,C,A,P,1,3
        - 0.002 xGS_B,N,N,C,A,P,2,3 - 0.002 xGS_B,N,N,C,A,P,3,3
        - 0.002 xGS_B,N,N,C,A,P,4,3 - 0.002 xGS_B,N,N,C,A,M,0,3
        - 0.002 xGS_B,N,N,C,A,M,1,3 - 0.002 xGS_B,N,N,C,A,M,2,3
        - 0.002 xGS_B,N,N,C,A,M,3,3 - 0.002 xGS_B,N,N,C,A,M,4,3
        - 0.002 xGS_B,N,N,C,A,L,0,3 - 0.002 xGS_B,N,N,C,A,L,1,3
        - 0.002 xGS_B,N,N,C,A,L,2,3 - 0.002 xGS_B,N,N,C,A,L,3,3
        - 0.002 xGS_B,N,N,C,A,L,4,3 - 0.002 xEAe_B,N,N,C,A,P,3
        - 0.002 xEAe_B,N,N,C,A,M,3 - 0.002 xEAe_B,N,N,C,A,L,3
        - 0.002 xEAr_B,N,N,C,A,3,1 - 0.002 xEAr_B,N,N,C,A,3,2
        + fire_B,N,N,C,A,3,P + fire_B,N,N,C,A,3,D + fire_B,N,N,C,A,3,W
        + fire_B,N,N,C,A,3,C + fire_B,N,N,C,A,3,I + fire_B,N,N,C,A,3,A
        + fire_B,N,N,C,A,3,L + fire_B,N,N,C,A,3,N  = 0
 c1849: - 0.002 xNG_B,N,N,C,A,P,4 - 0.002 xNG_B,N,N,C,A,M,4
        - 0.002 xNG_B,N,N,C,A,L,4 - 0.002 xPB_B,N,N,C,A,P,0,4
        - 0.002 xPB_B,N,N,C,A,P,1,4 - 0.002 xPB_B,N,N,C,A,P,2,4
        - 0.002 xPB_B,N,N,C,A,P,3,4 - 0.002 xPB_B,N,N,C,A,P,4,4
        - 0.002 xPB_B,N,N,C,A,M,0,4 - 0.002 xPB_B,N,N,C,A,M,1,4
        - 0.002 xPB_B,N,N,C,A,M,2,4 - 0.002 xPB_B,N,N,C,A,M,3,4
        - 0.002 xPB_B,N,N,C,A,M,4,4 - 0.002 xPB_B,N,N,C,A,L,0,4
        - 0.002 xPB_B,N,N,C,A,L,1,4 - 0.002 xPB_B,N,N,C,A,L,2,4
        - 0.002 xPB_B,N,N,C,A,L,3,4 - 0.002 xPB_B,N,N,C,A,L,4,4
        - 0.002 xGS_B,N,N,C,A,P,0,4 - 0.002 xGS_B,N,N,C,A,P,1,4
        - 0.002 xGS_B,N,N,C,A,P,2,4 - 0.002 xGS_B,N,N,C,A,P,3,4
        - 0.002 xGS_B,N,N,C,A,P,4,4 - 0.002 xGS_B,N,N,C,A,M,0,4
        - 0.002 xGS_B,N,N,C,A,M,1,4 - 0.002 xGS_B,N,N,C,A,M,2,4
        - 0.002 xGS_B,N,N,C,A,M,3,4 - 0.002 xGS_B,N,N,C,A,M,4,4
        - 0.002 xGS_B,N,N,C,A,L,0,4 - 0.002 xGS_B,N,N,C,A,L,1,4
        - 0.002 xGS_B,N,N,C,A,L,2,4 - 0.002 xGS_B,N,N,C,A,L,3,4
        - 0.002 xGS_B,N,N,C,A,L,4,4 - 0.002 xEAe_B,N,N,C,A,P,4
        - 0.002 xEAe_B,N,N,C,A,M,4 - 0.002 xEAe_B,N,N,C,A,L,4
        - 0.002 xEAr_B,N,N,C,A,4,1 - 0.002 xEAr_B,N,N,C,A,4,2
        - 0.002 xEAr_B,N,N,C,A,4,3 + fire_B,N,N,C,A,4,P + fire_B,N,N,C,A,4,D
        + fire_B,N,N,C,A,4,W + fire_B,N,N,C,A,4,C + fire_B,N,N,C,A,4,I
        + fire_B,N,N,C,A,4,A + fire_B,N,N,C,A,4,L + fire_B,N,N,C,A,4,N  = 0
 c1850: - 0.002 xNG_B,N,N,C,A,P,5 - 0.002 xNG_B,N,N,C,A,M,5
        - 0.002 xNG_B,N,N,C,A,L,5 - 0.002 xPB_B,N,N,C,A,P,0,5
        - 0.002 xPB_B,N,N,C,A,P,1,5 - 0.002 xPB_B,N,N,C,A,P,2,5
        - 0.002 xPB_B,N,N,C,A,P,3,5 - 0.002 xPB_B,N,N,C,A,P,4,5
        - 0.002 xPB_B,N,N,C,A,M,0,5 - 0.002 xPB_B,N,N,C,A,M,1,5
        - 0.002 xPB_B,N,N,C,A,M,2,5 - 0.002 xPB_B,N,N,C,A,M,3,5
        - 0.002 xPB_B,N,N,C,A,M,4,5 - 0.002 xPB_B,N,N,C,A,L,0,5
        - 0.002 xPB_B,N,N,C,A,L,1,5 - 0.002 xPB_B,N,N,C,A,L,2,5
        - 0.002 xPB_B,N,N,C,A,L,3,5 - 0.002 xPB_B,N,N,C,A,L,4,5
        - 0.002 xGS_B,N,N,C,A,P,0,5 - 0.002 xGS_B,N,N,C,A,P,1,5
        - 0.002 xGS_B,N,N,C,A,P,2,5 - 0.002 xGS_B,N,N,C,A,P,3,5
        - 0.002 xGS_B,N,N,C,A,P,4,5 - 0.002 xGS_B,N,N,C,A,M,0,5
        - 0.002 xGS_B,N,N,C,A,M,1,5 - 0.002 xGS_B,N,N,C,A,M,2,5
        - 0.002 xGS_B,N,N,C,A,M,3,5 - 0.002 xGS_B,N,N,C,A,M,4,5
        - 0.002 xGS_B,N,N,C,A,L,0,5 - 0.002 xGS_B,N,N,C,A,L,1,5
        - 0.002 xGS_B,N,N,C,A,L,2,5 - 0.002 xGS_B,N,N,C,A,L,3,5
        - 0.002 xGS_B,N,N,C,A,L,4,5 - 0.002 xEAe_B,N,N,C,A,P,5
        - 0.002 xEAe_B,N,N,C,A,M,5 - 0.002 xEAe_B,N,N,C,A,L,5
        - 0.002 xEAr_B,N,N,C,A,5,1 - 0.002 xEAr_B,N,N,C,A,5,2
        - 0.002 xEAr_B,N,N,C,A,5,3 - 0.002 xEAr_B,N,N,C,A,5,4
        + fire_B,N,N,C,A,5,P + fire_B,N,N,C,A,5,D + fire_B,N,N,C,A,5,W
        + fire_B,N,N,C,A,5,C + fire_B,N,N,C,A,5,I + fire_B,N,N,C,A,5,A
        + fire_B,N,N,C,A,5,L + fire_B,N,N,C,A,5,N  = 0
 c1851: - 0.002 xNG_B,N,N,C,L,M,1 - 0.002 xNG_B,N,N,C,L,L,1
        - 0.002 xPB_B,N,N,C,L,M,0,1 - 0.002 xPB_B,N,N,C,L,M,1,1
        - 0.002 xPB_B,N,N,C,L,M,2,1 - 0.002 xPB_B,N,N,C,L,M,3,1
        - 0.002 xPB_B,N,N,C,L,M,4,1 - 0.002 xPB_B,N,N,C,L,L,0,1
        - 0.002 xPB_B,N,N,C,L,L,1,1 - 0.002 xPB_B,N,N,C,L,L,2,1
        - 0.002 xPB_B,N,N,C,L,L,3,1 - 0.002 xPB_B,N,N,C,L,L,4,1
        - 0.002 xGS_B,N,N,C,L,M,0,1 - 0.002 xGS_B,N,N,C,L,M,1,1
        - 0.002 xGS_B,N,N,C,L,M,2,1 - 0.002 xGS_B,N,N,C,L,M,3,1
        - 0.002 xGS_B,N,N,C,L,M,4,1 - 0.002 xGS_B,N,N,C,L,L,0,1
        - 0.002 xGS_B,N,N,C,L,L,1,1 - 0.002 xGS_B,N,N,C,L,L,2,1
        - 0.002 xGS_B,N,N,C,L,L,3,1 - 0.002 xGS_B,N,N,C,L,L,4,1
        - 0.002 xEAe_B,N,N,C,L,M,1 - 0.002 xEAe_B,N,N,C,L,L,1
        + fire_B,N,N,C,L,1,P + fire_B,N,N,C,L,1,D + fire_B,N,N,C,L,1,W
        + fire_B,N,N,C,L,1,C + fire_B,N,N,C,L,1,I + fire_B,N,N,C,L,1,A
        + fire_B,N,N,C,L,1,L + fire_B,N,N,C,L,1,N  = 0
 c1852: - 0.002 xNG_B,N,N,C,L,M,2 - 0.002 xNG_B,N,N,C,L,L,2
        - 0.002 xPB_B,N,N,C,L,M,0,2 - 0.002 xPB_B,N,N,C,L,M,1,2
        - 0.002 xPB_B,N,N,C,L,M,2,2 - 0.002 xPB_B,N,N,C,L,M,3,2
        - 0.002 xPB_B,N,N,C,L,M,4,2 - 0.002 xPB_B,N,N,C,L,L,0,2
        - 0.002 xPB_B,N,N,C,L,L,1,2 - 0.002 xPB_B,N,N,C,L,L,2,2
        - 0.002 xPB_B,N,N,C,L,L,3,2 - 0.002 xPB_B,N,N,C,L,L,4,2
        - 0.002 xGS_B,N,N,C,L,M,0,2 - 0.002 xGS_B,N,N,C,L,M,1,2
        - 0.002 xGS_B,N,N,C,L,M,2,2 - 0.002 xGS_B,N,N,C,L,M,3,2
        - 0.002 xGS_B,N,N,C,L,M,4,2 - 0.002 xGS_B,N,N,C,L,L,0,2
        - 0.002 xGS_B,N,N,C,L,L,1,2 - 0.002 xGS_B,N,N,C,L,L,2,2
        - 0.002 xGS_B,N,N,C,L,L,3,2 - 0.002 xGS_B,N,N,C,L,L,4,2
        - 0.002 xEAe_B,N,N,C,L,M,2 - 0.002 xEAe_B,N,N,C,L,L,2
        - 0.002 xEAr_B,N,N,C,L,2,1 + fire_B,N,N,C,L,2,P + fire_B,N,N,C,L,2,D
        + fire_B,N,N,C,L,2,W + fire_B,N,N,C,L,2,C + fire_B,N,N,C,L,2,I
        + fire_B,N,N,C,L,2,A + fire_B,N,N,C,L,2,L + fire_B,N,N,C,L,2,N  = 0
 c1853: - 0.002 xNG_B,N,N,C,L,M,3 - 0.002 xNG_B,N,N,C,L,L,3
        - 0.002 xPB_B,N,N,C,L,M,0,3 - 0.002 xPB_B,N,N,C,L,M,1,3
        - 0.002 xPB_B,N,N,C,L,M,2,3 - 0.002 xPB_B,N,N,C,L,M,3,3
        - 0.002 xPB_B,N,N,C,L,M,4,3 - 0.002 xPB_B,N,N,C,L,L,0,3
        - 0.002 xPB_B,N,N,C,L,L,1,3 - 0.002 xPB_B,N,N,C,L,L,2,3
        - 0.002 xPB_B,N,N,C,L,L,3,3 - 0.002 xPB_B,N,N,C,L,L,4,3
        - 0.002 xGS_B,N,N,C,L,M,0,3 - 0.002 xGS_B,N,N,C,L,M,1,3
        - 0.002 xGS_B,N,N,C,L,M,2,3 - 0.002 xGS_B,N,N,C,L,M,3,3
        - 0.002 xGS_B,N,N,C,L,M,4,3 - 0.002 xGS_B,N,N,C,L,L,0,3
        - 0.002 xGS_B,N,N,C,L,L,1,3 - 0.002 xGS_B,N,N,C,L,L,2,3
        - 0.002 xGS_B,N,N,C,L,L,3,3 - 0.002 xGS_B,N,N,C,L,L,4,3
        - 0.002 xEAe_B,N,N,C,L,M,3 - 0.002 xEAe_B,N,N,C,L,L,3
        - 0.002 xEAr_B,N,N,C,L,3,1 - 0.002 xEAr_B,N,N,C,L,3,2
        + fire_B,N,N,C,L,3,P + fire_B,N,N,C,L,3,D + fire_B,N,N,C,L,3,W
        + fire_B,N,N,C,L,3,C + fire_B,N,N,C,L,3,I + fire_B,N,N,C,L,3,A
        + fire_B,N,N,C,L,3,L + fire_B,N,N,C,L,3,N  = 0
 c1854: - 0.002 xNG_B,N,N,C,L,M,4 - 0.002 xNG_B,N,N,C,L,L,4
        - 0.002 xPB_B,N,N,C,L,M,0,4 - 0.002 xPB_B,N,N,C,L,M,1,4
        - 0.002 xPB_B,N,N,C,L,M,2,4 - 0.002 xPB_B,N,N,C,L,M,3,4
        - 0.002 xPB_B,N,N,C,L,M,4,4 - 0.002 xPB_B,N,N,C,L,L,0,4
        - 0.002 xPB_B,N,N,C,L,L,1,4 - 0.002 xPB_B,N,N,C,L,L,2,4
        - 0.002 xPB_B,N,N,C,L,L,3,4 - 0.002 xPB_B,N,N,C,L,L,4,4
        - 0.002 xGS_B,N,N,C,L,M,0,4 - 0.002 xGS_B,N,N,C,L,M,1,4
        - 0.002 xGS_B,N,N,C,L,M,2,4 - 0.002 xGS_B,N,N,C,L,M,3,4
        - 0.002 xGS_B,N,N,C,L,M,4,4 - 0.002 xGS_B,N,N,C,L,L,0,4
        - 0.002 xGS_B,N,N,C,L,L,1,4 - 0.002 xGS_B,N,N,C,L,L,2,4
        - 0.002 xGS_B,N,N,C,L,L,3,4 - 0.002 xGS_B,N,N,C,L,L,4,4
        - 0.002 xEAe_B,N,N,C,L,M,4 - 0.002 xEAe_B,N,N,C,L,L,4
        - 0.002 xEAr_B,N,N,C,L,4,1 - 0.002 xEAr_B,N,N,C,L,4,2
        - 0.002 xEAr_B,N,N,C,L,4,3 + fire_B,N,N,C,L,4,P + fire_B,N,N,C,L,4,D
        + fire_B,N,N,C,L,4,W + fire_B,N,N,C,L,4,C + fire_B,N,N,C,L,4,I
        + fire_B,N,N,C,L,4,A + fire_B,N,N,C,L,4,L + fire_B,N,N,C,L,4,N  = 0
 c1855: - 0.002 xNG_B,N,N,C,L,M,5 - 0.002 xNG_B,N,N,C,L,L,5
        - 0.002 xPB_B,N,N,C,L,M,0,5 - 0.002 xPB_B,N,N,C,L,M,1,5
        - 0.002 xPB_B,N,N,C,L,M,2,5 - 0.002 xPB_B,N,N,C,L,M,3,5
        - 0.002 xPB_B,N,N,C,L,M,4,5 - 0.002 xPB_B,N,N,C,L,L,0,5
        - 0.002 xPB_B,N,N,C,L,L,1,5 - 0.002 xPB_B,N,N,C,L,L,2,5
        - 0.002 xPB_B,N,N,C,L,L,3,5 - 0.002 xPB_B,N,N,C,L,L,4,5
        - 0.002 xGS_B,N,N,C,L,M,0,5 - 0.002 xGS_B,N,N,C,L,M,1,5
        - 0.002 xGS_B,N,N,C,L,M,2,5 - 0.002 xGS_B,N,N,C,L,M,3,5
        - 0.002 xGS_B,N,N,C,L,M,4,5 - 0.002 xGS_B,N,N,C,L,L,0,5
        - 0.002 xGS_B,N,N,C,L,L,1,5 - 0.002 xGS_B,N,N,C,L,L,2,5
        - 0.002 xGS_B,N,N,C,L,L,3,5 - 0.002 xGS_B,N,N,C,L,L,4,5
        - 0.002 xEAe_B,N,N,C,L,M,5 - 0.002 xEAe_B,N,N,C,L,L,5
        - 0.002 xEAr_B,N,N,C,L,5,1 - 0.002 xEAr_B,N,N,C,L,5,2
        - 0.002 xEAr_B,N,N,C,L,5,3 - 0.002 xEAr_B,N,N,C,L,5,4
        + fire_B,N,N,C,L,5,P + fire_B,N,N,C,L,5,D + fire_B,N,N,C,L,5,W
        + fire_B,N,N,C,L,5,C + fire_B,N,N,C,L,5,I + fire_B,N,N,C,L,5,A
        + fire_B,N,N,C,L,5,L + fire_B,N,N,C,L,5,N  = 0
 c1856: fire_B,N,N,C,N,1,P + fire_B,N,N,C,N,1,D + fire_B,N,N,C,N,1,W
        + fire_B,N,N,C,N,1,C + fire_B,N,N,C,N,1,I + fire_B,N,N,C,N,1,A
        + fire_B,N,N,C,N,1,L + fire_B,N,N,C,N,1,N  = 0
 c1857: fire_B,N,N,C,N,2,P + fire_B,N,N,C,N,2,D + fire_B,N,N,C,N,2,W
        + fire_B,N,N,C,N,2,C + fire_B,N,N,C,N,2,I + fire_B,N,N,C,N,2,A
        + fire_B,N,N,C,N,2,L + fire_B,N,N,C,N,2,N  = 0
 c1858: fire_B,N,N,C,N,3,P + fire_B,N,N,C,N,3,D + fire_B,N,N,C,N,3,W
        + fire_B,N,N,C,N,3,C + fire_B,N,N,C,N,3,I + fire_B,N,N,C,N,3,A
        + fire_B,N,N,C,N,3,L + fire_B,N,N,C,N,3,N  = 0
 c1859: fire_B,N,N,C,N,4,P + fire_B,N,N,C,N,4,D + fire_B,N,N,C,N,4,W
        + fire_B,N,N,C,N,4,C + fire_B,N,N,C,N,4,I + fire_B,N,N,C,N,4,A
        + fire_B,N,N,C,N,4,L + fire_B,N,N,C,N,4,N  = 0
 c1860: fire_B,N,N,C,N,5,P + fire_B,N,N,C,N,5,D + fire_B,N,N,C,N,5,W
        + fire_B,N,N,C,N,5,C + fire_B,N,N,C,N,5,I + fire_B,N,N,C,N,5,A
        + fire_B,N,N,C,N,5,L + fire_B,N,N,C,N,5,N  = 0
 c1861: - fire_B,N,N,L,P,1,D - fire_B,N,N,L,P,1,W - fire_B,N,N,L,P,1,C
        - fire_B,N,N,L,P,1,I - fire_B,N,N,L,P,1,A - fire_B,N,N,L,P,1,L
        - fire_B,N,N,L,P,1,N  = 0
 c1862: fire_B,N,N,L,P,1,D  = 0
 c1863: fire_B,N,N,L,P,1,W  = 0
 c1864: fire_B,N,N,L,P,1,C  = 0
 c1865: fire_B,N,N,L,P,1,I  = 0
 c1866: fire_B,N,N,L,P,1,A  = 0
 c1867: fire_B,N,N,L,P,1,L  = 0
 c1868: fire_B,N,N,L,P,1,N  = 0
 c1869: - fire_B,N,N,L,P,2,D - fire_B,N,N,L,P,2,W - fire_B,N,N,L,P,2,C
        - fire_B,N,N,L,P,2,I - fire_B,N,N,L,P,2,A - fire_B,N,N,L,P,2,L
        - fire_B,N,N,L,P,2,N  = 0
 c1870: fire_B,N,N,L,P,2,D  = 0
 c1871: fire_B,N,N,L,P,2,W  = 0
 c1872: fire_B,N,N,L,P,2,C  = 0
 c1873: fire_B,N,N,L,P,2,I  = 0
 c1874: fire_B,N,N,L,P,2,A  = 0
 c1875: fire_B,N,N,L,P,2,L  = 0
 c1876: fire_B,N,N,L,P,2,N  = 0
 c1877: - fire_B,N,N,L,P,3,D - fire_B,N,N,L,P,3,W - fire_B,N,N,L,P,3,C
        - fire_B,N,N,L,P,3,I - fire_B,N,N,L,P,3,A - fire_B,N,N,L,P,3,L
        - fire_B,N,N,L,P,3,N  = 0
 c1878: fire_B,N,N,L,P,3,D  = 0
 c1879: fire_B,N,N,L,P,3,W  = 0
 c1880: fire_B,N,N,L,P,3,C  = 0
 c1881: fire_B,N,N,L,P,3,I  = 0
 c1882: fire_B,N,N,L,P,3,A  = 0
 c1883: fire_B,N,N,L,P,3,L  = 0
 c1884: fire_B,N,N,L,P,3,N  = 0
 c1885: - fire_B,N,N,L,P,4,D - fire_B,N,N,L,P,4,W - fire_B,N,N,L,P,4,C
        - fire_B,N,N,L,P,4,I - fire_B,N,N,L,P,4,A - fire_B,N,N,L,P,4,L
        - fire_B,N,N,L,P,4,N  = 0
 c1886: fire_B,N,N,L,P,4,D  = 0
 c1887: fire_B,N,N,L,P,4,W  = 0
 c1888: fire_B,N,N,L,P,4,C  = 0
 c1889: fire_B,N,N,L,P,4,I  = 0
 c1890: fire_B,N,N,L,P,4,A  = 0
 c1891: fire_B,N,N,L,P,4,L  = 0
 c1892: fire_B,N,N,L,P,4,N  = 0
 c1893: - fire_B,N,N,L,P,5,D - fire_B,N,N,L,P,5,W - fire_B,N,N,L,P,5,C
        - fire_B,N,N,L,P,5,I - fire_B,N,N,L,P,5,A - fire_B,N,N,L,P,5,L
        - fire_B,N,N,L,P,5,N  = 0
 c1894: fire_B,N,N,L,P,5,D  = 0
 c1895: fire_B,N,N,L,P,5,W  = 0
 c1896: fire_B,N,N,L,P,5,C  = 0
 c1897: fire_B,N,N,L,P,5,I  = 0
 c1898: fire_B,N,N,L,P,5,A  = 0
 c1899: fire_B,N,N,L,P,5,L  = 0
 c1900: fire_B,N,N,L,P,5,N  = 0
 c1901: fire_B,N,N,L,D,1,P  = 0
 c1902: - fire_B,N,N,L,D,1,P - fire_B,N,N,L,D,1,W - fire_B,N,N,L,D,1,C
        - fire_B,N,N,L,D,1,I - fire_B,N,N,L,D,1,A - fire_B,N,N,L,D,1,L
        - fire_B,N,N,L,D,1,N  = 0
 c1903: fire_B,N,N,L,D,1,W  = 0
 c1904: fire_B,N,N,L,D,1,C  = 0
 c1905: fire_B,N,N,L,D,1,I  = 0
 c1906: fire_B,N,N,L,D,1,A  = 0
 c1907: fire_B,N,N,L,D,1,L  = 0
 c1908: fire_B,N,N,L,D,1,N  = 0
 c1909: fire_B,N,N,L,D,2,P  = 0
 c1910: - fire_B,N,N,L,D,2,P - fire_B,N,N,L,D,2,W - fire_B,N,N,L,D,2,C
        - fire_B,N,N,L,D,2,I - fire_B,N,N,L,D,2,A - fire_B,N,N,L,D,2,L
        - fire_B,N,N,L,D,2,N  = 0
 c1911: fire_B,N,N,L,D,2,W  = 0
 c1912: fire_B,N,N,L,D,2,C  = 0
 c1913: fire_B,N,N,L,D,2,I  = 0
 c1914: fire_B,N,N,L,D,2,A  = 0
 c1915: fire_B,N,N,L,D,2,L  = 0
 c1916: fire_B,N,N,L,D,2,N  = 0
 c1917: fire_B,N,N,L,D,3,P  = 0
 c1918: - fire_B,N,N,L,D,3,P - fire_B,N,N,L,D,3,W - fire_B,N,N,L,D,3,C
        - fire_B,N,N,L,D,3,I - fire_B,N,N,L,D,3,A - fire_B,N,N,L,D,3,L
        - fire_B,N,N,L,D,3,N  = 0
 c1919: fire_B,N,N,L,D,3,W  = 0
 c1920: fire_B,N,N,L,D,3,C  = 0
 c1921: fire_B,N,N,L,D,3,I  = 0
 c1922: fire_B,N,N,L,D,3,A  = 0
 c1923: fire_B,N,N,L,D,3,L  = 0
 c1924: fire_B,N,N,L,D,3,N  = 0
 c1925: fire_B,N,N,L,D,4,P  = 0
 c1926: - fire_B,N,N,L,D,4,P - fire_B,N,N,L,D,4,W - fire_B,N,N,L,D,4,C
        - fire_B,N,N,L,D,4,I - fire_B,N,N,L,D,4,A - fire_B,N,N,L,D,4,L
        - fire_B,N,N,L,D,4,N  = 0
 c1927: fire_B,N,N,L,D,4,W  = 0
 c1928: fire_B,N,N,L,D,4,C  = 0
 c1929: fire_B,N,N,L,D,4,I  = 0
 c1930: fire_B,N,N,L,D,4,A  = 0
 c1931: fire_B,N,N,L,D,4,L  = 0
 c1932: fire_B,N,N,L,D,4,N  = 0
 c1933: fire_B,N,N,L,D,5,P  = 0
 c1934: - fire_B,N,N,L,D,5,P - fire_B,N,N,L,D,5,W - fire_B,N,N,L,D,5,C
        - fire_B,N,N,L,D,5,I - fire_B,N,N,L,D,5,A - fire_B,N,N,L,D,5,L
        - fire_B,N,N,L,D,5,N  = 0
 c1935: fire_B,N,N,L,D,5,W  = 0
 c1936: fire_B,N,N,L,D,5,C  = 0
 c1937: fire_B,N,N,L,D,5,I  = 0
 c1938: fire_B,N,N,L,D,5,A  = 0
 c1939: fire_B,N,N,L,D,5,L  = 0
 c1940: fire_B,N,N,L,D,5,N  = 0
 c1941: fire_B,N,N,L,W,1,P  = 0
 c1942: fire_B,N,N,L,W,1,D  = 0
 c1943: - fire_B,N,N,L,W,1,P - fire_B,N,N,L,W,1,D - fire_B,N,N,L,W,1,C
        - fire_B,N,N,L,W,1,I - fire_B,N,N,L,W,1,A - fire_B,N,N,L,W,1,L
        - fire_B,N,N,L,W,1,N  = 0
 c1944: fire_B,N,N,L,W,1,C  = 0
 c1945: fire_B,N,N,L,W,1,I  = 0
 c1946: fire_B,N,N,L,W,1,A  = 0
 c1947: fire_B,N,N,L,W,1,L  = 0
 c1948: fire_B,N,N,L,W,1,N  = 0
 c1949: fire_B,N,N,L,W,2,P  = 0
 c1950: fire_B,N,N,L,W,2,D  = 0
 c1951: - fire_B,N,N,L,W,2,P - fire_B,N,N,L,W,2,D - fire_B,N,N,L,W,2,C
        - fire_B,N,N,L,W,2,I - fire_B,N,N,L,W,2,A - fire_B,N,N,L,W,2,L
        - fire_B,N,N,L,W,2,N  = 0
 c1952: fire_B,N,N,L,W,2,C  = 0
 c1953: fire_B,N,N,L,W,2,I  = 0
 c1954: fire_B,N,N,L,W,2,A  = 0
 c1955: fire_B,N,N,L,W,2,L  = 0
 c1956: fire_B,N,N,L,W,2,N  = 0
 c1957: fire_B,N,N,L,W,3,P  = 0
 c1958: fire_B,N,N,L,W,3,D  = 0
 c1959: - fire_B,N,N,L,W,3,P - fire_B,N,N,L,W,3,D - fire_B,N,N,L,W,3,C
        - fire_B,N,N,L,W,3,I - fire_B,N,N,L,W,3,A - fire_B,N,N,L,W,3,L
        - fire_B,N,N,L,W,3,N  = 0
 c1960: fire_B,N,N,L,W,3,C  = 0
 c1961: fire_B,N,N,L,W,3,I  = 0
 c1962: fire_B,N,N,L,W,3,A  = 0
 c1963: fire_B,N,N,L,W,3,L  = 0
 c1964: fire_B,N,N,L,W,3,N  = 0
 c1965: fire_B,N,N,L,W,4,P  = 0
 c1966: fire_B,N,N,L,W,4,D  = 0
 c1967: - fire_B,N,N,L,W,4,P - fire_B,N,N,L,W,4,D - fire_B,N,N,L,W,4,C
        - fire_B,N,N,L,W,4,I - fire_B,N,N,L,W,4,A - fire_B,N,N,L,W,4,L
        - fire_B,N,N,L,W,4,N  = 0
 c1968: fire_B,N,N,L,W,4,C  = 0
 c1969: fire_B,N,N,L,W,4,I  = 0
 c1970: fire_B,N,N,L,W,4,A  = 0
 c1971: fire_B,N,N,L,W,4,L  = 0
 c1972: fire_B,N,N,L,W,4,N  = 0
 c1973: fire_B,N,N,L,W,5,P  = 0
 c1974: fire_B,N,N,L,W,5,D  = 0
 c1975: - fire_B,N,N,L,W,5,P - fire_B,N,N,L,W,5,D - fire_B,N,N,L,W,5,C
        - fire_B,N,N,L,W,5,I - fire_B,N,N,L,W,5,A - fire_B,N,N,L,W,5,L
        - fire_B,N,N,L,W,5,N  = 0
 c1976: fire_B,N,N,L,W,5,C  = 0
 c1977: fire_B,N,N,L,W,5,I  = 0
 c1978: fire_B,N,N,L,W,5,A  = 0
 c1979: fire_B,N,N,L,W,5,L  = 0
 c1980: fire_B,N,N,L,W,5,N  = 0
 c1981: fire_B,N,N,L,C,1,P  = 0
 c1982: fire_B,N,N,L,C,1,D  = 0
 c1983: fire_B,N,N,L,C,1,W  = 0
 c1984: - fire_B,N,N,L,C,1,P - fire_B,N,N,L,C,1,D - fire_B,N,N,L,C,1,W
        - fire_B,N,N,L,C,1,I - fire_B,N,N,L,C,1,A - fire_B,N,N,L,C,1,L
        - fire_B,N,N,L,C,1,N  = 0
 c1985: fire_B,N,N,L,C,1,I  = 0
 c1986: fire_B,N,N,L,C,1,A  = 0
 c1987: fire_B,N,N,L,C,1,L  = 0
 c1988: fire_B,N,N,L,C,1,N  = 0
 c1989: fire_B,N,N,L,C,2,P  = 0
 c1990: fire_B,N,N,L,C,2,D  = 0
 c1991: fire_B,N,N,L,C,2,W  = 0
 c1992: - fire_B,N,N,L,C,2,P - fire_B,N,N,L,C,2,D - fire_B,N,N,L,C,2,W
        - fire_B,N,N,L,C,2,I - fire_B,N,N,L,C,2,A - fire_B,N,N,L,C,2,L
        - fire_B,N,N,L,C,2,N  = 0
 c1993: fire_B,N,N,L,C,2,I  = 0
 c1994: fire_B,N,N,L,C,2,A  = 0
 c1995: fire_B,N,N,L,C,2,L  = 0
 c1996: fire_B,N,N,L,C,2,N  = 0
 c1997: fire_B,N,N,L,C,3,P  = 0
 c1998: fire_B,N,N,L,C,3,D  = 0
 c1999: fire_B,N,N,L,C,3,W  = 0
 c2000: - fire_B,N,N,L,C,3,P - fire_B,N,N,L,C,3,D - fire_B,N,N,L,C,3,W
        - fire_B,N,N,L,C,3,I - fire_B,N,N,L,C,3,A - fire_B,N,N,L,C,3,L
        - fire_B,N,N,L,C,3,N  = 0
 c2001: fire_B,N,N,L,C,3,I  = 0
 c2002: fire_B,N,N,L,C,3,A  = 0
 c2003: fire_B,N,N,L,C,3,L  = 0
 c2004: fire_B,N,N,L,C,3,N  = 0
 c2005: fire_B,N,N,L,C,4,P  = 0
 c2006: fire_B,N,N,L,C,4,D  = 0
 c2007: fire_B,N,N,L,C,4,W  = 0
 c2008: - fire_B,N,N,L,C,4,P - fire_B,N,N,L,C,4,D - fire_B,N,N,L,C,4,W
        - fire_B,N,N,L,C,4,I - fire_B,N,N,L,C,4,A - fire_B,N,N,L,C,4,L
        - fire_B,N,N,L,C,4,N  = 0
 c2009: fire_B,N,N,L,C,4,I  = 0
 c2010: fire_B,N,N,L,C,4,A  = 0
 c2011: fire_B,N,N,L,C,4,L  = 0
 c2012: fire_B,N,N,L,C,4,N  = 0
 c2013: fire_B,N,N,L,C,5,P  = 0
 c2014: fire_B,N,N,L,C,5,D  = 0
 c2015: fire_B,N,N,L,C,5,W  = 0
 c2016: - fire_B,N,N,L,C,5,P - fire_B,N,N,L,C,5,D - fire_B,N,N,L,C,5,W
        - fire_B,N,N,L,C,5,I - fire_B,N,N,L,C,5,A - fire_B,N,N,L,C,5,L
        - fire_B,N,N,L,C,5,N  = 0
 c2017: fire_B,N,N,L,C,5,I  = 0
 c2018: fire_B,N,N,L,C,5,A  = 0
 c2019: fire_B,N,N,L,C,5,L  = 0
 c2020: fire_B,N,N,L,C,5,N  = 0
 c2021: fire_B,N,N,L,I,1,P  = 0
 c2022: fire_B,N,N,L,I,1,D  = 0
 c2023: fire_B,N,N,L,I,1,W  = 0
 c2024: fire_B,N,N,L,I,1,C  = 0
 c2025: - fire_B,N,N,L,I,1,P - fire_B,N,N,L,I,1,D - fire_B,N,N,L,I,1,W
        - fire_B,N,N,L,I,1,C - fire_B,N,N,L,I,1,A - fire_B,N,N,L,I,1,L
        - fire_B,N,N,L,I,1,N  = 0
 c2026: fire_B,N,N,L,I,1,A  = 0
 c2027: fire_B,N,N,L,I,1,L  = 0
 c2028: fire_B,N,N,L,I,1,N  = 0
 c2029: fire_B,N,N,L,I,2,P  = 0
 c2030: fire_B,N,N,L,I,2,D  = 0
 c2031: fire_B,N,N,L,I,2,W  = 0
 c2032: fire_B,N,N,L,I,2,C  = 0
 c2033: - fire_B,N,N,L,I,2,P - fire_B,N,N,L,I,2,D - fire_B,N,N,L,I,2,W
        - fire_B,N,N,L,I,2,C - fire_B,N,N,L,I,2,A - fire_B,N,N,L,I,2,L
        - fire_B,N,N,L,I,2,N  = 0
 c2034: fire_B,N,N,L,I,2,A  = 0
 c2035: fire_B,N,N,L,I,2,L  = 0
 c2036: fire_B,N,N,L,I,2,N  = 0
 c2037: fire_B,N,N,L,I,3,P  = 0
 c2038: fire_B,N,N,L,I,3,D  = 0
 c2039: fire_B,N,N,L,I,3,W  = 0
 c2040: fire_B,N,N,L,I,3,C  = 0
 c2041: - fire_B,N,N,L,I,3,P - fire_B,N,N,L,I,3,D - fire_B,N,N,L,I,3,W
        - fire_B,N,N,L,I,3,C - fire_B,N,N,L,I,3,A - fire_B,N,N,L,I,3,L
        - fire_B,N,N,L,I,3,N  = 0
 c2042: fire_B,N,N,L,I,3,A  = 0
 c2043: fire_B,N,N,L,I,3,L  = 0
 c2044: fire_B,N,N,L,I,3,N  = 0
 c2045: fire_B,N,N,L,I,4,P  = 0
 c2046: fire_B,N,N,L,I,4,D  = 0
 c2047: fire_B,N,N,L,I,4,W  = 0
 c2048: fire_B,N,N,L,I,4,C  = 0
 c2049: - fire_B,N,N,L,I,4,P - fire_B,N,N,L,I,4,D - fire_B,N,N,L,I,4,W
        - fire_B,N,N,L,I,4,C - fire_B,N,N,L,I,4,A - fire_B,N,N,L,I,4,L
        - fire_B,N,N,L,I,4,N  = 0
 c2050: fire_B,N,N,L,I,4,A  = 0
 c2051: fire_B,N,N,L,I,4,L  = 0
 c2052: fire_B,N,N,L,I,4,N  = 0
 c2053: fire_B,N,N,L,I,5,P  = 0
 c2054: fire_B,N,N,L,I,5,D  = 0
 c2055: fire_B,N,N,L,I,5,W  = 0
 c2056: fire_B,N,N,L,I,5,C  = 0
 c2057: - fire_B,N,N,L,I,5,P - fire_B,N,N,L,I,5,D - fire_B,N,N,L,I,5,W
        - fire_B,N,N,L,I,5,C - fire_B,N,N,L,I,5,A - fire_B,N,N,L,I,5,L
        - fire_B,N,N,L,I,5,N  = 0
 c2058: fire_B,N,N,L,I,5,A  = 0
 c2059: fire_B,N,N,L,I,5,L  = 0
 c2060: fire_B,N,N,L,I,5,N  = 0
 c2061: fire_B,N,N,L,A,1,P  = 0
 c2062: fire_B,N,N,L,A,1,D  = 0
 c2063: fire_B,N,N,L,A,1,W  = 0
 c2064: fire_B,N,N,L,A,1,C  = 0
 c2065: fire_B,N,N,L,A,1,I  = 0
 c2066: - fire_B,N,N,L,A,1,P - fire_B,N,N,L,A,1,D - fire_B,N,N,L,A,1,W
        - fire_B,N,N,L,A,1,C - fire_B,N,N,L,A,1,I - fire_B,N,N,L,A,1,L
        - fire_B,N,N,L,A,1,N  = 0
 c2067: fire_B,N,N,L,A,1,L  = 0
 c2068: fire_B,N,N,L,A,1,N  = 0
 c2069: fire_B,N,N,L,A,2,P  = 0
 c2070: fire_B,N,N,L,A,2,D  = 0
 c2071: fire_B,N,N,L,A,2,W  = 0
 c2072: fire_B,N,N,L,A,2,C  = 0
 c2073: fire_B,N,N,L,A,2,I  = 0
 c2074: - fire_B,N,N,L,A,2,P - fire_B,N,N,L,A,2,D - fire_B,N,N,L,A,2,W
        - fire_B,N,N,L,A,2,C - fire_B,N,N,L,A,2,I - fire_B,N,N,L,A,2,L
        - fire_B,N,N,L,A,2,N  = 0
 c2075: fire_B,N,N,L,A,2,L  = 0
 c2076: fire_B,N,N,L,A,2,N  = 0
 c2077: fire_B,N,N,L,A,3,P  = 0
 c2078: fire_B,N,N,L,A,3,D  = 0
 c2079: fire_B,N,N,L,A,3,W  = 0
 c2080: fire_B,N,N,L,A,3,C  = 0
 c2081: fire_B,N,N,L,A,3,I  = 0
 c2082: - fire_B,N,N,L,A,3,P - fire_B,N,N,L,A,3,D - fire_B,N,N,L,A,3,W
        - fire_B,N,N,L,A,3,C - fire_B,N,N,L,A,3,I - fire_B,N,N,L,A,3,L
        - fire_B,N,N,L,A,3,N  = 0
 c2083: fire_B,N,N,L,A,3,L  = 0
 c2084: fire_B,N,N,L,A,3,N  = 0
 c2085: fire_B,N,N,L,A,4,P  = 0
 c2086: fire_B,N,N,L,A,4,D  = 0
 c2087: fire_B,N,N,L,A,4,W  = 0
 c2088: fire_B,N,N,L,A,4,C  = 0
 c2089: fire_B,N,N,L,A,4,I  = 0
 c2090: - fire_B,N,N,L,A,4,P - fire_B,N,N,L,A,4,D - fire_B,N,N,L,A,4,W
        - fire_B,N,N,L,A,4,C - fire_B,N,N,L,A,4,I - fire_B,N,N,L,A,4,L
        - fire_B,N,N,L,A,4,N  = 0
 c2091: fire_B,N,N,L,A,4,L  = 0
 c2092: fire_B,N,N,L,A,4,N  = 0
 c2093: fire_B,N,N,L,A,5,P  = 0
 c2094: fire_B,N,N,L,A,5,D  = 0
 c2095: fire_B,N,N,L,A,5,W  = 0
 c2096: fire_B,N,N,L,A,5,C  = 0
 c2097: fire_B,N,N,L,A,5,I  = 0
 c2098: - fire_B,N,N,L,A,5,P - fire_B,N,N,L,A,5,D - fire_B,N,N,L,A,5,W
        - fire_B,N,N,L,A,5,C - fire_B,N,N,L,A,5,I - fire_B,N,N,L,A,5,L
        - fire_B,N,N,L,A,5,N  = 0
 c2099: fire_B,N,N,L,A,5,L  = 0
 c2100: fire_B,N,N,L,A,5,N  = 0
 c2101: fire_B,N,N,L,L,1,P  = 0
 c2102: fire_B,N,N,L,L,1,D  = 0
 c2103: fire_B,N,N,L,L,1,W  = 0
 c2104: fire_B,N,N,L,L,1,C  = 0
 c2105: fire_B,N,N,L,L,1,I  = 0
 c2106: fire_B,N,N,L,L,1,A  = 0
 c2107: - fire_B,N,N,L,L,1,P - fire_B,N,N,L,L,1,D - fire_B,N,N,L,L,1,W
        - fire_B,N,N,L,L,1,C - fire_B,N,N,L,L,1,I - fire_B,N,N,L,L,1,A
        - fire_B,N,N,L,L,1,N  = 0
 c2108: fire_B,N,N,L,L,1,N  = 0
 c2109: fire_B,N,N,L,L,2,P  = 0
 c2110: fire_B,N,N,L,L,2,D  = 0
 c2111: fire_B,N,N,L,L,2,W  = 0
 c2112: fire_B,N,N,L,L,2,C  = 0
 c2113: fire_B,N,N,L,L,2,I  = 0
 c2114: fire_B,N,N,L,L,2,A  = 0
 c2115: - fire_B,N,N,L,L,2,P - fire_B,N,N,L,L,2,D - fire_B,N,N,L,L,2,W
        - fire_B,N,N,L,L,2,C - fire_B,N,N,L,L,2,I - fire_B,N,N,L,L,2,A
        - fire_B,N,N,L,L,2,N  = 0
 c2116: fire_B,N,N,L,L,2,N  = 0
 c2117: fire_B,N,N,L,L,3,P  = 0
 c2118: fire_B,N,N,L,L,3,D  = 0
 c2119: fire_B,N,N,L,L,3,W  = 0
 c2120: fire_B,N,N,L,L,3,C  = 0
 c2121: fire_B,N,N,L,L,3,I  = 0
 c2122: fire_B,N,N,L,L,3,A  = 0
 c2123: - fire_B,N,N,L,L,3,P - fire_B,N,N,L,L,3,D - fire_B,N,N,L,L,3,W
        - fire_B,N,N,L,L,3,C - fire_B,N,N,L,L,3,I - fire_B,N,N,L,L,3,A
        - fire_B,N,N,L,L,3,N  = 0
 c2124: fire_B,N,N,L,L,3,N  = 0
 c2125: fire_B,N,N,L,L,4,P  = 0
 c2126: fire_B,N,N,L,L,4,D  = 0
 c2127: fire_B,N,N,L,L,4,W  = 0
 c2128: fire_B,N,N,L,L,4,C  = 0
 c2129: fire_B,N,N,L,L,4,I  = 0
 c2130: fire_B,N,N,L,L,4,A  = 0
 c2131: - fire_B,N,N,L,L,4,P - fire_B,N,N,L,L,4,D - fire_B,N,N,L,L,4,W
        - fire_B,N,N,L,L,4,C - fire_B,N,N,L,L,4,I - fire_B,N,N,L,L,4,A
        - fire_B,N,N,L,L,4,N  = 0
 c2132: fire_B,N,N,L,L,4,N  = 0
 c2133: fire_B,N,N,L,L,5,P  = 0
 c2134: fire_B,N,N,L,L,5,D  = 0
 c2135: fire_B,N,N,L,L,5,W  = 0
 c2136: fire_B,N,N,L,L,5,C  = 0
 c2137: fire_B,N,N,L,L,5,I  = 0
 c2138: fire_B,N,N,L,L,5,A  = 0
 c2139: - fire_B,N,N,L,L,5,P - fire_B,N,N,L,L,5,D - fire_B,N,N,L,L,5,W
        - fire_B,N,N,L,L,5,C - fire_B,N,N,L,L,5,I - fire_B,N,N,L,L,5,A
        - fire_B,N,N,L,L,5,N  = 0
 c2140: fire_B,N,N,L,L,5,N  = 0
 c2141: fire_B,N,N,L,N,1,P  = 0
 c2142: fire_B,N,N,L,N,1,D  = 0
 c2143: fire_B,N,N,L,N,1,W  = 0
 c2144: fire_B,N,N,L,N,1,C  = 0
 c2145: fire_B,N,N,L,N,1,I  = 0
 c2146: fire_B,N,N,L,N,1,A  = 0
 c2147: fire_B,N,N,L,N,1,L  = 0
 c2148: - fire_B,N,N,L,N,1,P - fire_B,N,N,L,N,1,D - fire_B,N,N,L,N,1,W
        - fire_B,N,N,L,N,1,C - fire_B,N,N,L,N,1,I - fire_B,N,N,L,N,1,A
        - fire_B,N,N,L,N,1,L  = 0
 c2149: fire_B,N,N,L,N,2,P  = 0
 c2150: fire_B,N,N,L,N,2,D  = 0
 c2151: fire_B,N,N,L,N,2,W  = 0
 c2152: fire_B,N,N,L,N,2,C  = 0
 c2153: fire_B,N,N,L,N,2,I  = 0
 c2154: fire_B,N,N,L,N,2,A  = 0
 c2155: fire_B,N,N,L,N,2,L  = 0
 c2156: - fire_B,N,N,L,N,2,P - fire_B,N,N,L,N,2,D - fire_B,N,N,L,N,2,W
        - fire_B,N,N,L,N,2,C - fire_B,N,N,L,N,2,I - fire_B,N,N,L,N,2,A
        - fire_B,N,N,L,N,2,L  = 0
 c2157: fire_B,N,N,L,N,3,P  = 0
 c2158: fire_B,N,N,L,N,3,D  = 0
 c2159: fire_B,N,N,L,N,3,W  = 0
 c2160: fire_B,N,N,L,N,3,C  = 0
 c2161: fire_B,N,N,L,N,3,I  = 0
 c2162: fire_B,N,N,L,N,3,A  = 0
 c2163: fire_B,N,N,L,N,3,L  = 0
 c2164: - fire_B,N,N,L,N,3,P - fire_B,N,N,L,N,3,D - fire_B,N,N,L,N,3,W
        - fire_B,N,N,L,N,3,C - fire_B,N,N,L,N,3,I - fire_B,N,N,L,N,3,A
        - fire_B,N,N,L,N,3,L  = 0
 c2165: fire_B,N,N,L,N,4,P  = 0
 c2166: fire_B,N,N,L,N,4,D  = 0
 c2167: fire_B,N,N,L,N,4,W  = 0
 c2168: fire_B,N,N,L,N,4,C  = 0
 c2169: fire_B,N,N,L,N,4,I  = 0
 c2170: fire_B,N,N,L,N,4,A  = 0
 c2171: fire_B,N,N,L,N,4,L  = 0
 c2172: - fire_B,N,N,L,N,4,P - fire_B,N,N,L,N,4,D - fire_B,N,N,L,N,4,W
        - fire_B,N,N,L,N,4,C - fire_B,N,N,L,N,4,I - fire_B,N,N,L,N,4,A
        - fire_B,N,N,L,N,4,L  = 0
 c2173: fire_B,N,N,L,N,5,P  = 0
 c2174: fire_B,N,N,L,N,5,D  = 0
 c2175: fire_B,N,N,L,N,5,W  = 0
 c2176: fire_B,N,N,L,N,5,C  = 0
 c2177: fire_B,N,N,L,N,5,I  = 0
 c2178: fire_B,N,N,L,N,5,A  = 0
 c2179: fire_B,N,N,L,N,5,L  = 0
 c2180: - fire_B,N,N,L,N,5,P - fire_B,N,N,L,N,5,D - fire_B,N,N,L,N,5,W
        - fire_B,N,N,L,N,5,C - fire_B,N,N,L,N,5,I - fire_B,N,N,L,N,5,A
        - fire_B,N,N,L,N,5,L  = 0
 c2181: - fire_B,N,N,H,P,1,D - fire_B,N,N,H,P,1,W - fire_B,N,N,H,P,1,C
        - fire_B,N,N,H,P,1,I - fire_B,N,N,H,P,1,A - fire_B,N,N,H,P,1,L
        - fire_B,N,N,H,P,1,N  = 0
 c2182: fire_B,N,N,H,P,1,D  = 0
 c2183: fire_B,N,N,H,P,1,W  = 0
 c2184: fire_B,N,N,H,P,1,C  = 0
 c2185: fire_B,N,N,H,P,1,I  = 0
 c2186: fire_B,N,N,H,P,1,A  = 0
 c2187: fire_B,N,N,H,P,1,L  = 0
 c2188: fire_B,N,N,H,P,1,N  = 0
 c2189: - fire_B,N,N,H,P,2,D - fire_B,N,N,H,P,2,W - fire_B,N,N,H,P,2,C
        - fire_B,N,N,H,P,2,I - fire_B,N,N,H,P,2,A - fire_B,N,N,H,P,2,L
        - fire_B,N,N,H,P,2,N  = 0
 c2190: fire_B,N,N,H,P,2,D  = 0
 c2191: fire_B,N,N,H,P,2,W  = 0
 c2192: fire_B,N,N,H,P,2,C  = 0
 c2193: fire_B,N,N,H,P,2,I  = 0
 c2194: fire_B,N,N,H,P,2,A  = 0
 c2195: fire_B,N,N,H,P,2,L  = 0
 c2196: fire_B,N,N,H,P,2,N  = 0
 c2197: - fire_B,N,N,H,P,3,D - fire_B,N,N,H,P,3,W - fire_B,N,N,H,P,3,C
        - fire_B,N,N,H,P,3,I - fire_B,N,N,H,P,3,A - fire_B,N,N,H,P,3,L
        - fire_B,N,N,H,P,3,N  = 0
 c2198: fire_B,N,N,H,P,3,D  = 0
 c2199: fire_B,N,N,H,P,3,W  = 0
 c2200: fire_B,N,N,H,P,3,C  = 0
 c2201: fire_B,N,N,H,P,3,I  = 0
 c2202: fire_B,N,N,H,P,3,A  = 0
 c2203: fire_B,N,N,H,P,3,L  = 0
 c2204: fire_B,N,N,H,P,3,N  = 0
 c2205: - fire_B,N,N,H,P,4,D - fire_B,N,N,H,P,4,W - fire_B,N,N,H,P,4,C
        - fire_B,N,N,H,P,4,I - fire_B,N,N,H,P,4,A - fire_B,N,N,H,P,4,L
        - fire_B,N,N,H,P,4,N  = 0
 c2206: fire_B,N,N,H,P,4,D  = 0
 c2207: fire_B,N,N,H,P,4,W  = 0
 c2208: fire_B,N,N,H,P,4,C  = 0
 c2209: fire_B,N,N,H,P,4,I  = 0
 c2210: fire_B,N,N,H,P,4,A  = 0
 c2211: fire_B,N,N,H,P,4,L  = 0
 c2212: fire_B,N,N,H,P,4,N  = 0
 c2213: - fire_B,N,N,H,P,5,D - fire_B,N,N,H,P,5,W - fire_B,N,N,H,P,5,C
        - fire_B,N,N,H,P,5,I - fire_B,N,N,H,P,5,A - fire_B,N,N,H,P,5,L
        - fire_B,N,N,H,P,5,N  = 0
 c2214: fire_B,N,N,H,P,5,D  = 0
 c2215: fire_B,N,N,H,P,5,W  = 0
 c2216: fire_B,N,N,H,P,5,C  = 0
 c2217: fire_B,N,N,H,P,5,I  = 0
 c2218: fire_B,N,N,H,P,5,A  = 0
 c2219: fire_B,N,N,H,P,5,L  = 0
 c2220: fire_B,N,N,H,P,5,N  = 0
 c2221: fire_B,N,N,H,D,1,P  = 0
 c2222: - fire_B,N,N,H,D,1,P - fire_B,N,N,H,D,1,W - fire_B,N,N,H,D,1,C
        - fire_B,N,N,H,D,1,I - fire_B,N,N,H,D,1,A - fire_B,N,N,H,D,1,L
        - fire_B,N,N,H,D,1,N  = 0
 c2223: fire_B,N,N,H,D,1,W  = 0
 c2224: fire_B,N,N,H,D,1,C  = 0
 c2225: fire_B,N,N,H,D,1,I  = 0
 c2226: fire_B,N,N,H,D,1,A  = 0
 c2227: fire_B,N,N,H,D,1,L  = 0
 c2228: fire_B,N,N,H,D,1,N  = 0
 c2229: fire_B,N,N,H,D,2,P  = 0
 c2230: - fire_B,N,N,H,D,2,P - fire_B,N,N,H,D,2,W - fire_B,N,N,H,D,2,C
        - fire_B,N,N,H,D,2,I - fire_B,N,N,H,D,2,A - fire_B,N,N,H,D,2,L
        - fire_B,N,N,H,D,2,N  = 0
 c2231: fire_B,N,N,H,D,2,W  = 0
 c2232: fire_B,N,N,H,D,2,C  = 0
 c2233: fire_B,N,N,H,D,2,I  = 0
 c2234: fire_B,N,N,H,D,2,A  = 0
 c2235: fire_B,N,N,H,D,2,L  = 0
 c2236: fire_B,N,N,H,D,2,N  = 0
 c2237: fire_B,N,N,H,D,3,P  = 0
 c2238: - fire_B,N,N,H,D,3,P - fire_B,N,N,H,D,3,W - fire_B,N,N,H,D,3,C
        - fire_B,N,N,H,D,3,I - fire_B,N,N,H,D,3,A - fire_B,N,N,H,D,3,L
        - fire_B,N,N,H,D,3,N  = 0
 c2239: fire_B,N,N,H,D,3,W  = 0
 c2240: fire_B,N,N,H,D,3,C  = 0
 c2241: fire_B,N,N,H,D,3,I  = 0
 c2242: fire_B,N,N,H,D,3,A  = 0
 c2243: fire_B,N,N,H,D,3,L  = 0
 c2244: fire_B,N,N,H,D,3,N  = 0
 c2245: fire_B,N,N,H,D,4,P  = 0
 c2246: - fire_B,N,N,H,D,4,P - fire_B,N,N,H,D,4,W - fire_B,N,N,H,D,4,C
        - fire_B,N,N,H,D,4,I - fire_B,N,N,H,D,4,A - fire_B,N,N,H,D,4,L
        - fire_B,N,N,H,D,4,N  = 0
 c2247: fire_B,N,N,H,D,4,W  = 0
 c2248: fire_B,N,N,H,D,4,C  = 0
 c2249: fire_B,N,N,H,D,4,I  = 0
 c2250: fire_B,N,N,H,D,4,A  = 0
 c2251: fire_B,N,N,H,D,4,L  = 0
 c2252: fire_B,N,N,H,D,4,N  = 0
 c2253: fire_B,N,N,H,D,5,P  = 0
 c2254: - fire_B,N,N,H,D,5,P - fire_B,N,N,H,D,5,W - fire_B,N,N,H,D,5,C
        - fire_B,N,N,H,D,5,I - fire_B,N,N,H,D,5,A - fire_B,N,N,H,D,5,L
        - fire_B,N,N,H,D,5,N  = 0
 c2255: fire_B,N,N,H,D,5,W  = 0
 c2256: fire_B,N,N,H,D,5,C  = 0
 c2257: fire_B,N,N,H,D,5,I  = 0
 c2258: fire_B,N,N,H,D,5,A  = 0
 c2259: fire_B,N,N,H,D,5,L  = 0
 c2260: fire_B,N,N,H,D,5,N  = 0
 c2261: fire_B,N,N,H,W,1,P  = 0
 c2262: fire_B,N,N,H,W,1,D  = 0
 c2263: - fire_B,N,N,H,W,1,P - fire_B,N,N,H,W,1,D - fire_B,N,N,H,W,1,C
        - fire_B,N,N,H,W,1,I - fire_B,N,N,H,W,1,A - fire_B,N,N,H,W,1,L
        - fire_B,N,N,H,W,1,N  = 0
 c2264: fire_B,N,N,H,W,1,C  = 0
 c2265: fire_B,N,N,H,W,1,I  = 0
 c2266: fire_B,N,N,H,W,1,A  = 0
 c2267: fire_B,N,N,H,W,1,L  = 0
 c2268: fire_B,N,N,H,W,1,N  = 0
 c2269: fire_B,N,N,H,W,2,P  = 0
 c2270: fire_B,N,N,H,W,2,D  = 0
 c2271: - fire_B,N,N,H,W,2,P - fire_B,N,N,H,W,2,D - fire_B,N,N,H,W,2,C
        - fire_B,N,N,H,W,2,I - fire_B,N,N,H,W,2,A - fire_B,N,N,H,W,2,L
        - fire_B,N,N,H,W,2,N  = 0
 c2272: fire_B,N,N,H,W,2,C  = 0
 c2273: fire_B,N,N,H,W,2,I  = 0
 c2274: fire_B,N,N,H,W,2,A  = 0
 c2275: fire_B,N,N,H,W,2,L  = 0
 c2276: fire_B,N,N,H,W,2,N  = 0
 c2277: fire_B,N,N,H,W,3,P  = 0
 c2278: fire_B,N,N,H,W,3,D  = 0
 c2279: - fire_B,N,N,H,W,3,P - fire_B,N,N,H,W,3,D - fire_B,N,N,H,W,3,C
        - fire_B,N,N,H,W,3,I - fire_B,N,N,H,W,3,A - fire_B,N,N,H,W,3,L
        - fire_B,N,N,H,W,3,N  = 0
 c2280: fire_B,N,N,H,W,3,C  = 0
 c2281: fire_B,N,N,H,W,3,I  = 0
 c2282: fire_B,N,N,H,W,3,A  = 0
 c2283: fire_B,N,N,H,W,3,L  = 0
 c2284: fire_B,N,N,H,W,3,N  = 0
 c2285: fire_B,N,N,H,W,4,P  = 0
 c2286: fire_B,N,N,H,W,4,D  = 0
 c2287: - fire_B,N,N,H,W,4,P - fire_B,N,N,H,W,4,D - fire_B,N,N,H,W,4,C
        - fire_B,N,N,H,W,4,I - fire_B,N,N,H,W,4,A - fire_B,N,N,H,W,4,L
        - fire_B,N,N,H,W,4,N  = 0
 c2288: fire_B,N,N,H,W,4,C  = 0
 c2289: fire_B,N,N,H,W,4,I  = 0
 c2290: fire_B,N,N,H,W,4,A  = 0
 c2291: fire_B,N,N,H,W,4,L  = 0
 c2292: fire_B,N,N,H,W,4,N  = 0
 c2293: fire_B,N,N,H,W,5,P  = 0
 c2294: fire_B,N,N,H,W,5,D  = 0
 c2295: - fire_B,N,N,H,W,5,P - fire_B,N,N,H,W,5,D - fire_B,N,N,H,W,5,C
        - fire_B,N,N,H,W,5,I - fire_B,N,N,H,W,5,A - fire_B,N,N,H,W,5,L
        - fire_B,N,N,H,W,5,N  = 0
 c2296: fire_B,N,N,H,W,5,C  = 0
 c2297: fire_B,N,N,H,W,5,I  = 0
 c2298: fire_B,N,N,H,W,5,A  = 0
 c2299: fire_B,N,N,H,W,5,L  = 0
 c2300: fire_B,N,N,H,W,5,N  = 0
 c2301: fire_B,N,N,H,C,1,P  = 0
 c2302: fire_B,N,N,H,C,1,D  = 0
 c2303: fire_B,N,N,H,C,1,W  = 0
 c2304: - fire_B,N,N,H,C,1,P - fire_B,N,N,H,C,1,D - fire_B,N,N,H,C,1,W
        - fire_B,N,N,H,C,1,I - fire_B,N,N,H,C,1,A - fire_B,N,N,H,C,1,L
        - fire_B,N,N,H,C,1,N  = 0
 c2305: fire_B,N,N,H,C,1,I  = 0
 c2306: fire_B,N,N,H,C,1,A  = 0
 c2307: fire_B,N,N,H,C,1,L  = 0
 c2308: fire_B,N,N,H,C,1,N  = 0
 c2309: fire_B,N,N,H,C,2,P  = 0
 c2310: fire_B,N,N,H,C,2,D  = 0
 c2311: fire_B,N,N,H,C,2,W  = 0
 c2312: - fire_B,N,N,H,C,2,P - fire_B,N,N,H,C,2,D - fire_B,N,N,H,C,2,W
        - fire_B,N,N,H,C,2,I - fire_B,N,N,H,C,2,A - fire_B,N,N,H,C,2,L
        - fire_B,N,N,H,C,2,N  = 0
 c2313: fire_B,N,N,H,C,2,I  = 0
 c2314: fire_B,N,N,H,C,2,A  = 0
 c2315: fire_B,N,N,H,C,2,L  = 0
 c2316: fire_B,N,N,H,C,2,N  = 0
 c2317: fire_B,N,N,H,C,3,P  = 0
 c2318: fire_B,N,N,H,C,3,D  = 0
 c2319: fire_B,N,N,H,C,3,W  = 0
 c2320: - fire_B,N,N,H,C,3,P - fire_B,N,N,H,C,3,D - fire_B,N,N,H,C,3,W
        - fire_B,N,N,H,C,3,I - fire_B,N,N,H,C,3,A - fire_B,N,N,H,C,3,L
        - fire_B,N,N,H,C,3,N  = 0
 c2321: fire_B,N,N,H,C,3,I  = 0
 c2322: fire_B,N,N,H,C,3,A  = 0
 c2323: fire_B,N,N,H,C,3,L  = 0
 c2324: fire_B,N,N,H,C,3,N  = 0
 c2325: fire_B,N,N,H,C,4,P  = 0
 c2326: fire_B,N,N,H,C,4,D  = 0
 c2327: fire_B,N,N,H,C,4,W  = 0
 c2328: - fire_B,N,N,H,C,4,P - fire_B,N,N,H,C,4,D - fire_B,N,N,H,C,4,W
        - fire_B,N,N,H,C,4,I - fire_B,N,N,H,C,4,A - fire_B,N,N,H,C,4,L
        - fire_B,N,N,H,C,4,N  = 0
 c2329: fire_B,N,N,H,C,4,I  = 0
 c2330: fire_B,N,N,H,C,4,A  = 0
 c2331: fire_B,N,N,H,C,4,L  = 0
 c2332: fire_B,N,N,H,C,4,N  = 0
 c2333: fire_B,N,N,H,C,5,P  = 0
 c2334: fire_B,N,N,H,C,5,D  = 0
 c2335: fire_B,N,N,H,C,5,W  = 0
 c2336: - fire_B,N,N,H,C,5,P - fire_B,N,N,H,C,5,D - fire_B,N,N,H,C,5,W
        - fire_B,N,N,H,C,5,I - fire_B,N,N,H,C,5,A - fire_B,N,N,H,C,5,L
        - fire_B,N,N,H,C,5,N  = 0
 c2337: fire_B,N,N,H,C,5,I  = 0
 c2338: fire_B,N,N,H,C,5,A  = 0
 c2339: fire_B,N,N,H,C,5,L  = 0
 c2340: fire_B,N,N,H,C,5,N  = 0
 c2341: fire_B,N,N,H,I,1,P  = 0
 c2342: fire_B,N,N,H,I,1,D  = 0
 c2343: fire_B,N,N,H,I,1,W  = 0
 c2344: fire_B,N,N,H,I,1,C  = 0
 c2345: - fire_B,N,N,H,I,1,P - fire_B,N,N,H,I,1,D - fire_B,N,N,H,I,1,W
        - fire_B,N,N,H,I,1,C - fire_B,N,N,H,I,1,A - fire_B,N,N,H,I,1,L
        - fire_B,N,N,H,I,1,N  = 0
 c2346: fire_B,N,N,H,I,1,A  = 0
 c2347: fire_B,N,N,H,I,1,L  = 0
 c2348: fire_B,N,N,H,I,1,N  = 0
 c2349: fire_B,N,N,H,I,2,P  = 0
 c2350: fire_B,N,N,H,I,2,D  = 0
 c2351: fire_B,N,N,H,I,2,W  = 0
 c2352: fire_B,N,N,H,I,2,C  = 0
 c2353: - fire_B,N,N,H,I,2,P - fire_B,N,N,H,I,2,D - fire_B,N,N,H,I,2,W
        - fire_B,N,N,H,I,2,C - fire_B,N,N,H,I,2,A - fire_B,N,N,H,I,2,L
        - fire_B,N,N,H,I,2,N  = 0
 c2354: fire_B,N,N,H,I,2,A  = 0
 c2355: fire_B,N,N,H,I,2,L  = 0
 c2356: fire_B,N,N,H,I,2,N  = 0
 c2357: fire_B,N,N,H,I,3,P  = 0
 c2358: fire_B,N,N,H,I,3,D  = 0
 c2359: fire_B,N,N,H,I,3,W  = 0
 c2360: fire_B,N,N,H,I,3,C  = 0
 c2361: - fire_B,N,N,H,I,3,P - fire_B,N,N,H,I,3,D - fire_B,N,N,H,I,3,W
        - fire_B,N,N,H,I,3,C - fire_B,N,N,H,I,3,A - fire_B,N,N,H,I,3,L
        - fire_B,N,N,H,I,3,N  = 0
 c2362: fire_B,N,N,H,I,3,A  = 0
 c2363: fire_B,N,N,H,I,3,L  = 0
 c2364: fire_B,N,N,H,I,3,N  = 0
 c2365: fire_B,N,N,H,I,4,P  = 0
 c2366: fire_B,N,N,H,I,4,D  = 0
 c2367: fire_B,N,N,H,I,4,W  = 0
 c2368: fire_B,N,N,H,I,4,C  = 0
 c2369: - fire_B,N,N,H,I,4,P - fire_B,N,N,H,I,4,D - fire_B,N,N,H,I,4,W
        - fire_B,N,N,H,I,4,C - fire_B,N,N,H,I,4,A - fire_B,N,N,H,I,4,L
        - fire_B,N,N,H,I,4,N  = 0
 c2370: fire_B,N,N,H,I,4,A  = 0
 c2371: fire_B,N,N,H,I,4,L  = 0
 c2372: fire_B,N,N,H,I,4,N  = 0
 c2373: fire_B,N,N,H,I,5,P  = 0
 c2374: fire_B,N,N,H,I,5,D  = 0
 c2375: fire_B,N,N,H,I,5,W  = 0
 c2376: fire_B,N,N,H,I,5,C  = 0
 c2377: - fire_B,N,N,H,I,5,P - fire_B,N,N,H,I,5,D - fire_B,N,N,H,I,5,W
        - fire_B,N,N,H,I,5,C - fire_B,N,N,H,I,5,A - fire_B,N,N,H,I,5,L
        - fire_B,N,N,H,I,5,N  = 0
 c2378: fire_B,N,N,H,I,5,A  = 0
 c2379: fire_B,N,N,H,I,5,L  = 0
 c2380: fire_B,N,N,H,I,5,N  = 0
 c2381: fire_B,N,N,H,A,1,P  = 0
 c2382: fire_B,N,N,H,A,1,D  = 0
 c2383: fire_B,N,N,H,A,1,W  = 0
 c2384: fire_B,N,N,H,A,1,C  = 0
 c2385: fire_B,N,N,H,A,1,I  = 0
 c2386: - fire_B,N,N,H,A,1,P - fire_B,N,N,H,A,1,D - fire_B,N,N,H,A,1,W
        - fire_B,N,N,H,A,1,C - fire_B,N,N,H,A,1,I - fire_B,N,N,H,A,1,L
        - fire_B,N,N,H,A,1,N  = 0
 c2387: fire_B,N,N,H,A,1,L  = 0
 c2388: fire_B,N,N,H,A,1,N  = 0
 c2389: fire_B,N,N,H,A,2,P  = 0
 c2390: fire_B,N,N,H,A,2,D  = 0
 c2391: fire_B,N,N,H,A,2,W  = 0
 c2392: fire_B,N,N,H,A,2,C  = 0
 c2393: fire_B,N,N,H,A,2,I  = 0
 c2394: - fire_B,N,N,H,A,2,P - fire_B,N,N,H,A,2,D - fire_B,N,N,H,A,2,W
        - fire_B,N,N,H,A,2,C - fire_B,N,N,H,A,2,I - fire_B,N,N,H,A,2,L
        - fire_B,N,N,H,A,2,N  = 0
 c2395: fire_B,N,N,H,A,2,L  = 0
 c2396: fire_B,N,N,H,A,2,N  = 0
 c2397: fire_B,N,N,H,A,3,P  = 0
 c2398: fire_B,N,N,H,A,3,D  = 0
 c2399: fire_B,N,N,H,A,3,W  = 0
 c2400: fire_B,N,N,H,A,3,C  = 0
 c2401: fire_B,N,N,H,A,3,I  = 0
 c2402: - fire_B,N,N,H,A,3,P - fire_B,N,N,H,A,3,D - fire_B,N,N,H,A,3,W
        - fire_B,N,N,H,A,3,C - fire_B,N,N,H,A,3,I - fire_B,N,N,H,A,3,L
        - fire_B,N,N,H,A,3,N  = 0
 c2403: fire_B,N,N,H,A,3,L  = 0
 c2404: fire_B,N,N,H,A,3,N  = 0
 c2405: fire_B,N,N,H,A,4,P  = 0
 c2406: fire_B,N,N,H,A,4,D  = 0
 c2407: fire_B,N,N,H,A,4,W  = 0
 c2408: fire_B,N,N,H,A,4,C  = 0
 c2409: fire_B,N,N,H,A,4,I  = 0
 c2410: - fire_B,N,N,H,A,4,P - fire_B,N,N,H,A,4,D - fire_B,N,N,H,A,4,W
        - fire_B,N,N,H,A,4,C - fire_B,N,N,H,A,4,I - fire_B,N,N,H,A,4,L
        - fire_B,N,N,H,A,4,N  = 0
 c2411: fire_B,N,N,H,A,4,L  = 0
 c2412: fire_B,N,N,H,A,4,N  = 0
 c2413: fire_B,N,N,H,A,5,P  = 0
 c2414: fire_B,N,N,H,A,5,D  = 0
 c2415: fire_B,N,N,H,A,5,W  = 0
 c2416: fire_B,N,N,H,A,5,C  = 0
 c2417: fire_B,N,N,H,A,5,I  = 0
 c2418: - fire_B,N,N,H,A,5,P - fire_B,N,N,H,A,5,D - fire_B,N,N,H,A,5,W
        - fire_B,N,N,H,A,5,C - fire_B,N,N,H,A,5,I - fire_B,N,N,H,A,5,L
        - fire_B,N,N,H,A,5,N  = 0
 c2419: fire_B,N,N,H,A,5,L  = 0
 c2420: fire_B,N,N,H,A,5,N  = 0
 c2421: fire_B,N,N,H,L,1,P  = 0
 c2422: fire_B,N,N,H,L,1,D  = 0
 c2423: fire_B,N,N,H,L,1,W  = 0
 c2424: fire_B,N,N,H,L,1,C  = 0
 c2425: fire_B,N,N,H,L,1,I  = 0
 c2426: fire_B,N,N,H,L,1,A  = 0
 c2427: - fire_B,N,N,H,L,1,P - fire_B,N,N,H,L,1,D - fire_B,N,N,H,L,1,W
        - fire_B,N,N,H,L,1,C - fire_B,N,N,H,L,1,I - fire_B,N,N,H,L,1,A
        - fire_B,N,N,H,L,1,N  = 0
 c2428: fire_B,N,N,H,L,1,N  = 0
 c2429: fire_B,N,N,H,L,2,P  = 0
 c2430: fire_B,N,N,H,L,2,D  = 0
 c2431: fire_B,N,N,H,L,2,W  = 0
 c2432: fire_B,N,N,H,L,2,C  = 0
 c2433: fire_B,N,N,H,L,2,I  = 0
 c2434: fire_B,N,N,H,L,2,A  = 0
 c2435: - fire_B,N,N,H,L,2,P - fire_B,N,N,H,L,2,D - fire_B,N,N,H,L,2,W
        - fire_B,N,N,H,L,2,C - fire_B,N,N,H,L,2,I - fire_B,N,N,H,L,2,A
        - fire_B,N,N,H,L,2,N  = 0
 c2436: fire_B,N,N,H,L,2,N  = 0
 c2437: fire_B,N,N,H,L,3,P  = 0
 c2438: fire_B,N,N,H,L,3,D  = 0
 c2439: fire_B,N,N,H,L,3,W  = 0
 c2440: fire_B,N,N,H,L,3,C  = 0
 c2441: fire_B,N,N,H,L,3,I  = 0
 c2442: fire_B,N,N,H,L,3,A  = 0
 c2443: - fire_B,N,N,H,L,3,P - fire_B,N,N,H,L,3,D - fire_B,N,N,H,L,3,W
        - fire_B,N,N,H,L,3,C - fire_B,N,N,H,L,3,I - fire_B,N,N,H,L,3,A
        - fire_B,N,N,H,L,3,N  = 0
 c2444: fire_B,N,N,H,L,3,N  = 0
 c2445: fire_B,N,N,H,L,4,P  = 0
 c2446: fire_B,N,N,H,L,4,D  = 0
 c2447: fire_B,N,N,H,L,4,W  = 0
 c2448: fire_B,N,N,H,L,4,C  = 0
 c2449: fire_B,N,N,H,L,4,I  = 0
 c2450: fire_B,N,N,H,L,4,A  = 0
 c2451: - fire_B,N,N,H,L,4,P - fire_B,N,N,H,L,4,D - fire_B,N,N,H,L,4,W
        - fire_B,N,N,H,L,4,C - fire_B,N,N,H,L,4,I - fire_B,N,N,H,L,4,A
        - fire_B,N,N,H,L,4,N  = 0
 c2452: fire_B,N,N,H,L,4,N  = 0
 c2453: fire_B,N,N,H,L,5,P  = 0
 c2454: fire_B,N,N,H,L,5,D  = 0
 c2455: fire_B,N,N,H,L,5,W  = 0
 c2456: fire_B,N,N,H,L,5,C  = 0
 c2457: fire_B,N,N,H,L,5,I  = 0
 c2458: fire_B,N,N,H,L,5,A  = 0
 c2459: - fire_B,N,N,H,L,5,P - fire_B,N,N,H,L,5,D - fire_B,N,N,H,L,5,W
        - fire_B,N,N,H,L,5,C - fire_B,N,N,H,L,5,I - fire_B,N,N,H,L,5,A
        - fire_B,N,N,H,L,5,N  = 0
 c2460: fire_B,N,N,H,L,5,N  = 0
 c2461: fire_B,N,N,H,N,1,P  = 0
 c2462: fire_B,N,N,H,N,1,D  = 0
 c2463: fire_B,N,N,H,N,1,W  = 0
 c2464: fire_B,N,N,H,N,1,C  = 0
 c2465: fire_B,N,N,H,N,1,I  = 0
 c2466: fire_B,N,N,H,N,1,A  = 0
 c2467: fire_B,N,N,H,N,1,L  = 0
 c2468: - fire_B,N,N,H,N,1,P - fire_B,N,N,H,N,1,D - fire_B,N,N,H,N,1,W
        - fire_B,N,N,H,N,1,C - fire_B,N,N,H,N,1,I - fire_B,N,N,H,N,1,A
        - fire_B,N,N,H,N,1,L  = 0
 c2469: fire_B,N,N,H,N,2,P  = 0
 c2470: fire_B,N,N,H,N,2,D  = 0
 c2471: fire_B,N,N,H,N,2,W  = 0
 c2472: fire_B,N,N,H,N,2,C  = 0
 c2473: fire_B,N,N,H,N,2,I  = 0
 c2474: fire_B,N,N,H,N,2,A  = 0
 c2475: fire_B,N,N,H,N,2,L  = 0
 c2476: - fire_B,N,N,H,N,2,P - fire_B,N,N,H,N,2,D - fire_B,N,N,H,N,2,W
        - fire_B,N,N,H,N,2,C - fire_B,N,N,H,N,2,I - fire_B,N,N,H,N,2,A
        - fire_B,N,N,H,N,2,L  = 0
 c2477: fire_B,N,N,H,N,3,P  = 0
 c2478: fire_B,N,N,H,N,3,D  = 0
 c2479: fire_B,N,N,H,N,3,W  = 0
 c2480: fire_B,N,N,H,N,3,C  = 0
 c2481: fire_B,N,N,H,N,3,I  = 0
 c2482: fire_B,N,N,H,N,3,A  = 0
 c2483: fire_B,N,N,H,N,3,L  = 0
 c2484: - fire_B,N,N,H,N,3,P - fire_B,N,N,H,N,3,D - fire_B,N,N,H,N,3,W
        - fire_B,N,N,H,N,3,C - fire_B,N,N,H,N,3,I - fire_B,N,N,H,N,3,A
        - fire_B,N,N,H,N,3,L  = 0
 c2485: fire_B,N,N,H,N,4,P  = 0
 c2486: fire_B,N,N,H,N,4,D  = 0
 c2487: fire_B,N,N,H,N,4,W  = 0
 c2488: fire_B,N,N,H,N,4,C  = 0
 c2489: fire_B,N,N,H,N,4,I  = 0
 c2490: fire_B,N,N,H,N,4,A  = 0
 c2491: fire_B,N,N,H,N,4,L  = 0
 c2492: - fire_B,N,N,H,N,4,P - fire_B,N,N,H,N,4,D - fire_B,N,N,H,N,4,W
        - fire_B,N,N,H,N,4,C - fire_B,N,N,H,N,4,I - fire_B,N,N,H,N,4,A
        - fire_B,N,N,H,N,4,L  = 0
 c2493: fire_B,N,N,H,N,5,P  = 0
 c2494: fire_B,N,N,H,N,5,D  = 0
 c2495: fire_B,N,N,H,N,5,W  = 0
 c2496: fire_B,N,N,H,N,5,C  = 0
 c2497: fire_B,N,N,H,N,5,I  = 0
 c2498: fire_B,N,N,H,N,5,A  = 0
 c2499: fire_B,N,N,H,N,5,L  = 0
 c2500: - fire_B,N,N,H,N,5,P - fire_B,N,N,H,N,5,D - fire_B,N,N,H,N,5,W
        - fire_B,N,N,H,N,5,C - fire_B,N,N,H,N,5,I - fire_B,N,N,H,N,5,A
        - fire_B,N,N,H,N,5,L  = 0
 c2501: - fire_B,N,N,C,P,1,D - fire_B,N,N,C,P,1,W - fire_B,N,N,C,P,1,C
        - fire_B,N,N,C,P,1,I - fire_B,N,N,C,P,1,A - fire_B,N,N,C,P,1,L
        - fire_B,N,N,C,P,1,N  = 0
 c2502: fire_B,N,N,C,P,1,D  = 0
 c2503: fire_B,N,N,C,P,1,W  = 0
 c2504: fire_B,N,N,C,P,1,C  = 0
 c2505: fire_B,N,N,C,P,1,I  = 0
 c2506: fire_B,N,N,C,P,1,A  = 0
 c2507: fire_B,N,N,C,P,1,L  = 0
 c2508: fire_B,N,N,C,P,1,N  = 0
 c2509: - fire_B,N,N,C,P,2,D - fire_B,N,N,C,P,2,W - fire_B,N,N,C,P,2,C
        - fire_B,N,N,C,P,2,I - fire_B,N,N,C,P,2,A - fire_B,N,N,C,P,2,L
        - fire_B,N,N,C,P,2,N  = 0
 c2510: fire_B,N,N,C,P,2,D  = 0
 c2511: fire_B,N,N,C,P,2,W  = 0
 c2512: fire_B,N,N,C,P,2,C  = 0
 c2513: fire_B,N,N,C,P,2,I  = 0
 c2514: fire_B,N,N,C,P,2,A  = 0
 c2515: fire_B,N,N,C,P,2,L  = 0
 c2516: fire_B,N,N,C,P,2,N  = 0
 c2517: - fire_B,N,N,C,P,3,D - fire_B,N,N,C,P,3,W - fire_B,N,N,C,P,3,C
        - fire_B,N,N,C,P,3,I - fire_B,N,N,C,P,3,A - fire_B,N,N,C,P,3,L
        - fire_B,N,N,C,P,3,N  = 0
 c2518: fire_B,N,N,C,P,3,D  = 0
 c2519: fire_B,N,N,C,P,3,W  = 0
 c2520: fire_B,N,N,C,P,3,C  = 0
 c2521: fire_B,N,N,C,P,3,I  = 0
 c2522: fire_B,N,N,C,P,3,A  = 0
 c2523: fire_B,N,N,C,P,3,L  = 0
 c2524: fire_B,N,N,C,P,3,N  = 0
 c2525: - fire_B,N,N,C,P,4,D - fire_B,N,N,C,P,4,W - fire_B,N,N,C,P,4,C
        - fire_B,N,N,C,P,4,I - fire_B,N,N,C,P,4,A - fire_B,N,N,C,P,4,L
        - fire_B,N,N,C,P,4,N  = 0
 c2526: fire_B,N,N,C,P,4,D  = 0
 c2527: fire_B,N,N,C,P,4,W  = 0
 c2528: fire_B,N,N,C,P,4,C  = 0
 c2529: fire_B,N,N,C,P,4,I  = 0
 c2530: fire_B,N,N,C,P,4,A  = 0
 c2531: fire_B,N,N,C,P,4,L  = 0
 c2532: fire_B,N,N,C,P,4,N  = 0
 c2533: - fire_B,N,N,C,P,5,D - fire_B,N,N,C,P,5,W - fire_B,N,N,C,P,5,C
        - fire_B,N,N,C,P,5,I - fire_B,N,N,C,P,5,A - fire_B,N,N,C,P,5,L
        - fire_B,N,N,C,P,5,N  = 0
 c2534: fire_B,N,N,C,P,5,D  = 0
 c2535: fire_B,N,N,C,P,5,W  = 0
 c2536: fire_B,N,N,C,P,5,C  = 0
 c2537: fire_B,N,N,C,P,5,I  = 0
 c2538: fire_B,N,N,C,P,5,A  = 0
 c2539: fire_B,N,N,C,P,5,L  = 0
 c2540: fire_B,N,N,C,P,5,N  = 0
 c2541: fire_B,N,N,C,D,1,P  = 0
 c2542: - fire_B,N,N,C,D,1,P - fire_B,N,N,C,D,1,W - fire_B,N,N,C,D,1,C
        - fire_B,N,N,C,D,1,I - fire_B,N,N,C,D,1,A - fire_B,N,N,C,D,1,L
        - fire_B,N,N,C,D,1,N  = 0
 c2543: fire_B,N,N,C,D,1,W  = 0
 c2544: fire_B,N,N,C,D,1,C  = 0
 c2545: fire_B,N,N,C,D,1,I  = 0
 c2546: fire_B,N,N,C,D,1,A  = 0
 c2547: fire_B,N,N,C,D,1,L  = 0
 c2548: fire_B,N,N,C,D,1,N  = 0
 c2549: fire_B,N,N,C,D,2,P  = 0
 c2550: - fire_B,N,N,C,D,2,P - fire_B,N,N,C,D,2,W - fire_B,N,N,C,D,2,C
        - fire_B,N,N,C,D,2,I - fire_B,N,N,C,D,2,A - fire_B,N,N,C,D,2,L
        - fire_B,N,N,C,D,2,N  = 0
 c2551: fire_B,N,N,C,D,2,W  = 0
 c2552: fire_B,N,N,C,D,2,C  = 0
 c2553: fire_B,N,N,C,D,2,I  = 0
 c2554: fire_B,N,N,C,D,2,A  = 0
 c2555: fire_B,N,N,C,D,2,L  = 0
 c2556: fire_B,N,N,C,D,2,N  = 0
 c2557: fire_B,N,N,C,D,3,P  = 0
 c2558: - fire_B,N,N,C,D,3,P - fire_B,N,N,C,D,3,W - fire_B,N,N,C,D,3,C
        - fire_B,N,N,C,D,3,I - fire_B,N,N,C,D,3,A - fire_B,N,N,C,D,3,L
        - fire_B,N,N,C,D,3,N  = 0
 c2559: fire_B,N,N,C,D,3,W  = 0
 c2560: fire_B,N,N,C,D,3,C  = 0
 c2561: fire_B,N,N,C,D,3,I  = 0
 c2562: fire_B,N,N,C,D,3,A  = 0
 c2563: fire_B,N,N,C,D,3,L  = 0
 c2564: fire_B,N,N,C,D,3,N  = 0
 c2565: fire_B,N,N,C,D,4,P  = 0
 c2566: - fire_B,N,N,C,D,4,P - fire_B,N,N,C,D,4,W - fire_B,N,N,C,D,4,C
        - fire_B,N,N,C,D,4,I - fire_B,N,N,C,D,4,A - fire_B,N,N,C,D,4,L
        - fire_B,N,N,C,D,4,N  = 0
 c2567: fire_B,N,N,C,D,4,W  = 0
 c2568: fire_B,N,N,C,D,4,C  = 0
 c2569: fire_B,N,N,C,D,4,I  = 0
 c2570: fire_B,N,N,C,D,4,A  = 0
 c2571: fire_B,N,N,C,D,4,L  = 0
 c2572: fire_B,N,N,C,D,4,N  = 0
 c2573: fire_B,N,N,C,D,5,P  = 0
 c2574: - fire_B,N,N,C,D,5,P - fire_B,N,N,C,D,5,W - fire_B,N,N,C,D,5,C
        - fire_B,N,N,C,D,5,I - fire_B,N,N,C,D,5,A - fire_B,N,N,C,D,5,L
        - fire_B,N,N,C,D,5,N  = 0
 c2575: fire_B,N,N,C,D,5,W  = 0
 c2576: fire_B,N,N,C,D,5,C  = 0
 c2577: fire_B,N,N,C,D,5,I  = 0
 c2578: fire_B,N,N,C,D,5,A  = 0
 c2579: fire_B,N,N,C,D,5,L  = 0
 c2580: fire_B,N,N,C,D,5,N  = 0
 c2581: fire_B,N,N,C,W,1,P  = 0
 c2582: fire_B,N,N,C,W,1,D  = 0
 c2583: - fire_B,N,N,C,W,1,P - fire_B,N,N,C,W,1,D - fire_B,N,N,C,W,1,C
        - fire_B,N,N,C,W,1,I - fire_B,N,N,C,W,1,A - fire_B,N,N,C,W,1,L
        - fire_B,N,N,C,W,1,N  = 0
 c2584: fire_B,N,N,C,W,1,C  = 0
 c2585: fire_B,N,N,C,W,1,I  = 0
 c2586: fire_B,N,N,C,W,1,A  = 0
 c2587: fire_B,N,N,C,W,1,L  = 0
 c2588: fire_B,N,N,C,W,1,N  = 0
 c2589: fire_B,N,N,C,W,2,P  = 0
 c2590: fire_B,N,N,C,W,2,D  = 0
 c2591: - fire_B,N,N,C,W,2,P - fire_B,N,N,C,W,2,D - fire_B,N,N,C,W,2,C
        - fire_B,N,N,C,W,2,I - fire_B,N,N,C,W,2,A - fire_B,N,N,C,W,2,L
        - fire_B,N,N,C,W,2,N  = 0
 c2592: fire_B,N,N,C,W,2,C  = 0
 c2593: fire_B,N,N,C,W,2,I  = 0
 c2594: fire_B,N,N,C,W,2,A  = 0
 c2595: fire_B,N,N,C,W,2,L  = 0
 c2596: fire_B,N,N,C,W,2,N  = 0
 c2597: fire_B,N,N,C,W,3,P  = 0
 c2598: fire_B,N,N,C,W,3,D  = 0
 c2599: - fire_B,N,N,C,W,3,P - fire_B,N,N,C,W,3,D - fire_B,N,N,C,W,3,C
        - fire_B,N,N,C,W,3,I - fire_B,N,N,C,W,3,A - fire_B,N,N,C,W,3,L
        - fire_B,N,N,C,W,3,N  = 0
 c2600: fire_B,N,N,C,W,3,C  = 0
 c2601: fire_B,N,N,C,W,3,I  = 0
 c2602: fire_B,N,N,C,W,3,A  = 0
 c2603: fire_B,N,N,C,W,3,L  = 0
 c2604: fire_B,N,N,C,W,3,N  = 0
 c2605: fire_B,N,N,C,W,4,P  = 0
 c2606: fire_B,N,N,C,W,4,D  = 0
 c2607: - fire_B,N,N,C,W,4,P - fire_B,N,N,C,W,4,D - fire_B,N,N,C,W,4,C
        - fire_B,N,N,C,W,4,I - fire_B,N,N,C,W,4,A - fire_B,N,N,C,W,4,L
        - fire_B,N,N,C,W,4,N  = 0
 c2608: fire_B,N,N,C,W,4,C  = 0
 c2609: fire_B,N,N,C,W,4,I  = 0
 c2610: fire_B,N,N,C,W,4,A  = 0
 c2611: fire_B,N,N,C,W,4,L  = 0
 c2612: fire_B,N,N,C,W,4,N  = 0
 c2613: fire_B,N,N,C,W,5,P  = 0
 c2614: fire_B,N,N,C,W,5,D  = 0
 c2615: - fire_B,N,N,C,W,5,P - fire_B,N,N,C,W,5,D - fire_B,N,N,C,W,5,C
        - fire_B,N,N,C,W,5,I - fire_B,N,N,C,W,5,A - fire_B,N,N,C,W,5,L
        - fire_B,N,N,C,W,5,N  = 0
 c2616: fire_B,N,N,C,W,5,C  = 0
 c2617: fire_B,N,N,C,W,5,I  = 0
 c2618: fire_B,N,N,C,W,5,A  = 0
 c2619: fire_B,N,N,C,W,5,L  = 0
 c2620: fire_B,N,N,C,W,5,N  = 0
 c2621: fire_B,N,N,C,C,1,P  = 0
 c2622: fire_B,N,N,C,C,1,D  = 0
 c2623: fire_B,N,N,C,C,1,W  = 0
 c2624: - fire_B,N,N,C,C,1,P - fire_B,N,N,C,C,1,D - fire_B,N,N,C,C,1,W
        - fire_B,N,N,C,C,1,I - fire_B,N,N,C,C,1,A - fire_B,N,N,C,C,1,L
        - fire_B,N,N,C,C,1,N  = 0
 c2625: fire_B,N,N,C,C,1,I  = 0
 c2626: fire_B,N,N,C,C,1,A  = 0
 c2627: fire_B,N,N,C,C,1,L  = 0
 c2628: fire_B,N,N,C,C,1,N  = 0
 c2629: fire_B,N,N,C,C,2,P  = 0
 c2630: fire_B,N,N,C,C,2,D  = 0
 c2631: fire_B,N,N,C,C,2,W  = 0
 c2632: - fire_B,N,N,C,C,2,P - fire_B,N,N,C,C,2,D - fire_B,N,N,C,C,2,W
        - fire_B,N,N,C,C,2,I - fire_B,N,N,C,C,2,A - fire_B,N,N,C,C,2,L
        - fire_B,N,N,C,C,2,N  = 0
 c2633: fire_B,N,N,C,C,2,I  = 0
 c2634: fire_B,N,N,C,C,2,A  = 0
 c2635: fire_B,N,N,C,C,2,L  = 0
 c2636: fire_B,N,N,C,C,2,N  = 0
 c2637: fire_B,N,N,C,C,3,P  = 0
 c2638: fire_B,N,N,C,C,3,D  = 0
 c2639: fire_B,N,N,C,C,3,W  = 0
 c2640: - fire_B,N,N,C,C,3,P - fire_B,N,N,C,C,3,D - fire_B,N,N,C,C,3,W
        - fire_B,N,N,C,C,3,I - fire_B,N,N,C,C,3,A - fire_B,N,N,C,C,3,L
        - fire_B,N,N,C,C,3,N  = 0
 c2641: fire_B,N,N,C,C,3,I  = 0
 c2642: fire_B,N,N,C,C,3,A  = 0
 c2643: fire_B,N,N,C,C,3,L  = 0
 c2644: fire_B,N,N,C,C,3,N  = 0
 c2645: fire_B,N,N,C,C,4,P  = 0
 c2646: fire_B,N,N,C,C,4,D  = 0
 c2647: fire_B,N,N,C,C,4,W  = 0
 c2648: - fire_B,N,N,C,C,4,P - fire_B,N,N,C,C,4,D - fire_B,N,N,C,C,4,W
        - fire_B,N,N,C,C,4,I - fire_B,N,N,C,C,4,A - fire_B,N,N,C,C,4,L
        - fire_B,N,N,C,C,4,N  = 0
 c2649: fire_B,N,N,C,C,4,I  = 0
 c2650: fire_B,N,N,C,C,4,A  = 0
 c2651: fire_B,N,N,C,C,4,L  = 0
 c2652: fire_B,N,N,C,C,4,N  = 0
 c2653: fire_B,N,N,C,C,5,P  = 0
 c2654: fire_B,N,N,C,C,5,D  = 0
 c2655: fire_B,N,N,C,C,5,W  = 0
 c2656: - fire_B,N,N,C,C,5,P - fire_B,N,N,C,C,5,D - fire_B,N,N,C,C,5,W
        - fire_B,N,N,C,C,5,I - fire_B,N,N,C,C,5,A - fire_B,N,N,C,C,5,L
        - fire_B,N,N,C,C,5,N  = 0
 c2657: fire_B,N,N,C,C,5,I  = 0
 c2658: fire_B,N,N,C,C,5,A  = 0
 c2659: fire_B,N,N,C,C,5,L  = 0
 c2660: fire_B,N,N,C,C,5,N  = 0
 c2661: fire_B,N,N,C,I,1,P  = 0
 c2662: fire_B,N,N,C,I,1,D  = 0
 c2663: fire_B,N,N,C,I,1,W  = 0
 c2664: fire_B,N,N,C,I,1,C  = 0
 c2665: - fire_B,N,N,C,I,1,P - fire_B,N,N,C,I,1,D - fire_B,N,N,C,I,1,W
        - fire_B,N,N,C,I,1,C - fire_B,N,N,C,I,1,A - fire_B,N,N,C,I,1,L
        - fire_B,N,N,C,I,1,N  = 0
 c2666: fire_B,N,N,C,I,1,A  = 0
 c2667: fire_B,N,N,C,I,1,L  = 0
 c2668: fire_B,N,N,C,I,1,N  = 0
 c2669: fire_B,N,N,C,I,2,P  = 0
 c2670: fire_B,N,N,C,I,2,D  = 0
 c2671: fire_B,N,N,C,I,2,W  = 0
 c2672: fire_B,N,N,C,I,2,C  = 0
 c2673: - fire_B,N,N,C,I,2,P - fire_B,N,N,C,I,2,D - fire_B,N,N,C,I,2,W
        - fire_B,N,N,C,I,2,C - fire_B,N,N,C,I,2,A - fire_B,N,N,C,I,2,L
        - fire_B,N,N,C,I,2,N  = 0
 c2674: fire_B,N,N,C,I,2,A  = 0
 c2675: fire_B,N,N,C,I,2,L  = 0
 c2676: fire_B,N,N,C,I,2,N  = 0
 c2677: fire_B,N,N,C,I,3,P  = 0
 c2678: fire_B,N,N,C,I,3,D  = 0
 c2679: fire_B,N,N,C,I,3,W  = 0
 c2680: fire_B,N,N,C,I,3,C  = 0
 c2681: - fire_B,N,N,C,I,3,P - fire_B,N,N,C,I,3,D - fire_B,N,N,C,I,3,W
        - fire_B,N,N,C,I,3,C - fire_B,N,N,C,I,3,A - fire_B,N,N,C,I,3,L
        - fire_B,N,N,C,I,3,N  = 0
 c2682: fire_B,N,N,C,I,3,A  = 0
 c2683: fire_B,N,N,C,I,3,L  = 0
 c2684: fire_B,N,N,C,I,3,N  = 0
 c2685: fire_B,N,N,C,I,4,P  = 0
 c2686: fire_B,N,N,C,I,4,D  = 0
 c2687: fire_B,N,N,C,I,4,W  = 0
 c2688: fire_B,N,N,C,I,4,C  = 0
 c2689: - fire_B,N,N,C,I,4,P - fire_B,N,N,C,I,4,D - fire_B,N,N,C,I,4,W
        - fire_B,N,N,C,I,4,C - fire_B,N,N,C,I,4,A - fire_B,N,N,C,I,4,L
        - fire_B,N,N,C,I,4,N  = 0
 c2690: fire_B,N,N,C,I,4,A  = 0
 c2691: fire_B,N,N,C,I,4,L  = 0
 c2692: fire_B,N,N,C,I,4,N  = 0
 c2693: fire_B,N,N,C,I,5,P  = 0
 c2694: fire_B,N,N,C,I,5,D  = 0
 c2695: fire_B,N,N,C,I,5,W  = 0
 c2696: fire_B,N,N,C,I,5,C  = 0
 c2697: - fire_B,N,N,C,I,5,P - fire_B,N,N,C,I,5,D - fire_B,N,N,C,I,5,W
        - fire_B,N,N,C,I,5,C - fire_B,N,N,C,I,5,A - fire_B,N,N,C,I,5,L
        - fire_B,N,N,C,I,5,N  = 0
 c2698: fire_B,N,N,C,I,5,A  = 0
 c2699: fire_B,N,N,C,I,5,L  = 0
 c2700: fire_B,N,N,C,I,5,N  = 0
 c2701: fire_B,N,N,C,A,1,P  = 0
 c2702: fire_B,N,N,C,A,1,D  = 0
 c2703: fire_B,N,N,C,A,1,W  = 0
 c2704: fire_B,N,N,C,A,1,C  = 0
 c2705: fire_B,N,N,C,A,1,I  = 0
 c2706: - fire_B,N,N,C,A,1,P - fire_B,N,N,C,A,1,D - fire_B,N,N,C,A,1,W
        - fire_B,N,N,C,A,1,C - fire_B,N,N,C,A,1,I - fire_B,N,N,C,A,1,L
        - fire_B,N,N,C,A,1,N  = 0
 c2707: fire_B,N,N,C,A,1,L  = 0
 c2708: fire_B,N,N,C,A,1,N  = 0
 c2709: fire_B,N,N,C,A,2,P  = 0
 c2710: fire_B,N,N,C,A,2,D  = 0
 c2711: fire_B,N,N,C,A,2,W  = 0
 c2712: fire_B,N,N,C,A,2,C  = 0
 c2713: fire_B,N,N,C,A,2,I  = 0
 c2714: - fire_B,N,N,C,A,2,P - fire_B,N,N,C,A,2,D - fire_B,N,N,C,A,2,W
        - fire_B,N,N,C,A,2,C - fire_B,N,N,C,A,2,I - fire_B,N,N,C,A,2,L
        - fire_B,N,N,C,A,2,N  = 0
 c2715: fire_B,N,N,C,A,2,L  = 0
 c2716: fire_B,N,N,C,A,2,N  = 0
 c2717: fire_B,N,N,C,A,3,P  = 0
 c2718: fire_B,N,N,C,A,3,D  = 0
 c2719: fire_B,N,N,C,A,3,W  = 0
 c2720: fire_B,N,N,C,A,3,C  = 0
 c2721: fire_B,N,N,C,A,3,I  = 0
 c2722: - fire_B,N,N,C,A,3,P - fire_B,N,N,C,A,3,D - fire_B,N,N,C,A,3,W
        - fire_B,N,N,C,A,3,C - fire_B,N,N,C,A,3,I - fire_B,N,N,C,A,3,L
        - fire_B,N,N,C,A,3,N  = 0
 c2723: fire_B,N,N,C,A,3,L  = 0
 c2724: fire_B,N,N,C,A,3,N  = 0
 c2725: fire_B,N,N,C,A,4,P  = 0
 c2726: fire_B,N,N,C,A,4,D  = 0
 c2727: fire_B,N,N,C,A,4,W  = 0
 c2728: fire_B,N,N,C,A,4,C  = 0
 c2729: fire_B,N,N,C,A,4,I  = 0
 c2730: - fire_B,N,N,C,A,4,P - fire_B,N,N,C,A,4,D - fire_B,N,N,C,A,4,W
        - fire_B,N,N,C,A,4,C - fire_B,N,N,C,A,4,I - fire_B,N,N,C,A,4,L
        - fire_B,N,N,C,A,4,N  = 0
 c2731: fire_B,N,N,C,A,4,L  = 0
 c2732: fire_B,N,N,C,A,4,N  = 0
 c2733: fire_B,N,N,C,A,5,P  = 0
 c2734: fire_B,N,N,C,A,5,D  = 0
 c2735: fire_B,N,N,C,A,5,W  = 0
 c2736: fire_B,N,N,C,A,5,C  = 0
 c2737: fire_B,N,N,C,A,5,I  = 0
 c2738: - fire_B,N,N,C,A,5,P - fire_B,N,N,C,A,5,D - fire_B,N,N,C,A,5,W
        - fire_B,N,N,C,A,5,C - fire_B,N,N,C,A,5,I - fire_B,N,N,C,A,5,L
        - fire_B,N,N,C,A,5,N  = 0
 c2739: fire_B,N,N,C,A,5,L  = 0
 c2740: fire_B,N,N,C,A,5,N  = 0
 c2741: fire_B,N,N,C,L,1,P  = 0
 c2742: fire_B,N,N,C,L,1,D  = 0
 c2743: fire_B,N,N,C,L,1,W  = 0
 c2744: fire_B,N,N,C,L,1,C  = 0
 c2745: fire_B,N,N,C,L,1,I  = 0
 c2746: fire_B,N,N,C,L,1,A  = 0
 c2747: - fire_B,N,N,C,L,1,P - fire_B,N,N,C,L,1,D - fire_B,N,N,C,L,1,W
        - fire_B,N,N,C,L,1,C - fire_B,N,N,C,L,1,I - fire_B,N,N,C,L,1,A
        - fire_B,N,N,C,L,1,N  = 0
 c2748: fire_B,N,N,C,L,1,N  = 0
 c2749: fire_B,N,N,C,L,2,P  = 0
 c2750: fire_B,N,N,C,L,2,D  = 0
 c2751: fire_B,N,N,C,L,2,W  = 0
 c2752: fire_B,N,N,C,L,2,C  = 0
 c2753: fire_B,N,N,C,L,2,I  = 0
 c2754: fire_B,N,N,C,L,2,A  = 0
 c2755: - fire_B,N,N,C,L,2,P - fire_B,N,N,C,L,2,D - fire_B,N,N,C,L,2,W
        - fire_B,N,N,C,L,2,C - fire_B,N,N,C,L,2,I - fire_B,N,N,C,L,2,A
        - fire_B,N,N,C,L,2,N  = 0
 c2756: fire_B,N,N,C,L,2,N  = 0
 c2757: fire_B,N,N,C,L,3,P  = 0
 c2758: fire_B,N,N,C,L,3,D  = 0
 c2759: fire_B,N,N,C,L,3,W  = 0
 c2760: fire_B,N,N,C,L,3,C  = 0
 c2761: fire_B,N,N,C,L,3,I  = 0
 c2762: fire_B,N,N,C,L,3,A  = 0
 c2763: - fire_B,N,N,C,L,3,P - fire_B,N,N,C,L,3,D - fire_B,N,N,C,L,3,W
        - fire_B,N,N,C,L,3,C - fire_B,N,N,C,L,3,I - fire_B,N,N,C,L,3,A
        - fire_B,N,N,C,L,3,N  = 0
 c2764: fire_B,N,N,C,L,3,N  = 0
 c2765: fire_B,N,N,C,L,4,P  = 0
 c2766: fire_B,N,N,C,L,4,D  = 0
 c2767: fire_B,N,N,C,L,4,W  = 0
 c2768: fire_B,N,N,C,L,4,C  = 0
 c2769: fire_B,N,N,C,L,4,I  = 0
 c2770: fire_B,N,N,C,L,4,A  = 0
 c2771: - fire_B,N,N,C,L,4,P - fire_B,N,N,C,L,4,D - fire_B,N,N,C,L,4,W
        - fire_B,N,N,C,L,4,C - fire_B,N,N,C,L,4,I - fire_B,N,N,C,L,4,A
        - fire_B,N,N,C,L,4,N  = 0
 c2772: fire_B,N,N,C,L,4,N  = 0
 c2773: fire_B,N,N,C,L,5,P  = 0
 c2774: fire_B,N,N,C,L,5,D  = 0
 c2775: fire_B,N,N,C,L,5,W  = 0
 c2776: fire_B,N,N,C,L,5,C  = 0
 c2777: fire_B,N,N,C,L,5,I  = 0
 c2778: fire_B,N,N,C,L,5,A  = 0
 c2779: - fire_B,N,N,C,L,5,P - fire_B,N,N,C,L,5,D - fire_B,N,N,C,L,5,W
        - fire_B,N,N,C,L,5,C - fire_B,N,N,C,L,5,I - fire_B,N,N,C,L,5,A
        - fire_B,N,N,C,L,5,N  = 0
 c2780: fire_B,N,N,C,L,5,N  = 0
 c2781: fire_B,N,N,C,N,1,P  = 0
 c2782: fire_B,N,N,C,N,1,D  = 0
 c2783: fire_B,N,N,C,N,1,W  = 0
 c2784: fire_B,N,N,C,N,1,C  = 0
 c2785: fire_B,N,N,C,N,1,I  = 0
 c2786: fire_B,N,N,C,N,1,A  = 0
 c2787: fire_B,N,N,C,N,1,L  = 0
 c2788: - fire_B,N,N,C,N,1,P - fire_B,N,N,C,N,1,D - fire_B,N,N,C,N,1,W
        - fire_B,N,N,C,N,1,C - fire_B,N,N,C,N,1,I - fire_B,N,N,C,N,1,A
        - fire_B,N,N,C,N,1,L  = 0
 c2789: fire_B,N,N,C,N,2,P  = 0
 c2790: fire_B,N,N,C,N,2,D  = 0
 c2791: fire_B,N,N,C,N,2,W  = 0
 c2792: fire_B,N,N,C,N,2,C  = 0
 c2793: fire_B,N,N,C,N,2,I  = 0
 c2794: fire_B,N,N,C,N,2,A  = 0
 c2795: fire_B,N,N,C,N,2,L  = 0
 c2796: - fire_B,N,N,C,N,2,P - fire_B,N,N,C,N,2,D - fire_B,N,N,C,N,2,W
        - fire_B,N,N,C,N,2,C - fire_B,N,N,C,N,2,I - fire_B,N,N,C,N,2,A
        - fire_B,N,N,C,N,2,L  = 0
 c2797: fire_B,N,N,C,N,3,P  = 0
 c2798: fire_B,N,N,C,N,3,D  = 0
 c2799: fire_B,N,N,C,N,3,W  = 0
 c2800: fire_B,N,N,C,N,3,C  = 0
 c2801: fire_B,N,N,C,N,3,I  = 0
 c2802: fire_B,N,N,C,N,3,A  = 0
 c2803: fire_B,N,N,C,N,3,L  = 0
 c2804: - fire_B,N,N,C,N,3,P - fire_B,N,N,C,N,3,D - fire_B,N,N,C,N,3,W
        - fire_B,N,N,C,N,3,C - fire_B,N,N,C,N,3,I - fire_B,N,N,C,N,3,A
        - fire_B,N,N,C,N,3,L  = 0
 c2805: fire_B,N,N,C,N,4,P  = 0
 c2806: fire_B,N,N,C,N,4,D  = 0
 c2807: fire_B,N,N,C,N,4,W  = 0
 c2808: fire_B,N,N,C,N,4,C  = 0
 c2809: fire_B,N,N,C,N,4,I  = 0
 c2810: fire_B,N,N,C,N,4,A  = 0
 c2811: fire_B,N,N,C,N,4,L  = 0
 c2812: - fire_B,N,N,C,N,4,P - fire_B,N,N,C,N,4,D - fire_B,N,N,C,N,4,W
        - fire_B,N,N,C,N,4,C - fire_B,N,N,C,N,4,I - fire_B,N,N,C,N,4,A
        - fire_B,N,N,C,N,4,L  = 0
 c2813: fire_B,N,N,C,N,5,P  = 0
 c2814: fire_B,N,N,C,N,5,D  = 0
 c2815: fire_B,N,N,C,N,5,W  = 0
 c2816: fire_B,N,N,C,N,5,C  = 0
 c2817: fire_B,N,N,C,N,5,I  = 0
 c2818: fire_B,N,N,C,N,5,A  = 0
 c2819: fire_B,N,N,C,N,5,L  = 0
 c2820: - fire_B,N,N,C,N,5,P - fire_B,N,N,C,N,5,D - fire_B,N,N,C,N,5,W
        - fire_B,N,N,C,N,5,C - fire_B,N,N,C,N,5,I - fire_B,N,N,C,N,5,A
        - fire_B,N,N,C,N,5,L  = 0
 c2821: - xEAr_B,N,N,L,P,2,1 + fire_B,N,N,L,P,1,P  = 0
 c2822: - xEAr_B,N,N,L,D,2,1 + fire_B,N,N,L,D,1,D  = 0
 c2823: - xEAr_B,N,N,L,W,2,1 + fire_B,N,N,L,W,1,W  = 0
 c2824: - xEAr_B,N,N,L,C,2,1 + fire_B,N,N,L,C,1,C  = 0
 c2825: - xEAr_B,N,N,L,I,2,1 + fire_B,N,N,L,I,1,I  = 0
 c2826: - xEAr_B,N,N,L,A,2,1 + fire_B,N,N,L,A,1,A  = 0
 c2827: - xEAr_B,N,N,L,L,2,1 + fire_B,N,N,L,L,1,L  = 0
 c2828: - xEAr_B,N,N,L,N,2,1 + fire_B,N,N,L,N,1,N  = 0
 c2829: - xEAr_B,N,N,L,P,3,1 + fire_B,N,N,L,P,2,P  = 0
 c2830: - xEAr_B,N,N,L,D,3,1 + fire_B,N,N,L,D,2,D  = 0
 c2831: - xEAr_B,N,N,L,W,3,1 + fire_B,N,N,L,W,2,W  = 0
 c2832: - xEAr_B,N,N,L,C,3,1 + fire_B,N,N,L,C,2,C  = 0
 c2833: - xEAr_B,N,N,L,I,3,1 + fire_B,N,N,L,I,2,I  = 0
 c2834: - xEAr_B,N,N,L,A,3,1 + fire_B,N,N,L,A,2,A  = 0
 c2835: - xEAr_B,N,N,L,L,3,1 + fire_B,N,N,L,L,2,L  = 0
 c2836: - xEAr_B,N,N,L,N,3,1 + fire_B,N,N,L,N,2,N  = 0
 c2837: - xEAr_B,N,N,L,P,4,1 + fire_B,N,N,L,P,3,P  = 0
 c2838: - xEAr_B,N,N,L,D,4,1 + fire_B,N,N,L,D,3,D  = 0
 c2839: - xEAr_B,N,N,L,W,4,1 + fire_B,N,N,L,W,3,W  = 0
 c2840: - xEAr_B,N,N,L,C,4,1 + fire_B,N,N,L,C,3,C  = 0
 c2841: - xEAr_B,N,N,L,I,4,1 + fire_B,N,N,L,I,3,I  = 0
 c2842: - xEAr_B,N,N,L,A,4,1 + fire_B,N,N,L,A,3,A  = 0
 c2843: - xEAr_B,N,N,L,L,4,1 + fire_B,N,N,L,L,3,L  = 0
 c2844: - xEAr_B,N,N,L,N,4,1 + fire_B,N,N,L,N,3,N  = 0
 c2845: - xEAr_B,N,N,L,P,5,1 + fire_B,N,N,L,P,4,P  = 0
 c2846: - xEAr_B,N,N,L,D,5,1 + fire_B,N,N,L,D,4,D  = 0
 c2847: - xEAr_B,N,N,L,W,5,1 + fire_B,N,N,L,W,4,W  = 0
 c2848: - xEAr_B,N,N,L,C,5,1 + fire_B,N,N,L,C,4,C  = 0
 c2849: - xEAr_B,N,N,L,I,5,1 + fire_B,N,N,L,I,4,I  = 0
 c2850: - xEAr_B,N,N,L,A,5,1 + fire_B,N,N,L,A,4,A  = 0
 c2851: - xEAr_B,N,N,L,L,5,1 + fire_B,N,N,L,L,4,L  = 0
 c2852: - xEAr_B,N,N,L,N,5,1 + fire_B,N,N,L,N,4,N  = 0
 c2853: - xEAr_B,N,N,H,P,2,1 + fire_B,N,N,H,P,1,P  = 0
 c2854: - xEAr_B,N,N,H,D,2,1 + fire_B,N,N,H,D,1,D  = 0
 c2855: - xEAr_B,N,N,H,W,2,1 + fire_B,N,N,H,W,1,W  = 0
 c2856: - xEAr_B,N,N,H,C,2,1 + fire_B,N,N,H,C,1,C  = 0
 c2857: - xEAr_B,N,N,H,I,2,1 + fire_B,N,N,H,I,1,I  = 0
 c2858: - xEAr_B,N,N,H,A,2,1 + fire_B,N,N,H,A,1,A  = 0
 c2859: - xEAr_B,N,N,H,L,2,1 + fire_B,N,N,H,L,1,L  = 0
 c2860: - xEAr_B,N,N,H,N,2,1 + fire_B,N,N,H,N,1,N  = 0
 c2861: - xEAr_B,N,N,H,P,3,1 + fire_B,N,N,H,P,2,P  = 0
 c2862: - xEAr_B,N,N,H,D,3,1 + fire_B,N,N,H,D,2,D  = 0
 c2863: - xEAr_B,N,N,H,W,3,1 + fire_B,N,N,H,W,2,W  = 0
 c2864: - xEAr_B,N,N,H,C,3,1 + fire_B,N,N,H,C,2,C  = 0
 c2865: - xEAr_B,N,N,H,I,3,1 + fire_B,N,N,H,I,2,I  = 0
 c2866: - xEAr_B,N,N,H,A,3,1 + fire_B,N,N,H,A,2,A  = 0
 c2867: - xEAr_B,N,N,H,L,3,1 + fire_B,N,N,H,L,2,L  = 0
 c2868: - xEAr_B,N,N,H,N,3,1 + fire_B,N,N,H,N,2,N  = 0
 c2869: - xEAr_B,N,N,H,P,4,1 + fire_B,N,N,H,P,3,P  = 0
 c2870: - xEAr_B,N,N,H,D,4,1 + fire_B,N,N,H,D,3,D  = 0
 c2871: - xEAr_B,N,N,H,W,4,1 + fire_B,N,N,H,W,3,W  = 0
 c2872: - xEAr_B,N,N,H,C,4,1 + fire_B,N,N,H,C,3,C  = 0
 c2873: - xEAr_B,N,N,H,I,4,1 + fire_B,N,N,H,I,3,I  = 0
 c2874: - xEAr_B,N,N,H,A,4,1 + fire_B,N,N,H,A,3,A  = 0
 c2875: - xEAr_B,N,N,H,L,4,1 + fire_B,N,N,H,L,3,L  = 0
 c2876: - xEAr_B,N,N,H,N,4,1 + fire_B,N,N,H,N,3,N  = 0
 c2877: - xEAr_B,N,N,H,P,5,1 + fire_B,N,N,H,P,4,P  = 0
 c2878: - xEAr_B,N,N,H,D,5,1 + fire_B,N,N,H,D,4,D  = 0
 c2879: - xEAr_B,N,N,H,W,5,1 + fire_B,N,N,H,W,4,W  = 0
 c2880: - xEAr_B,N,N,H,C,5,1 + fire_B,N,N,H,C,4,C  = 0
 c2881: - xEAr_B,N,N,H,I,5,1 + fire_B,N,N,H,I,4,I  = 0
 c2882: - xEAr_B,N,N,H,A,5,1 + fire_B,N,N,H,A,4,A  = 0
 c2883: - xEAr_B,N,N,H,L,5,1 + fire_B,N,N,H,L,4,L  = 0
 c2884: - xEAr_B,N,N,H,N,5,1 + fire_B,N,N,H,N,4,N  = 0
 c2885: - xEAr_B,N,N,C,P,2,1 + fire_B,N,N,C,P,1,P  = 0
 c2886: - xEAr_B,N,N,C,D,2,1 + fire_B,N,N,C,D,1,D  = 0
 c2887: - xEAr_B,N,N,C,W,2,1 + fire_B,N,N,C,W,1,W  = 0
 c2888: - xEAr_B,N,N,C,C,2,1 + fire_B,N,N,C,C,1,C  = 0
 c2889: - xEAr_B,N,N,C,I,2,1 + fire_B,N,N,C,I,1,I  = 0
 c2890: - xEAr_B,N,N,C,A,2,1 + fire_B,N,N,C,A,1,A  = 0
 c2891: - xEAr_B,N,N,C,L,2,1 + fire_B,N,N,C,L,1,L  = 0
 c2892: - xEAr_B,N,N,C,N,2,1 + fire_B,N,N,C,N,1,N  = 0
 c2893: - xEAr_B,N,N,C,P,3,1 + fire_B,N,N,C,P,2,P  = 0
 c2894: - xEAr_B,N,N,C,D,3,1 + fire_B,N,N,C,D,2,D  = 0
 c2895: - xEAr_B,N,N,C,W,3,1 + fire_B,N,N,C,W,2,W  = 0
 c2896: - xEAr_B,N,N,C,C,3,1 + fire_B,N,N,C,C,2,C  = 0
 c2897: - xEAr_B,N,N,C,I,3,1 + fire_B,N,N,C,I,2,I  = 0
 c2898: - xEAr_B,N,N,C,A,3,1 + fire_B,N,N,C,A,2,A  = 0
 c2899: - xEAr_B,N,N,C,L,3,1 + fire_B,N,N,C,L,2,L  = 0
 c2900: - xEAr_B,N,N,C,N,3,1 + fire_B,N,N,C,N,2,N  = 0
 c2901: - xEAr_B,N,N,C,P,4,1 + fire_B,N,N,C,P,3,P  = 0
 c2902: - xEAr_B,N,N,C,D,4,1 + fire_B,N,N,C,D,3,D  = 0
 c2903: - xEAr_B,N,N,C,W,4,1 + fire_B,N,N,C,W,3,W  = 0
 c2904: - xEAr_B,N,N,C,C,4,1 + fire_B,N,N,C,C,3,C  = 0
 c2905: - xEAr_B,N,N,C,I,4,1 + fire_B,N,N,C,I,3,I  = 0
 c2906: - xEAr_B,N,N,C,A,4,1 + fire_B,N,N,C,A,3,A  = 0
 c2907: - xEAr_B,N,N,C,L,4,1 + fire_B,N,N,C,L,3,L  = 0
 c2908: - xEAr_B,N,N,C,N,4,1 + fire_B,N,N,C,N,3,N  = 0
 c2909: - xEAr_B,N,N,C,P,5,1 + fire_B,N,N,C,P,4,P  = 0
 c2910: - xEAr_B,N,N,C,D,5,1 + fire_B,N,N,C,D,4,D  = 0
 c2911: - xEAr_B,N,N,C,W,5,1 + fire_B,N,N,C,W,4,W  = 0
 c2912: - xEAr_B,N,N,C,C,5,1 + fire_B,N,N,C,C,4,C  = 0
 c2913: - xEAr_B,N,N,C,I,5,1 + fire_B,N,N,C,I,4,I  = 0
 c2914: - xEAr_B,N,N,C,A,5,1 + fire_B,N,N,C,A,4,A  = 0
 c2915: - xEAr_B,N,N,C,L,5,1 + fire_B,N,N,C,L,4,L  = 0
 c2916: - xEAr_B,N,N,C,N,5,1 + fire_B,N,N,C,N,4,N  = 0
 c2917: 0.998 xEAr_B,N,N,L,P,2,1 - xEAr_B,N,N,L,P,3,2  = 0
 c2918: 0.998 xEAr_B,N,N,L,P,3,1 - xEAr_B,N,N,L,P,4,2  = 0
 c2919: 0.998 xEAr_B,N,N,L,P,3,2 - xEAr_B,N,N,L,P,4,3  = 0
 c2920: 0.998 xEAr_B,N,N,L,P,4,1 - xEAr_B,N,N,L,P,5,2  = 0
 c2921: 0.998 xEAr_B,N,N,L,P,4,2 - xEAr_B,N,N,L,P,5,3  = 0
 c2922: 0.998 xEAr_B,N,N,L,P,4,3 - xEAr_B,N,N,L,P,5,4  = 0
 c2923: 0.998 xEAr_B,N,N,L,D,2,1 - xEAr_B,N,N,L,D,3,2  = 0
 c2924: 0.998 xEAr_B,N,N,L,D,3,1 - xEAr_B,N,N,L,D,4,2  = 0
 c2925: 0.998 xEAr_B,N,N,L,D,3,2 - xEAr_B,N,N,L,D,4,3  = 0
 c2926: 0.998 xEAr_B,N,N,L,D,4,1 - xEAr_B,N,N,L,D,5,2  = 0
 c2927: 0.998 xEAr_B,N,N,L,D,4,2 - xEAr_B,N,N,L,D,5,3  = 0
 c2928: 0.998 xEAr_B,N,N,L,D,4,3 - xEAr_B,N,N,L,D,5,4  = 0
 c2929: 0.998 xEAr_B,N,N,L,W,2,1 - xEAr_B,N,N,L,W,3,2  = 0
 c2930: 0.998 xEAr_B,N,N,L,W,3,1 - xEAr_B,N,N,L,W,4,2  = 0
 c2931: 0.998 xEAr_B,N,N,L,W,3,2 - xEAr_B,N,N,L,W,4,3  = 0
 c2932: 0.998 xEAr_B,N,N,L,W,4,1 - xEAr_B,N,N,L,W,5,2  = 0
 c2933: 0.998 xEAr_B,N,N,L,W,4,2 - xEAr_B,N,N,L,W,5,3  = 0
 c2934: 0.998 xEAr_B,N,N,L,W,4,3 - xEAr_B,N,N,L,W,5,4  = 0
 c2935: 0.998 xEAr_B,N,N,L,C,2,1 - xEAr_B,N,N,L,C,3,2  = 0
 c2936: 0.998 xEAr_B,N,N,L,C,3,1 - xEAr_B,N,N,L,C,4,2  = 0
 c2937: 0.998 xEAr_B,N,N,L,C,3,2 - xEAr_B,N,N,L,C,4,3  = 0
 c2938: 0.998 xEAr_B,N,N,L,C,4,1 - xEAr_B,N,N,L,C,5,2  = 0
 c2939: 0.998 xEAr_B,N,N,L,C,4,2 - xEAr_B,N,N,L,C,5,3  = 0
 c2940: 0.998 xEAr_B,N,N,L,C,4,3 - xEAr_B,N,N,L,C,5,4  = 0
 c2941: 0.998 xEAr_B,N,N,L,I,2,1 - xEAr_B,N,N,L,I,3,2  = 0
 c2942: 0.998 xEAr_B,N,N,L,I,3,1 - xEAr_B,N,N,L,I,4,2  = 0
 c2943: 0.998 xEAr_B,N,N,L,I,3,2 - xEAr_B,N,N,L,I,4,3  = 0
 c2944: 0.998 xEAr_B,N,N,L,I,4,1 - xEAr_B,N,N,L,I,5,2  = 0
 c2945: 0.998 xEAr_B,N,N,L,I,4,2 - xEAr_B,N,N,L,I,5,3  = 0
 c2946: 0.998 xEAr_B,N,N,L,I,4,3 - xEAr_B,N,N,L,I,5,4  = 0
 c2947: 0.998 xEAr_B,N,N,L,A,2,1 - xEAr_B,N,N,L,A,3,2  = 0
 c2948: 0.998 xEAr_B,N,N,L,A,3,1 - xEAr_B,N,N,L,A,4,2  = 0
 c2949: 0.998 xEAr_B,N,N,L,A,3,2 - xEAr_B,N,N,L,A,4,3  = 0
 c2950: 0.998 xEAr_B,N,N,L,A,4,1 - xEAr_B,N,N,L,A,5,2  = 0
 c2951: 0.998 xEAr_B,N,N,L,A,4,2 - xEAr_B,N,N,L,A,5,3  = 0
 c2952: 0.998 xEAr_B,N,N,L,A,4,3 - xEAr_B,N,N,L,A,5,4  = 0
 c2953: 0.998 xEAr_B,N,N,L,L,2,1 - xEAr_B,N,N,L,L,3,2  = 0
 c2954: 0.998 xEAr_B,N,N,L,L,3,1 - xEAr_B,N,N,L,L,4,2  = 0
 c2955: 0.998 xEAr_B,N,N,L,L,3,2 - xEAr_B,N,N,L,L,4,3  = 0
 c2956: 0.998 xEAr_B,N,N,L,L,4,1 - xEAr_B,N,N,L,L,5,2  = 0
 c2957: 0.998 xEAr_B,N,N,L,L,4,2 - xEAr_B,N,N,L,L,5,3  = 0
 c2958: 0.998 xEAr_B,N,N,L,L,4,3 - xEAr_B,N,N,L,L,5,4  = 0
 c2959: xEAr_B,N,N,L,N,2,1 - xEAr_B,N,N,L,N,3,2  = 0
 c2960: xEAr_B,N,N,L,N,3,1 - xEAr_B,N,N,L,N,4,2  = 0
 c2961: xEAr_B,N,N,L,N,3,2 - xEAr_B,N,N,L,N,4,3  = 0
 c2962: xEAr_B,N,N,L,N,4,1 - xEAr_B,N,N,L,N,5,2  = 0
 c2963: xEAr_B,N,N,L,N,4,2 - xEAr_B,N,N,L,N,5,3  = 0
 c2964: xEAr_B,N,N,L,N,4,3 - xEAr_B,N,N,L,N,5,4  = 0
 c2965: 0.998 xEAr_B,N,N,H,P,2,1 - xEAr_B,N,N,H,P,3,2  = 0
 c2966: 0.998 xEAr_B,N,N,H,P,3,1 - xEAr_B,N,N,H,P,4,2  = 0
 c2967: 0.998 xEAr_B,N,N,H,P,3,2 - xEAr_B,N,N,H,P,4,3  = 0
 c2968: 0.998 xEAr_B,N,N,H,P,4,1 - xEAr_B,N,N,H,P,5,2  = 0
 c2969: 0.998 xEAr_B,N,N,H,P,4,2 - xEAr_B,N,N,H,P,5,3  = 0
 c2970: 0.998 xEAr_B,N,N,H,P,4,3 - xEAr_B,N,N,H,P,5,4  = 0
 c2971: 0.998 xEAr_B,N,N,H,D,2,1 - xEAr_B,N,N,H,D,3,2  = 0
 c2972: 0.998 xEAr_B,N,N,H,D,3,1 - xEAr_B,N,N,H,D,4,2  = 0
 c2973: 0.998 xEAr_B,N,N,H,D,3,2 - xEAr_B,N,N,H,D,4,3  = 0
 c2974: 0.998 xEAr_B,N,N,H,D,4,1 - xEAr_B,N,N,H,D,5,2  = 0
 c2975: 0.998 xEAr_B,N,N,H,D,4,2 - xEAr_B,N,N,H,D,5,3  = 0
 c2976: 0.998 xEAr_B,N,N,H,D,4,3 - xEAr_B,N,N,H,D,5,4  = 0
 c2977: 0.998 xEAr_B,N,N,H,W,2,1 - xEAr_B,N,N,H,W,3,2  = 0
 c2978: 0.998 xEAr_B,N,N,H,W,3,1 - xEAr_B,N,N,H,W,4,2  = 0
 c2979: 0.998 xEAr_B,N,N,H,W,3,2 - xEAr_B,N,N,H,W,4,3  = 0
 c2980: 0.998 xEAr_B,N,N,H,W,4,1 - xEAr_B,N,N,H,W,5,2  = 0
 c2981: 0.998 xEAr_B,N,N,H,W,4,2 - xEAr_B,N,N,H,W,5,3  = 0
 c2982: 0.998 xEAr_B,N,N,H,W,4,3 - xEAr_B,N,N,H,W,5,4  = 0
 c2983: 0.998 xEAr_B,N,N,H,C,2,1 - xEAr_B,N,N,H,C,3,2  = 0
 c2984: 0.998 xEAr_B,N,N,H,C,3,1 - xEAr_B,N,N,H,C,4,2  = 0
 c2985: 0.998 xEAr_B,N,N,H,C,3,2 - xEAr_B,N,N,H,C,4,3  = 0
 c2986: 0.998 xEAr_B,N,N,H,C,4,1 - xEAr_B,N,N,H,C,5,2  = 0
 c2987: 0.998 xEAr_B,N,N,H,C,4,2 - xEAr_B,N,N,H,C,5,3  = 0
 c2988: 0.998 xEAr_B,N,N,H,C,4,3 - xEAr_B,N,N,H,C,5,4  = 0
 c2989: 0.998 xEAr_B,N,N,H,I,2,1 - xEAr_B,N,N,H,I,3,2  = 0
 c2990: 0.998 xEAr_B,N,N,H,I,3,1 - xEAr_B,N,N,H,I,4,2  = 0
 c2991: 0.998 xEAr_B,N,N,H,I,3,2 - xEAr_B,N,N,H,I,4,3  = 0
 c2992: 0.998 xEAr_B,N,N,H,I,4,1 - xEAr_B,N,N,H,I,5,2  = 0
 c2993: 0.998 xEAr_B,N,N,H,I,4,2 - xEAr_B,N,N,H,I,5,3  = 0
 c2994: 0.998 xEAr_B,N,N,H,I,4,3 - xEAr_B,N,N,H,I,5,4  = 0
 c2995: 0.998 xEAr_B,N,N,H,A,2,1 - xEAr_B,N,N,H,A,3,2  = 0
 c2996: 0.998 xEAr_B,N,N,H,A,3,1 - xEAr_B,N,N,H,A,4,2  = 0
 c2997: 0.998 xEAr_B,N,N,H,A,3,2 - xEAr_B,N,N,H,A,4,3  = 0
 c2998: 0.998 xEAr_B,N,N,H,A,4,1 - xEAr_B,N,N,H,A,5,2  = 0
 c2999: 0.998 xEAr_B,N,N,H,A,4,2 - xEAr_B,N,N,H,A,5,3  = 0
 c3000: 0.998 xEAr_B,N,N,H,A,4,3 - xEAr_B,N,N,H,A,5,4  = 0
 c3001: 0.998 xEAr_B,N,N,H,L,2,1 - xEAr_B,N,N,H,L,3,2  = 0
 c3002: 0.998 xEAr_B,N,N,H,L,3,1 - xEAr_B,N,N,H,L,4,2  = 0
 c3003: 0.998 xEAr_B,N,N,H,L,3,2 - xEAr_B,N,N,H,L,4,3  = 0
 c3004: 0.998 xEAr_B,N,N,H,L,4,1 - xEAr_B,N,N,H,L,5,2  = 0
 c3005: 0.998 xEAr_B,N,N,H,L,4,2 - xEAr_B,N,N,H,L,5,3  = 0
 c3006: 0.998 xEAr_B,N,N,H,L,4,3 - xEAr_B,N,N,H,L,5,4  = 0
 c3007: xEAr_B,N,N,H,N,2,1 - xEAr_B,N,N,H,N,3,2  = 0
 c3008: xEAr_B,N,N,H,N,3,1 - xEAr_B,N,N,H,N,4,2  = 0
 c3009: xEAr_B,N,N,H,N,3,2 - xEAr_B,N,N,H,N,4,3  = 0
 c3010: xEAr_B,N,N,H,N,4,1 - xEAr_B,N,N,H,N,5,2  = 0
 c3011: xEAr_B,N,N,H,N,4,2 - xEAr_B,N,N,H,N,5,3  = 0
 c3012: xEAr_B,N,N,H,N,4,3 - xEAr_B,N,N,H,N,5,4  = 0
 c3013: 0.998 xEAr_B,N,N,C,P,2,1 - xEAr_B,N,N,C,P,3,2  = 0
 c3014: 0.998 xEAr_B,N,N,C,P,3,1 - xEAr_B,N,N,C,P,4,2  = 0
 c3015: 0.998 xEAr_B,N,N,C,P,3,2 - xEAr_B,N,N,C,P,4,3  = 0
 c3016: 0.998 xEAr_B,N,N,C,P,4,1 - xEAr_B,N,N,C,P,5,2  = 0
 c3017: 0.998 xEAr_B,N,N,C,P,4,2 - xEAr_B,N,N,C,P,5,3  = 0
 c3018: 0.998 xEAr_B,N,N,C,P,4,3 - xEAr_B,N,N,C,P,5,4  = 0
 c3019: 0.998 xEAr_B,N,N,C,D,2,1 - xEAr_B,N,N,C,D,3,2  = 0
 c3020: 0.998 xEAr_B,N,N,C,D,3,1 - xEAr_B,N,N,C,D,4,2  = 0
 c3021: 0.998 xEAr_B,N,N,C,D,3,2 - xEAr_B,N,N,C,D,4,3  = 0
 c3022: 0.998 xEAr_B,N,N,C,D,4,1 - xEAr_B,N,N,C,D,5,2  = 0
 c3023: 0.998 xEAr_B,N,N,C,D,4,2 - xEAr_B,N,N,C,D,5,3  = 0
 c3024: 0.998 xEAr_B,N,N,C,D,4,3 - xEAr_B,N,N,C,D,5,4  = 0
 c3025: 0.998 xEAr_B,N,N,C,W,2,1 - xEAr_B,N,N,C,W,3,2  = 0
 c3026: 0.998 xEAr_B,N,N,C,W,3,1 - xEAr_B,N,N,C,W,4,2  = 0
 c3027: 0.998 xEAr_B,N,N,C,W,3,2 - xEAr_B,N,N,C,W,4,3  = 0
 c3028: 0.998 xEAr_B,N,N,C,W,4,1 - xEAr_B,N,N,C,W,5,2  = 0
 c3029: 0.998 xEAr_B,N,N,C,W,4,2 - xEAr_B,N,N,C,W,5,3  = 0
 c3030: 0.998 xEAr_B,N,N,C,W,4,3 - xEAr_B,N,N,C,W,5,4  = 0
 c3031: 0.998 xEAr_B,N,N,C,C,2,1 - xEAr_B,N,N,C,C,3,2  = 0
 c3032: 0.998 xEAr_B,N,N,C,C,3,1 - xEAr_B,N,N,C,C,4,2  = 0
 c3033: 0.998 xEAr_B,N,N,C,C,3,2 - xEAr_B,N,N,C,C,4,3  = 0
 c3034: 0.998 xEAr_B,N,N,C,C,4,1 - xEAr_B,N,N,C,C,5,2  = 0
 c3035: 0.998 xEAr_B,N,N,C,C,4,2 - xEAr_B,N,N,C,C,5,3  = 0
 c3036: 0.998 xEAr_B,N,N,C,C,4,3 - xEAr_B,N,N,C,C,5,4  = 0
 c3037: 0.998 xEAr_B,N,N,C,I,2,1 - xEAr_B,N,N,C,I,3,2  = 0
 c3038: 0.998 xEAr_B,N,N,C,I,3,1 - xEAr_B,N,N,C,I,4,2  = 0
 c3039: 0.998 xEAr_B,N,N,C,I,3,2 - xEAr_B,N,N,C,I,4,3  = 0
 c3040: 0.998 xEAr_B,N,N,C,I,4,1 - xEAr_B,N,N,C,I,5,2  = 0
 c3041: 0.998 xEAr_B,N,N,C,I,4,2 - xEAr_B,N,N,C,I,5,3  = 0
 c3042: 0.998 xEAr_B,N,N,C,I,4,3 - xEAr_B,N,N,C,I,5,4  = 0
 c3043: 0.998 xEAr_B,N,N,C,A,2,1 - xEAr_B,N,N,C,A,3,2  = 0
 c3044: 0.998 xEAr_B,N,N,C,A,3,1 - xEAr_B,N,N,C,A,4,2  = 0
 c3045: 0.998 xEAr_B,N,N,C,A,3,2 - xEAr_B,N,N,C,A,4,3  = 0
 c3046: 0.998 xEAr_B,N,N,C,A,4,1 - xEAr_B,N,N,C,A,5,2  = 0
 c3047: 0.998 xEAr_B,N,N,C,A,4,2 - xEAr_B,N,N,C,A,5,3  = 0
 c3048: 0.998 xEAr_B,N,N,C,A,4,3 - xEAr_B,N,N,C,A,5,4  = 0
 c3049: 0.998 xEAr_B,N,N,C,L,2,1 - xEAr_B,N,N,C,L,3,2  = 0
 c3050: 0.998 xEAr_B,N,N,C,L,3,1 - xEAr_B,N,N,C,L,4,2  = 0
 c3051: 0.998 xEAr_B,N,N,C,L,3,2 - xEAr_B,N,N,C,L,4,3  = 0
 c3052: 0.998 xEAr_B,N,N,C,L,4,1 - xEAr_B,N,N,C,L,5,2  = 0
 c3053: 0.998 xEAr_B,N,N,C,L,4,2 - xEAr_B,N,N,C,L,5,3  = 0
 c3054: 0.998 xEAr_B,N,N,C,L,4,3 - xEAr_B,N,N,C,L,5,4  = 0
 c3055: xEAr_B,N,N,C,N,2,1 - xEAr_B,N,N,C,N,3,2  = 0
 c3056: xEAr_B,N,N,C,N,3,1 - xEAr_B,N,N,C,N,4,2  = 0
 c3057: xEAr_B,N,N,C,N,3,2 - xEAr_B,N,N,C,N,4,3  = 0
 c3058: xEAr_B,N,N,C,N,4,1 - xEAr_B,N,N,C,N,5,2  = 0
 c3059: xEAr_B,N,N,C,N,4,2 - xEAr_B,N,N,C,N,5,3  = 0
 c3060: xEAr_B,N,N,C,N,4,3 - xEAr_B,N,N,C,N,5,4  = 0
End
