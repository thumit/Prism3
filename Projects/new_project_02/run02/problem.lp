\ENCODING=ISO-8859-1
\Problem name: ilog.cplex

Minimize
 obj:
Subject To
 c1:   x_B,N,N,C,P,M,0 + x_B,N,N,C,P,M,1 + x_B,N,N,C,P,M,2 + x_B,N,N,C,P,M,3
       + x_B,N,N,C,P,M,4 + x_B,N,N,C,P,M,5  = 909
 c2:   x_B,N,N,C,P,L,0 + x_B,N,N,C,P,L,1 + x_B,N,N,C,P,L,2 + x_B,N,N,C,P,L,3
       + x_B,N,N,C,P,L,4 + x_B,N,N,C,P,L,5  = 1268
 c3:   x_B,N,N,C,D,L,0 + x_B,N,N,C,D,L,1 + x_B,N,N,C,D,L,2 + x_B,N,N,C,D,L,3
       + x_B,N,N,C,D,L,4 + x_B,N,N,C,D,L,5  = 1725
 c4:   x_B,N,N,C,C,P,0 + x_B,N,N,C,C,P,1 + x_B,N,N,C,C,P,2 + x_B,N,N,C,C,P,3
       + x_B,N,N,C,C,P,4 + x_B,N,N,C,C,P,5  = 651
 c5:   x_B,N,N,C,C,M,0 + x_B,N,N,C,C,M,1 + x_B,N,N,C,C,M,2 + x_B,N,N,C,C,M,3
       + x_B,N,N,C,C,M,4 + x_B,N,N,C,C,M,5  = 1353
 c6:   x_B,N,N,C,C,L,0 + x_B,N,N,C,C,L,1 + x_B,N,N,C,C,L,2 + x_B,N,N,C,C,L,3
       + x_B,N,N,C,C,L,4 + x_B,N,N,C,C,L,5  = 4121
 c7:   - x_B,N,N,C,C,L,0  = 0
 c8:   - x_B,N,N,C,C,L,0 + x_B,N,N,C,C,M,0  = 0
 c9:   - x_B,N,N,C,C,L,0 + x_B,N,N,C,C,P,0  = 0
 c10:  - x_B,N,N,C,C,L,0 + x_B,N,N,C,D,L,0  = 0
 c11:  - x_B,N,N,C,C,L,0 + x_B,N,N,C,P,L,0  = 0
 c12:  - x_B,N,N,C,C,L,0 + x_B,N,N,C,P,M,0  = 0
 c13:  x_B,N,N,C,C,L,1 - xPB_E_B,N,N,C,C,L,0,1 - xPB_E_B,N,N,C,C,L,1,1
       - xPB_E_B,N,N,C,C,L,2,1 - xPB_E_B,N,N,C,C,L,3,1 - xPB_E_B,N,N,C,C,L,4,1
        = 0
 c14:  x_B,N,N,C,C,M,1 - xPB_E_B,N,N,C,C,M,0,1 - xPB_E_B,N,N,C,C,M,1,1
       - xPB_E_B,N,N,C,C,M,2,1 - xPB_E_B,N,N,C,C,M,3,1 - xPB_E_B,N,N,C,C,M,4,1
        = 0
 c15:  x_B,N,N,C,C,P,1 - xPB_E_B,N,N,C,C,P,0,1 - xPB_E_B,N,N,C,C,P,1,1
       - xPB_E_B,N,N,C,C,P,2,1 - xPB_E_B,N,N,C,C,P,3,1 - xPB_E_B,N,N,C,C,P,4,1
        = 0
 c16:  x_B,N,N,C,D,L,1 - xPB_E_B,N,N,C,D,L,0,1 - xPB_E_B,N,N,C,D,L,1,1
       - xPB_E_B,N,N,C,D,L,2,1 - xPB_E_B,N,N,C,D,L,3,1 - xPB_E_B,N,N,C,D,L,4,1
        = 0
 c17:  x_B,N,N,C,P,L,1 - xPB_E_B,N,N,C,P,L,0,1 - xPB_E_B,N,N,C,P,L,1,1
       - xPB_E_B,N,N,C,P,L,2,1 - xPB_E_B,N,N,C,P,L,3,1 - xPB_E_B,N,N,C,P,L,4,1
        = 0
 c18:  x_B,N,N,C,P,M,1 - xPB_E_B,N,N,C,P,M,0,1 - xPB_E_B,N,N,C,P,M,1,1
       - xPB_E_B,N,N,C,P,M,2,1 - xPB_E_B,N,N,C,P,M,3,1 - xPB_E_B,N,N,C,P,M,4,1
        = 0
 c19:  0.998 xPB_E_B,N,N,C,C,L,0,1 - xPB_E_B,N,N,C,C,L,0,2  = 0
 c20:  0.998 xPB_E_B,N,N,C,C,L,0,2 - xPB_E_B,N,N,C,C,L,0,3  = 0
 c21:  0.998 xPB_E_B,N,N,C,C,L,0,3 - xPB_E_B,N,N,C,C,L,0,4  = 0
 c22:  0.998 xPB_E_B,N,N,C,C,L,0,4 - xPB_E_B,N,N,C,C,L,0,5  = 0
 c23:  0.998 xPB_E_B,N,N,C,C,L,1,1 - xPB_E_B,N,N,C,C,L,1,2  = 0
 c24:  0.998 xPB_E_B,N,N,C,C,L,1,2 - xPB_E_B,N,N,C,C,L,1,3  = 0
 c25:  0.998 xPB_E_B,N,N,C,C,L,1,3 - xPB_E_B,N,N,C,C,L,1,4  = 0
 c26:  0.998 xPB_E_B,N,N,C,C,L,1,4 - xPB_E_B,N,N,C,C,L,1,5  = 0
 c27:  0.998 xPB_E_B,N,N,C,C,L,2,1 - xPB_E_B,N,N,C,C,L,2,2  = 0
 c28:  0.998 xPB_E_B,N,N,C,C,L,2,2 - xPB_E_B,N,N,C,C,L,2,3  = 0
 c29:  0.998 xPB_E_B,N,N,C,C,L,2,3 - xPB_E_B,N,N,C,C,L,2,4  = 0
 c30:  0.998 xPB_E_B,N,N,C,C,L,2,4 - xPB_E_B,N,N,C,C,L,2,5  = 0
 c31:  0.998 xPB_E_B,N,N,C,C,L,3,1 - xPB_E_B,N,N,C,C,L,3,2  = 0
 c32:  0.998 xPB_E_B,N,N,C,C,L,3,2 - xPB_E_B,N,N,C,C,L,3,3  = 0
 c33:  0.998 xPB_E_B,N,N,C,C,L,3,3 - xPB_E_B,N,N,C,C,L,3,4  = 0
 c34:  0.998 xPB_E_B,N,N,C,C,L,3,4 - xPB_E_B,N,N,C,C,L,3,5  = 0
 c35:  0.998 xPB_E_B,N,N,C,C,L,4,1 - xPB_E_B,N,N,C,C,L,4,2  = 0
 c36:  0.998 xPB_E_B,N,N,C,C,L,4,2 - xPB_E_B,N,N,C,C,L,4,3  = 0
 c37:  0.998 xPB_E_B,N,N,C,C,L,4,3 - xPB_E_B,N,N,C,C,L,4,4  = 0
 c38:  0.998 xPB_E_B,N,N,C,C,L,4,4 - xPB_E_B,N,N,C,C,L,4,5  = 0
 c39:  0.998 xPB_E_B,N,N,C,C,M,0,1 - xPB_E_B,N,N,C,C,M,0,2  = 0
 c40:  0.998 xPB_E_B,N,N,C,C,M,0,2 - xPB_E_B,N,N,C,C,M,0,3  = 0
 c41:  0.998 xPB_E_B,N,N,C,C,M,0,3 - xPB_E_B,N,N,C,C,M,0,4  = 0
 c42:  0.998 xPB_E_B,N,N,C,C,M,0,4 - xPB_E_B,N,N,C,C,M,0,5  = 0
 c43:  0.998 xPB_E_B,N,N,C,C,M,1,1 - xPB_E_B,N,N,C,C,M,1,2  = 0
 c44:  0.998 xPB_E_B,N,N,C,C,M,1,2 - xPB_E_B,N,N,C,C,M,1,3  = 0
 c45:  0.998 xPB_E_B,N,N,C,C,M,1,3 - xPB_E_B,N,N,C,C,M,1,4  = 0
 c46:  0.998 xPB_E_B,N,N,C,C,M,1,4 - xPB_E_B,N,N,C,C,M,1,5  = 0
 c47:  0.998 xPB_E_B,N,N,C,C,M,2,1 - xPB_E_B,N,N,C,C,M,2,2  = 0
 c48:  0.998 xPB_E_B,N,N,C,C,M,2,2 - xPB_E_B,N,N,C,C,M,2,3  = 0
 c49:  0.998 xPB_E_B,N,N,C,C,M,2,3 - xPB_E_B,N,N,C,C,M,2,4  = 0
 c50:  0.998 xPB_E_B,N,N,C,C,M,2,4 - xPB_E_B,N,N,C,C,M,2,5  = 0
 c51:  0.998 xPB_E_B,N,N,C,C,M,3,1 - xPB_E_B,N,N,C,C,M,3,2  = 0
 c52:  0.998 xPB_E_B,N,N,C,C,M,3,2 - xPB_E_B,N,N,C,C,M,3,3  = 0
 c53:  0.998 xPB_E_B,N,N,C,C,M,3,3 - xPB_E_B,N,N,C,C,M,3,4  = 0
 c54:  0.998 xPB_E_B,N,N,C,C,M,3,4 - xPB_E_B,N,N,C,C,M,3,5  = 0
 c55:  0.998 xPB_E_B,N,N,C,C,M,4,1 - xPB_E_B,N,N,C,C,M,4,2  = 0
 c56:  0.998 xPB_E_B,N,N,C,C,M,4,2 - xPB_E_B,N,N,C,C,M,4,3  = 0
 c57:  0.998 xPB_E_B,N,N,C,C,M,4,3 - xPB_E_B,N,N,C,C,M,4,4  = 0
 c58:  0.998 xPB_E_B,N,N,C,C,M,4,4 - xPB_E_B,N,N,C,C,M,4,5  = 0
 c59:  0.998 xPB_E_B,N,N,C,C,P,0,1 - xPB_E_B,N,N,C,C,P,0,2  = 0
 c60:  0.998 xPB_E_B,N,N,C,C,P,0,2 - xPB_E_B,N,N,C,C,P,0,3  = 0
 c61:  0.998 xPB_E_B,N,N,C,C,P,0,3 - xPB_E_B,N,N,C,C,P,0,4  = 0
 c62:  0.998 xPB_E_B,N,N,C,C,P,0,4 - xPB_E_B,N,N,C,C,P,0,5  = 0
 c63:  0.998 xPB_E_B,N,N,C,C,P,1,1 - xPB_E_B,N,N,C,C,P,1,2  = 0
 c64:  0.998 xPB_E_B,N,N,C,C,P,1,2 - xPB_E_B,N,N,C,C,P,1,3  = 0
 c65:  0.998 xPB_E_B,N,N,C,C,P,1,3 - xPB_E_B,N,N,C,C,P,1,4  = 0
 c66:  0.998 xPB_E_B,N,N,C,C,P,1,4 - xPB_E_B,N,N,C,C,P,1,5  = 0
 c67:  0.998 xPB_E_B,N,N,C,C,P,2,1 - xPB_E_B,N,N,C,C,P,2,2  = 0
 c68:  0.998 xPB_E_B,N,N,C,C,P,2,2 - xPB_E_B,N,N,C,C,P,2,3  = 0
 c69:  0.998 xPB_E_B,N,N,C,C,P,2,3 - xPB_E_B,N,N,C,C,P,2,4  = 0
 c70:  0.998 xPB_E_B,N,N,C,C,P,2,4 - xPB_E_B,N,N,C,C,P,2,5  = 0
 c71:  0.998 xPB_E_B,N,N,C,C,P,3,1 - xPB_E_B,N,N,C,C,P,3,2  = 0
 c72:  0.998 xPB_E_B,N,N,C,C,P,3,2 - xPB_E_B,N,N,C,C,P,3,3  = 0
 c73:  0.998 xPB_E_B,N,N,C,C,P,3,3 - xPB_E_B,N,N,C,C,P,3,4  = 0
 c74:  0.998 xPB_E_B,N,N,C,C,P,3,4 - xPB_E_B,N,N,C,C,P,3,5  = 0
 c75:  0.998 xPB_E_B,N,N,C,C,P,4,1 - xPB_E_B,N,N,C,C,P,4,2  = 0
 c76:  0.998 xPB_E_B,N,N,C,C,P,4,2 - xPB_E_B,N,N,C,C,P,4,3  = 0
 c77:  0.998 xPB_E_B,N,N,C,C,P,4,3 - xPB_E_B,N,N,C,C,P,4,4  = 0
 c78:  0.998 xPB_E_B,N,N,C,C,P,4,4 - xPB_E_B,N,N,C,C,P,4,5  = 0
 c79:  0.998 xPB_E_B,N,N,C,D,L,0,1 - xPB_E_B,N,N,C,D,L,0,2  = 0
 c80:  0.998 xPB_E_B,N,N,C,D,L,0,2 - xPB_E_B,N,N,C,D,L,0,3  = 0
 c81:  0.998 xPB_E_B,N,N,C,D,L,0,3 - xPB_E_B,N,N,C,D,L,0,4  = 0
 c82:  0.998 xPB_E_B,N,N,C,D,L,0,4 - xPB_E_B,N,N,C,D,L,0,5  = 0
 c83:  0.998 xPB_E_B,N,N,C,D,L,1,1 - xPB_E_B,N,N,C,D,L,1,2  = 0
 c84:  0.998 xPB_E_B,N,N,C,D,L,1,2 - xPB_E_B,N,N,C,D,L,1,3  = 0
 c85:  0.998 xPB_E_B,N,N,C,D,L,1,3 - xPB_E_B,N,N,C,D,L,1,4  = 0
 c86:  0.998 xPB_E_B,N,N,C,D,L,1,4 - xPB_E_B,N,N,C,D,L,1,5  = 0
 c87:  0.998 xPB_E_B,N,N,C,D,L,2,1 - xPB_E_B,N,N,C,D,L,2,2  = 0
 c88:  0.998 xPB_E_B,N,N,C,D,L,2,2 - xPB_E_B,N,N,C,D,L,2,3  = 0
 c89:  0.998 xPB_E_B,N,N,C,D,L,2,3 - xPB_E_B,N,N,C,D,L,2,4  = 0
 c90:  0.998 xPB_E_B,N,N,C,D,L,2,4 - xPB_E_B,N,N,C,D,L,2,5  = 0
 c91:  0.998 xPB_E_B,N,N,C,D,L,3,1 - xPB_E_B,N,N,C,D,L,3,2  = 0
 c92:  0.998 xPB_E_B,N,N,C,D,L,3,2 - xPB_E_B,N,N,C,D,L,3,3  = 0
 c93:  0.998 xPB_E_B,N,N,C,D,L,3,3 - xPB_E_B,N,N,C,D,L,3,4  = 0
 c94:  0.998 xPB_E_B,N,N,C,D,L,3,4 - xPB_E_B,N,N,C,D,L,3,5  = 0
 c95:  0.998 xPB_E_B,N,N,C,D,L,4,1 - xPB_E_B,N,N,C,D,L,4,2  = 0
 c96:  0.998 xPB_E_B,N,N,C,D,L,4,2 - xPB_E_B,N,N,C,D,L,4,3  = 0
 c97:  0.998 xPB_E_B,N,N,C,D,L,4,3 - xPB_E_B,N,N,C,D,L,4,4  = 0
 c98:  0.998 xPB_E_B,N,N,C,D,L,4,4 - xPB_E_B,N,N,C,D,L,4,5  = 0
 c99:  0.998 xPB_E_B,N,N,C,P,L,0,1 - xPB_E_B,N,N,C,P,L,0,2  = 0
 c100: 0.998 xPB_E_B,N,N,C,P,L,0,2 - xPB_E_B,N,N,C,P,L,0,3  = 0
 c101: 0.998 xPB_E_B,N,N,C,P,L,0,3 - xPB_E_B,N,N,C,P,L,0,4  = 0
 c102: 0.998 xPB_E_B,N,N,C,P,L,0,4 - xPB_E_B,N,N,C,P,L,0,5  = 0
 c103: 0.998 xPB_E_B,N,N,C,P,L,1,1 - xPB_E_B,N,N,C,P,L,1,2  = 0
 c104: 0.998 xPB_E_B,N,N,C,P,L,1,2 - xPB_E_B,N,N,C,P,L,1,3  = 0
 c105: 0.998 xPB_E_B,N,N,C,P,L,1,3 - xPB_E_B,N,N,C,P,L,1,4  = 0
 c106: 0.998 xPB_E_B,N,N,C,P,L,1,4 - xPB_E_B,N,N,C,P,L,1,5  = 0
 c107: 0.998 xPB_E_B,N,N,C,P,L,2,1 - xPB_E_B,N,N,C,P,L,2,2  = 0
 c108: 0.998 xPB_E_B,N,N,C,P,L,2,2 - xPB_E_B,N,N,C,P,L,2,3  = 0
 c109: 0.998 xPB_E_B,N,N,C,P,L,2,3 - xPB_E_B,N,N,C,P,L,2,4  = 0
 c110: 0.998 xPB_E_B,N,N,C,P,L,2,4 - xPB_E_B,N,N,C,P,L,2,5  = 0
 c111: 0.998 xPB_E_B,N,N,C,P,L,3,1 - xPB_E_B,N,N,C,P,L,3,2  = 0
 c112: 0.998 xPB_E_B,N,N,C,P,L,3,2 - xPB_E_B,N,N,C,P,L,3,3  = 0
 c113: 0.998 xPB_E_B,N,N,C,P,L,3,3 - xPB_E_B,N,N,C,P,L,3,4  = 0
 c114: 0.998 xPB_E_B,N,N,C,P,L,3,4 - xPB_E_B,N,N,C,P,L,3,5  = 0
 c115: 0.998 xPB_E_B,N,N,C,P,L,4,1 - xPB_E_B,N,N,C,P,L,4,2  = 0
 c116: 0.998 xPB_E_B,N,N,C,P,L,4,2 - xPB_E_B,N,N,C,P,L,4,3  = 0
 c117: 0.998 xPB_E_B,N,N,C,P,L,4,3 - xPB_E_B,N,N,C,P,L,4,4  = 0
 c118: 0.998 xPB_E_B,N,N,C,P,L,4,4 - xPB_E_B,N,N,C,P,L,4,5  = 0
 c119: 0.998 xPB_E_B,N,N,C,P,M,0,1 - xPB_E_B,N,N,C,P,M,0,2  = 0
 c120: 0.998 xPB_E_B,N,N,C,P,M,0,2 - xPB_E_B,N,N,C,P,M,0,3  = 0
 c121: 0.998 xPB_E_B,N,N,C,P,M,0,3 - xPB_E_B,N,N,C,P,M,0,4  = 0
 c122: 0.998 xPB_E_B,N,N,C,P,M,0,4 - xPB_E_B,N,N,C,P,M,0,5  = 0
 c123: 0.998 xPB_E_B,N,N,C,P,M,1,1 - xPB_E_B,N,N,C,P,M,1,2  = 0
 c124: 0.998 xPB_E_B,N,N,C,P,M,1,2 - xPB_E_B,N,N,C,P,M,1,3  = 0
 c125: 0.998 xPB_E_B,N,N,C,P,M,1,3 - xPB_E_B,N,N,C,P,M,1,4  = 0
 c126: 0.998 xPB_E_B,N,N,C,P,M,1,4 - xPB_E_B,N,N,C,P,M,1,5  = 0
 c127: 0.998 xPB_E_B,N,N,C,P,M,2,1 - xPB_E_B,N,N,C,P,M,2,2  = 0
 c128: 0.998 xPB_E_B,N,N,C,P,M,2,2 - xPB_E_B,N,N,C,P,M,2,3  = 0
 c129: 0.998 xPB_E_B,N,N,C,P,M,2,3 - xPB_E_B,N,N,C,P,M,2,4  = 0
 c130: 0.998 xPB_E_B,N,N,C,P,M,2,4 - xPB_E_B,N,N,C,P,M,2,5  = 0
 c131: 0.998 xPB_E_B,N,N,C,P,M,3,1 - xPB_E_B,N,N,C,P,M,3,2  = 0
 c132: 0.998 xPB_E_B,N,N,C,P,M,3,2 - xPB_E_B,N,N,C,P,M,3,3  = 0
 c133: 0.998 xPB_E_B,N,N,C,P,M,3,3 - xPB_E_B,N,N,C,P,M,3,4  = 0
 c134: 0.998 xPB_E_B,N,N,C,P,M,3,4 - xPB_E_B,N,N,C,P,M,3,5  = 0
 c135: 0.998 xPB_E_B,N,N,C,P,M,4,1 - xPB_E_B,N,N,C,P,M,4,2  = 0
 c136: 0.998 xPB_E_B,N,N,C,P,M,4,2 - xPB_E_B,N,N,C,P,M,4,3  = 0
 c137: 0.998 xPB_E_B,N,N,C,P,M,4,3 - xPB_E_B,N,N,C,P,M,4,4  = 0
 c138: 0.998 xPB_E_B,N,N,C,P,M,4,4 - xPB_E_B,N,N,C,P,M,4,5  = 0
 c139: - 0.002 xPB_E_B,N,N,C,P,L,0,1 - 0.002 xPB_E_B,N,N,C,P,L,1,1
       - 0.002 xPB_E_B,N,N,C,P,L,2,1 - 0.002 xPB_E_B,N,N,C,P,L,3,1
       - 0.002 xPB_E_B,N,N,C,P,L,4,1 - 0.002 xPB_E_B,N,N,C,P,M,0,1
       - 0.002 xPB_E_B,N,N,C,P,M,1,1 - 0.002 xPB_E_B,N,N,C,P,M,2,1
       - 0.002 xPB_E_B,N,N,C,P,M,3,1 - 0.002 xPB_E_B,N,N,C,P,M,4,1
       + fire_B,N,N,C,P,1,P + fire_B,N,N,C,P,1,D + fire_B,N,N,C,P,1,W
       + fire_B,N,N,C,P,1,C + fire_B,N,N,C,P,1,I + fire_B,N,N,C,P,1,A
       + fire_B,N,N,C,P,1,L + fire_B,N,N,C,P,1,N  = 0
 c140: - 0.002 xPB_E_B,N,N,C,P,L,0,2 - 0.002 xPB_E_B,N,N,C,P,L,1,2
       - 0.002 xPB_E_B,N,N,C,P,L,2,2 - 0.002 xPB_E_B,N,N,C,P,L,3,2
       - 0.002 xPB_E_B,N,N,C,P,L,4,2 - 0.002 xPB_E_B,N,N,C,P,M,0,2
       - 0.002 xPB_E_B,N,N,C,P,M,1,2 - 0.002 xPB_E_B,N,N,C,P,M,2,2
       - 0.002 xPB_E_B,N,N,C,P,M,3,2 - 0.002 xPB_E_B,N,N,C,P,M,4,2
       - 0.002 xEA_R_B,N,N,C,P,3,2,P,0,2 - 0.002 xEA_R_B,N,N,C,P,4,3,P,0,2
       - 0.002 xEA_R_B,N,N,C,P,5,4,P,0,2 + fire_B,N,N,C,P,2,P
       + fire_B,N,N,C,P,2,D + fire_B,N,N,C,P,2,W + fire_B,N,N,C,P,2,C
       + fire_B,N,N,C,P,2,I + fire_B,N,N,C,P,2,A + fire_B,N,N,C,P,2,L
       + fire_B,N,N,C,P,2,N  = 0
 c141: - 0.002 xPB_E_B,N,N,C,P,L,0,3 - 0.002 xPB_E_B,N,N,C,P,L,1,3
       - 0.002 xPB_E_B,N,N,C,P,L,2,3 - 0.002 xPB_E_B,N,N,C,P,L,3,3
       - 0.002 xPB_E_B,N,N,C,P,L,4,3 - 0.002 xPB_E_B,N,N,C,P,M,0,3
       - 0.002 xPB_E_B,N,N,C,P,M,1,3 - 0.002 xPB_E_B,N,N,C,P,M,2,3
       - 0.002 xPB_E_B,N,N,C,P,M,3,3 - 0.002 xPB_E_B,N,N,C,P,M,4,3
       - 0.002 xEA_R_B,N,N,C,P,4,2,P,0,3 - 0.002 xEA_R_B,N,N,C,P,4,3,P,0,3
       - 0.002 xEA_R_B,N,N,C,P,5,3,P,0,3 - 0.002 xEA_R_B,N,N,C,P,5,4,P,0,3
       + fire_B,N,N,C,P,3,P + fire_B,N,N,C,P,3,D + fire_B,N,N,C,P,3,W
       + fire_B,N,N,C,P,3,C + fire_B,N,N,C,P,3,I + fire_B,N,N,C,P,3,A
       + fire_B,N,N,C,P,3,L + fire_B,N,N,C,P,3,N  = 0
 c142: - 0.002 xPB_E_B,N,N,C,P,L,0,4 - 0.002 xPB_E_B,N,N,C,P,L,1,4
       - 0.002 xPB_E_B,N,N,C,P,L,2,4 - 0.002 xPB_E_B,N,N,C,P,L,3,4
       - 0.002 xPB_E_B,N,N,C,P,L,4,4 - 0.002 xPB_E_B,N,N,C,P,M,0,4
       - 0.002 xPB_E_B,N,N,C,P,M,1,4 - 0.002 xPB_E_B,N,N,C,P,M,2,4
       - 0.002 xPB_E_B,N,N,C,P,M,3,4 - 0.002 xPB_E_B,N,N,C,P,M,4,4
       - 0.002 xEA_R_B,N,N,C,P,5,2,P,0,4 - 0.002 xEA_R_B,N,N,C,P,5,3,P,0,4
       - 0.002 xEA_R_B,N,N,C,P,5,4,P,0,4 + fire_B,N,N,C,P,4,P
       + fire_B,N,N,C,P,4,D + fire_B,N,N,C,P,4,W + fire_B,N,N,C,P,4,C
       + fire_B,N,N,C,P,4,I + fire_B,N,N,C,P,4,A + fire_B,N,N,C,P,4,L
       + fire_B,N,N,C,P,4,N  = 0
 c143: - 0.002 xPB_E_B,N,N,C,P,L,0,5 - 0.002 xPB_E_B,N,N,C,P,L,1,5
       - 0.002 xPB_E_B,N,N,C,P,L,2,5 - 0.002 xPB_E_B,N,N,C,P,L,3,5
       - 0.002 xPB_E_B,N,N,C,P,L,4,5 - 0.002 xPB_E_B,N,N,C,P,M,0,5
       - 0.002 xPB_E_B,N,N,C,P,M,1,5 - 0.002 xPB_E_B,N,N,C,P,M,2,5
       - 0.002 xPB_E_B,N,N,C,P,M,3,5 - 0.002 xPB_E_B,N,N,C,P,M,4,5
       + fire_B,N,N,C,P,5,P + fire_B,N,N,C,P,5,D + fire_B,N,N,C,P,5,W
       + fire_B,N,N,C,P,5,C + fire_B,N,N,C,P,5,I + fire_B,N,N,C,P,5,A
       + fire_B,N,N,C,P,5,L + fire_B,N,N,C,P,5,N  = 0
 c144: - 0.002 xPB_E_B,N,N,C,D,L,0,1 - 0.002 xPB_E_B,N,N,C,D,L,1,1
       - 0.002 xPB_E_B,N,N,C,D,L,2,1 - 0.002 xPB_E_B,N,N,C,D,L,3,1
       - 0.002 xPB_E_B,N,N,C,D,L,4,1 + fire_B,N,N,C,D,1,P + fire_B,N,N,C,D,1,D
       + fire_B,N,N,C,D,1,W + fire_B,N,N,C,D,1,C + fire_B,N,N,C,D,1,I
       + fire_B,N,N,C,D,1,A + fire_B,N,N,C,D,1,L + fire_B,N,N,C,D,1,N  = 0
 c145: - 0.002 xPB_E_B,N,N,C,D,L,0,2 - 0.002 xPB_E_B,N,N,C,D,L,1,2
       - 0.002 xPB_E_B,N,N,C,D,L,2,2 - 0.002 xPB_E_B,N,N,C,D,L,3,2
       - 0.002 xPB_E_B,N,N,C,D,L,4,2 - 0.002 xEA_R_B,N,N,C,D,3,2,D,0,2
       - 0.002 xEA_R_B,N,N,C,D,4,3,D,0,2 - 0.002 xEA_R_B,N,N,C,D,5,4,D,0,2
       + fire_B,N,N,C,D,2,P + fire_B,N,N,C,D,2,D + fire_B,N,N,C,D,2,W
       + fire_B,N,N,C,D,2,C + fire_B,N,N,C,D,2,I + fire_B,N,N,C,D,2,A
       + fire_B,N,N,C,D,2,L + fire_B,N,N,C,D,2,N  = 0
 c146: - 0.002 xPB_E_B,N,N,C,D,L,0,3 - 0.002 xPB_E_B,N,N,C,D,L,1,3
       - 0.002 xPB_E_B,N,N,C,D,L,2,3 - 0.002 xPB_E_B,N,N,C,D,L,3,3
       - 0.002 xPB_E_B,N,N,C,D,L,4,3 - 0.002 xEA_R_B,N,N,C,D,4,2,D,0,3
       - 0.002 xEA_R_B,N,N,C,D,4,3,D,0,3 - 0.002 xEA_R_B,N,N,C,D,5,3,D,0,3
       - 0.002 xEA_R_B,N,N,C,D,5,4,D,0,3 + fire_B,N,N,C,D,3,P
       + fire_B,N,N,C,D,3,D + fire_B,N,N,C,D,3,W + fire_B,N,N,C,D,3,C
       + fire_B,N,N,C,D,3,I + fire_B,N,N,C,D,3,A + fire_B,N,N,C,D,3,L
       + fire_B,N,N,C,D,3,N  = 0
 c147: - 0.002 xPB_E_B,N,N,C,D,L,0,4 - 0.002 xPB_E_B,N,N,C,D,L,1,4
       - 0.002 xPB_E_B,N,N,C,D,L,2,4 - 0.002 xPB_E_B,N,N,C,D,L,3,4
       - 0.002 xPB_E_B,N,N,C,D,L,4,4 - 0.002 xEA_R_B,N,N,C,D,5,2,D,0,4
       - 0.002 xEA_R_B,N,N,C,D,5,3,D,0,4 - 0.002 xEA_R_B,N,N,C,D,5,4,D,0,4
       + fire_B,N,N,C,D,4,P + fire_B,N,N,C,D,4,D + fire_B,N,N,C,D,4,W
       + fire_B,N,N,C,D,4,C + fire_B,N,N,C,D,4,I + fire_B,N,N,C,D,4,A
       + fire_B,N,N,C,D,4,L + fire_B,N,N,C,D,4,N  = 0
 c148: - 0.002 xPB_E_B,N,N,C,D,L,0,5 - 0.002 xPB_E_B,N,N,C,D,L,1,5
       - 0.002 xPB_E_B,N,N,C,D,L,2,5 - 0.002 xPB_E_B,N,N,C,D,L,3,5
       - 0.002 xPB_E_B,N,N,C,D,L,4,5 + fire_B,N,N,C,D,5,P + fire_B,N,N,C,D,5,D
       + fire_B,N,N,C,D,5,W + fire_B,N,N,C,D,5,C + fire_B,N,N,C,D,5,I
       + fire_B,N,N,C,D,5,A + fire_B,N,N,C,D,5,L + fire_B,N,N,C,D,5,N  = 0
 c149: fire_B,N,N,C,W,1,P + fire_B,N,N,C,W,1,D + fire_B,N,N,C,W,1,W
       + fire_B,N,N,C,W,1,C + fire_B,N,N,C,W,1,I + fire_B,N,N,C,W,1,A
       + fire_B,N,N,C,W,1,L + fire_B,N,N,C,W,1,N  = 0
 c150: - 0.002 xEA_R_B,N,N,C,W,3,2,W,0,2 - 0.002 xEA_R_B,N,N,C,W,4,3,W,0,2
       - 0.002 xEA_R_B,N,N,C,W,5,4,W,0,2 + fire_B,N,N,C,W,2,P
       + fire_B,N,N,C,W,2,D + fire_B,N,N,C,W,2,W + fire_B,N,N,C,W,2,C
       + fire_B,N,N,C,W,2,I + fire_B,N,N,C,W,2,A + fire_B,N,N,C,W,2,L
       + fire_B,N,N,C,W,2,N  = 0
 c151: - 0.002 xEA_R_B,N,N,C,W,4,2,W,0,3 - 0.002 xEA_R_B,N,N,C,W,4,3,W,0,3
       - 0.002 xEA_R_B,N,N,C,W,5,3,W,0,3 - 0.002 xEA_R_B,N,N,C,W,5,4,W,0,3
       + fire_B,N,N,C,W,3,P + fire_B,N,N,C,W,3,D + fire_B,N,N,C,W,3,W
       + fire_B,N,N,C,W,3,C + fire_B,N,N,C,W,3,I + fire_B,N,N,C,W,3,A
       + fire_B,N,N,C,W,3,L + fire_B,N,N,C,W,3,N  = 0
 c152: - 0.002 xEA_R_B,N,N,C,W,5,2,W,0,4 - 0.002 xEA_R_B,N,N,C,W,5,3,W,0,4
       - 0.002 xEA_R_B,N,N,C,W,5,4,W,0,4 + fire_B,N,N,C,W,4,P
       + fire_B,N,N,C,W,4,D + fire_B,N,N,C,W,4,W + fire_B,N,N,C,W,4,C
       + fire_B,N,N,C,W,4,I + fire_B,N,N,C,W,4,A + fire_B,N,N,C,W,4,L
       + fire_B,N,N,C,W,4,N  = 0
 c153: fire_B,N,N,C,W,5,P + fire_B,N,N,C,W,5,D + fire_B,N,N,C,W,5,W
       + fire_B,N,N,C,W,5,C + fire_B,N,N,C,W,5,I + fire_B,N,N,C,W,5,A
       + fire_B,N,N,C,W,5,L + fire_B,N,N,C,W,5,N  = 0
 c154: - 0.002 xPB_E_B,N,N,C,C,L,0,1 - 0.002 xPB_E_B,N,N,C,C,L,1,1
       - 0.002 xPB_E_B,N,N,C,C,L,2,1 - 0.002 xPB_E_B,N,N,C,C,L,3,1
       - 0.002 xPB_E_B,N,N,C,C,L,4,1 - 0.002 xPB_E_B,N,N,C,C,M,0,1
       - 0.002 xPB_E_B,N,N,C,C,M,1,1 - 0.002 xPB_E_B,N,N,C,C,M,2,1
       - 0.002 xPB_E_B,N,N,C,C,M,3,1 - 0.002 xPB_E_B,N,N,C,C,M,4,1
       - 0.002 xPB_E_B,N,N,C,C,P,0,1 - 0.002 xPB_E_B,N,N,C,C,P,1,1
       - 0.002 xPB_E_B,N,N,C,C,P,2,1 - 0.002 xPB_E_B,N,N,C,C,P,3,1
       - 0.002 xPB_E_B,N,N,C,C,P,4,1 + fire_B,N,N,C,C,1,P + fire_B,N,N,C,C,1,D
       + fire_B,N,N,C,C,1,W + fire_B,N,N,C,C,1,C + fire_B,N,N,C,C,1,I
       + fire_B,N,N,C,C,1,A + fire_B,N,N,C,C,1,L + fire_B,N,N,C,C,1,N  = 0
 c155: - 0.002 xPB_E_B,N,N,C,C,L,0,2 - 0.002 xPB_E_B,N,N,C,C,L,1,2
       - 0.002 xPB_E_B,N,N,C,C,L,2,2 - 0.002 xPB_E_B,N,N,C,C,L,3,2
       - 0.002 xPB_E_B,N,N,C,C,L,4,2 - 0.002 xPB_E_B,N,N,C,C,M,0,2
       - 0.002 xPB_E_B,N,N,C,C,M,1,2 - 0.002 xPB_E_B,N,N,C,C,M,2,2
       - 0.002 xPB_E_B,N,N,C,C,M,3,2 - 0.002 xPB_E_B,N,N,C,C,M,4,2
       - 0.002 xPB_E_B,N,N,C,C,P,0,2 - 0.002 xPB_E_B,N,N,C,C,P,1,2
       - 0.002 xPB_E_B,N,N,C,C,P,2,2 - 0.002 xPB_E_B,N,N,C,C,P,3,2
       - 0.002 xPB_E_B,N,N,C,C,P,4,2 - 0.002 xEA_R_B,N,N,C,C,3,2,C,0,2
       - 0.002 xEA_R_B,N,N,C,C,4,3,C,0,2 - 0.002 xEA_R_B,N,N,C,C,5,4,C,0,2
       + fire_B,N,N,C,C,2,P + fire_B,N,N,C,C,2,D + fire_B,N,N,C,C,2,W
       + fire_B,N,N,C,C,2,C + fire_B,N,N,C,C,2,I + fire_B,N,N,C,C,2,A
       + fire_B,N,N,C,C,2,L + fire_B,N,N,C,C,2,N  = 0
 c156: - 0.002 xPB_E_B,N,N,C,C,L,0,3 - 0.002 xPB_E_B,N,N,C,C,L,1,3
       - 0.002 xPB_E_B,N,N,C,C,L,2,3 - 0.002 xPB_E_B,N,N,C,C,L,3,3
       - 0.002 xPB_E_B,N,N,C,C,L,4,3 - 0.002 xPB_E_B,N,N,C,C,M,0,3
       - 0.002 xPB_E_B,N,N,C,C,M,1,3 - 0.002 xPB_E_B,N,N,C,C,M,2,3
       - 0.002 xPB_E_B,N,N,C,C,M,3,3 - 0.002 xPB_E_B,N,N,C,C,M,4,3
       - 0.002 xPB_E_B,N,N,C,C,P,0,3 - 0.002 xPB_E_B,N,N,C,C,P,1,3
       - 0.002 xPB_E_B,N,N,C,C,P,2,3 - 0.002 xPB_E_B,N,N,C,C,P,3,3
       - 0.002 xPB_E_B,N,N,C,C,P,4,3 - 0.002 xEA_R_B,N,N,C,C,4,2,C,0,3
       - 0.002 xEA_R_B,N,N,C,C,4,3,C,0,3 - 0.002 xEA_R_B,N,N,C,C,5,3,C,0,3
       - 0.002 xEA_R_B,N,N,C,C,5,4,C,0,3 + fire_B,N,N,C,C,3,P
       + fire_B,N,N,C,C,3,D + fire_B,N,N,C,C,3,W + fire_B,N,N,C,C,3,C
       + fire_B,N,N,C,C,3,I + fire_B,N,N,C,C,3,A + fire_B,N,N,C,C,3,L
       + fire_B,N,N,C,C,3,N  = 0
 c157: - 0.002 xPB_E_B,N,N,C,C,L,0,4 - 0.002 xPB_E_B,N,N,C,C,L,1,4
       - 0.002 xPB_E_B,N,N,C,C,L,2,4 - 0.002 xPB_E_B,N,N,C,C,L,3,4
       - 0.002 xPB_E_B,N,N,C,C,L,4,4 - 0.002 xPB_E_B,N,N,C,C,M,0,4
       - 0.002 xPB_E_B,N,N,C,C,M,1,4 - 0.002 xPB_E_B,N,N,C,C,M,2,4
       - 0.002 xPB_E_B,N,N,C,C,M,3,4 - 0.002 xPB_E_B,N,N,C,C,M,4,4
       - 0.002 xPB_E_B,N,N,C,C,P,0,4 - 0.002 xPB_E_B,N,N,C,C,P,1,4
       - 0.002 xPB_E_B,N,N,C,C,P,2,4 - 0.002 xPB_E_B,N,N,C,C,P,3,4
       - 0.002 xPB_E_B,N,N,C,C,P,4,4 - 0.002 xEA_R_B,N,N,C,C,5,2,C,0,4
       - 0.002 xEA_R_B,N,N,C,C,5,3,C,0,4 - 0.002 xEA_R_B,N,N,C,C,5,4,C,0,4
       + fire_B,N,N,C,C,4,P + fire_B,N,N,C,C,4,D + fire_B,N,N,C,C,4,W
       + fire_B,N,N,C,C,4,C + fire_B,N,N,C,C,4,I + fire_B,N,N,C,C,4,A
       + fire_B,N,N,C,C,4,L + fire_B,N,N,C,C,4,N  = 0
 c158: - 0.002 xPB_E_B,N,N,C,C,L,0,5 - 0.002 xPB_E_B,N,N,C,C,L,1,5
       - 0.002 xPB_E_B,N,N,C,C,L,2,5 - 0.002 xPB_E_B,N,N,C,C,L,3,5
       - 0.002 xPB_E_B,N,N,C,C,L,4,5 - 0.002 xPB_E_B,N,N,C,C,M,0,5
       - 0.002 xPB_E_B,N,N,C,C,M,1,5 - 0.002 xPB_E_B,N,N,C,C,M,2,5
       - 0.002 xPB_E_B,N,N,C,C,M,3,5 - 0.002 xPB_E_B,N,N,C,C,M,4,5
       - 0.002 xPB_E_B,N,N,C,C,P,0,5 - 0.002 xPB_E_B,N,N,C,C,P,1,5
       - 0.002 xPB_E_B,N,N,C,C,P,2,5 - 0.002 xPB_E_B,N,N,C,C,P,3,5
       - 0.002 xPB_E_B,N,N,C,C,P,4,5 + fire_B,N,N,C,C,5,P + fire_B,N,N,C,C,5,D
       + fire_B,N,N,C,C,5,W + fire_B,N,N,C,C,5,C + fire_B,N,N,C,C,5,I
       + fire_B,N,N,C,C,5,A + fire_B,N,N,C,C,5,L + fire_B,N,N,C,C,5,N  = 0
 c159: fire_B,N,N,C,I,1,P + fire_B,N,N,C,I,1,D + fire_B,N,N,C,I,1,W
       + fire_B,N,N,C,I,1,C + fire_B,N,N,C,I,1,I + fire_B,N,N,C,I,1,A
       + fire_B,N,N,C,I,1,L + fire_B,N,N,C,I,1,N  = 0
 c160: - 0.002 xEA_R_B,N,N,C,I,3,2,I,0,2 - 0.002 xEA_R_B,N,N,C,I,4,3,I,0,2
       - 0.002 xEA_R_B,N,N,C,I,5,4,I,0,2 + fire_B,N,N,C,I,2,P
       + fire_B,N,N,C,I,2,D + fire_B,N,N,C,I,2,W + fire_B,N,N,C,I,2,C
       + fire_B,N,N,C,I,2,I + fire_B,N,N,C,I,2,A + fire_B,N,N,C,I,2,L
       + fire_B,N,N,C,I,2,N  = 0
 c161: - 0.002 xEA_R_B,N,N,C,I,4,2,I,0,3 - 0.002 xEA_R_B,N,N,C,I,4,3,I,0,3
       - 0.002 xEA_R_B,N,N,C,I,5,3,I,0,3 - 0.002 xEA_R_B,N,N,C,I,5,4,I,0,3
       + fire_B,N,N,C,I,3,P + fire_B,N,N,C,I,3,D + fire_B,N,N,C,I,3,W
       + fire_B,N,N,C,I,3,C + fire_B,N,N,C,I,3,I + fire_B,N,N,C,I,3,A
       + fire_B,N,N,C,I,3,L + fire_B,N,N,C,I,3,N  = 0
 c162: - 0.002 xEA_R_B,N,N,C,I,5,2,I,0,4 - 0.002 xEA_R_B,N,N,C,I,5,3,I,0,4
       - 0.002 xEA_R_B,N,N,C,I,5,4,I,0,4 + fire_B,N,N,C,I,4,P
       + fire_B,N,N,C,I,4,D + fire_B,N,N,C,I,4,W + fire_B,N,N,C,I,4,C
       + fire_B,N,N,C,I,4,I + fire_B,N,N,C,I,4,A + fire_B,N,N,C,I,4,L
       + fire_B,N,N,C,I,4,N  = 0
 c163: fire_B,N,N,C,I,5,P + fire_B,N,N,C,I,5,D + fire_B,N,N,C,I,5,W
       + fire_B,N,N,C,I,5,C + fire_B,N,N,C,I,5,I + fire_B,N,N,C,I,5,A
       + fire_B,N,N,C,I,5,L + fire_B,N,N,C,I,5,N  = 0
 c164: fire_B,N,N,C,A,1,P + fire_B,N,N,C,A,1,D + fire_B,N,N,C,A,1,W
       + fire_B,N,N,C,A,1,C + fire_B,N,N,C,A,1,I + fire_B,N,N,C,A,1,A
       + fire_B,N,N,C,A,1,L + fire_B,N,N,C,A,1,N  = 0
 c165: - 0.002 xEA_R_B,N,N,C,A,3,2,A,0,2 - 0.002 xEA_R_B,N,N,C,A,4,3,A,0,2
       - 0.002 xEA_R_B,N,N,C,A,5,4,A,0,2 + fire_B,N,N,C,A,2,P
       + fire_B,N,N,C,A,2,D + fire_B,N,N,C,A,2,W + fire_B,N,N,C,A,2,C
       + fire_B,N,N,C,A,2,I + fire_B,N,N,C,A,2,A + fire_B,N,N,C,A,2,L
       + fire_B,N,N,C,A,2,N  = 0
 c166: - 0.002 xEA_R_B,N,N,C,A,4,2,A,0,3 - 0.002 xEA_R_B,N,N,C,A,4,3,A,0,3
       - 0.002 xEA_R_B,N,N,C,A,5,3,A,0,3 - 0.002 xEA_R_B,N,N,C,A,5,4,A,0,3
       + fire_B,N,N,C,A,3,P + fire_B,N,N,C,A,3,D + fire_B,N,N,C,A,3,W
       + fire_B,N,N,C,A,3,C + fire_B,N,N,C,A,3,I + fire_B,N,N,C,A,3,A
       + fire_B,N,N,C,A,3,L + fire_B,N,N,C,A,3,N  = 0
 c167: - 0.002 xEA_R_B,N,N,C,A,5,2,A,0,4 - 0.002 xEA_R_B,N,N,C,A,5,3,A,0,4
       - 0.002 xEA_R_B,N,N,C,A,5,4,A,0,4 + fire_B,N,N,C,A,4,P
       + fire_B,N,N,C,A,4,D + fire_B,N,N,C,A,4,W + fire_B,N,N,C,A,4,C
       + fire_B,N,N,C,A,4,I + fire_B,N,N,C,A,4,A + fire_B,N,N,C,A,4,L
       + fire_B,N,N,C,A,4,N  = 0
 c168: fire_B,N,N,C,A,5,P + fire_B,N,N,C,A,5,D + fire_B,N,N,C,A,5,W
       + fire_B,N,N,C,A,5,C + fire_B,N,N,C,A,5,I + fire_B,N,N,C,A,5,A
       + fire_B,N,N,C,A,5,L + fire_B,N,N,C,A,5,N  = 0
 c169: fire_B,N,N,C,L,1,P + fire_B,N,N,C,L,1,D + fire_B,N,N,C,L,1,W
       + fire_B,N,N,C,L,1,C + fire_B,N,N,C,L,1,I + fire_B,N,N,C,L,1,A
       + fire_B,N,N,C,L,1,L + fire_B,N,N,C,L,1,N  = 0
 c170: - 0.002 xEA_R_B,N,N,C,L,3,2,L,0,2 - 0.002 xEA_R_B,N,N,C,L,4,3,L,0,2
       - 0.002 xEA_R_B,N,N,C,L,5,4,L,0,2 + fire_B,N,N,C,L,2,P
       + fire_B,N,N,C,L,2,D + fire_B,N,N,C,L,2,W + fire_B,N,N,C,L,2,C
       + fire_B,N,N,C,L,2,I + fire_B,N,N,C,L,2,A + fire_B,N,N,C,L,2,L
       + fire_B,N,N,C,L,2,N  = 0
 c171: - 0.002 xEA_R_B,N,N,C,L,4,2,L,0,3 - 0.002 xEA_R_B,N,N,C,L,4,3,L,0,3
       - 0.002 xEA_R_B,N,N,C,L,5,3,L,0,3 - 0.002 xEA_R_B,N,N,C,L,5,4,L,0,3
       + fire_B,N,N,C,L,3,P + fire_B,N,N,C,L,3,D + fire_B,N,N,C,L,3,W
       + fire_B,N,N,C,L,3,C + fire_B,N,N,C,L,3,I + fire_B,N,N,C,L,3,A
       + fire_B,N,N,C,L,3,L + fire_B,N,N,C,L,3,N  = 0
 c172: - 0.002 xEA_R_B,N,N,C,L,5,2,L,0,4 - 0.002 xEA_R_B,N,N,C,L,5,3,L,0,4
       - 0.002 xEA_R_B,N,N,C,L,5,4,L,0,4 + fire_B,N,N,C,L,4,P
       + fire_B,N,N,C,L,4,D + fire_B,N,N,C,L,4,W + fire_B,N,N,C,L,4,C
       + fire_B,N,N,C,L,4,I + fire_B,N,N,C,L,4,A + fire_B,N,N,C,L,4,L
       + fire_B,N,N,C,L,4,N  = 0
 c173: fire_B,N,N,C,L,5,P + fire_B,N,N,C,L,5,D + fire_B,N,N,C,L,5,W
       + fire_B,N,N,C,L,5,C + fire_B,N,N,C,L,5,I + fire_B,N,N,C,L,5,A
       + fire_B,N,N,C,L,5,L + fire_B,N,N,C,L,5,N  = 0
 c174: fire_B,N,N,C,N,1,P + fire_B,N,N,C,N,1,D + fire_B,N,N,C,N,1,W
       + fire_B,N,N,C,N,1,C + fire_B,N,N,C,N,1,I + fire_B,N,N,C,N,1,A
       + fire_B,N,N,C,N,1,L + fire_B,N,N,C,N,1,N  = 0
 c175: fire_B,N,N,C,N,2,P + fire_B,N,N,C,N,2,D + fire_B,N,N,C,N,2,W
       + fire_B,N,N,C,N,2,C + fire_B,N,N,C,N,2,I + fire_B,N,N,C,N,2,A
       + fire_B,N,N,C,N,2,L + fire_B,N,N,C,N,2,N  = 0
 c176: fire_B,N,N,C,N,3,P + fire_B,N,N,C,N,3,D + fire_B,N,N,C,N,3,W
       + fire_B,N,N,C,N,3,C + fire_B,N,N,C,N,3,I + fire_B,N,N,C,N,3,A
       + fire_B,N,N,C,N,3,L + fire_B,N,N,C,N,3,N  = 0
 c177: fire_B,N,N,C,N,4,P + fire_B,N,N,C,N,4,D + fire_B,N,N,C,N,4,W
       + fire_B,N,N,C,N,4,C + fire_B,N,N,C,N,4,I + fire_B,N,N,C,N,4,A
       + fire_B,N,N,C,N,4,L + fire_B,N,N,C,N,4,N  = 0
 c178: fire_B,N,N,C,N,5,P + fire_B,N,N,C,N,5,D + fire_B,N,N,C,N,5,W
       + fire_B,N,N,C,N,5,C + fire_B,N,N,C,N,5,I + fire_B,N,N,C,N,5,A
       + fire_B,N,N,C,N,5,L + fire_B,N,N,C,N,5,N  = 0
 c179: - fire_B,N,N,C,P,1,D - fire_B,N,N,C,P,1,W - fire_B,N,N,C,P,1,C
       - fire_B,N,N,C,P,1,I - fire_B,N,N,C,P,1,A - fire_B,N,N,C,P,1,L
       - fire_B,N,N,C,P,1,N  = 0
 c180: fire_B,N,N,C,P,1,D  = 0
 c181: fire_B,N,N,C,P,1,W  = 0
 c182: fire_B,N,N,C,P,1,C  = 0
 c183: fire_B,N,N,C,P,1,I  = 0
 c184: fire_B,N,N,C,P,1,A  = 0
 c185: fire_B,N,N,C,P,1,L  = 0
 c186: fire_B,N,N,C,P,1,N  = 0
 c187: - fire_B,N,N,C,P,2,D - fire_B,N,N,C,P,2,W - fire_B,N,N,C,P,2,C
       - fire_B,N,N,C,P,2,I - fire_B,N,N,C,P,2,A - fire_B,N,N,C,P,2,L
       - fire_B,N,N,C,P,2,N  = 0
 c188: fire_B,N,N,C,P,2,D  = 0
 c189: fire_B,N,N,C,P,2,W  = 0
 c190: fire_B,N,N,C,P,2,C  = 0
 c191: fire_B,N,N,C,P,2,I  = 0
 c192: fire_B,N,N,C,P,2,A  = 0
 c193: fire_B,N,N,C,P,2,L  = 0
 c194: fire_B,N,N,C,P,2,N  = 0
 c195: - fire_B,N,N,C,P,3,D - fire_B,N,N,C,P,3,W - fire_B,N,N,C,P,3,C
       - fire_B,N,N,C,P,3,I - fire_B,N,N,C,P,3,A - fire_B,N,N,C,P,3,L
       - fire_B,N,N,C,P,3,N  = 0
 c196: fire_B,N,N,C,P,3,D  = 0
 c197: fire_B,N,N,C,P,3,W  = 0
 c198: fire_B,N,N,C,P,3,C  = 0
 c199: fire_B,N,N,C,P,3,I  = 0
 c200: fire_B,N,N,C,P,3,A  = 0
 c201: fire_B,N,N,C,P,3,L  = 0
 c202: fire_B,N,N,C,P,3,N  = 0
 c203: - fire_B,N,N,C,P,4,D - fire_B,N,N,C,P,4,W - fire_B,N,N,C,P,4,C
       - fire_B,N,N,C,P,4,I - fire_B,N,N,C,P,4,A - fire_B,N,N,C,P,4,L
       - fire_B,N,N,C,P,4,N  = 0
 c204: fire_B,N,N,C,P,4,D  = 0
 c205: fire_B,N,N,C,P,4,W  = 0
 c206: fire_B,N,N,C,P,4,C  = 0
 c207: fire_B,N,N,C,P,4,I  = 0
 c208: fire_B,N,N,C,P,4,A  = 0
 c209: fire_B,N,N,C,P,4,L  = 0
 c210: fire_B,N,N,C,P,4,N  = 0
 c211: - fire_B,N,N,C,P,5,D - fire_B,N,N,C,P,5,W - fire_B,N,N,C,P,5,C
       - fire_B,N,N,C,P,5,I - fire_B,N,N,C,P,5,A - fire_B,N,N,C,P,5,L
       - fire_B,N,N,C,P,5,N  = 0
 c212: fire_B,N,N,C,P,5,D  = 0
 c213: fire_B,N,N,C,P,5,W  = 0
 c214: fire_B,N,N,C,P,5,C  = 0
 c215: fire_B,N,N,C,P,5,I  = 0
 c216: fire_B,N,N,C,P,5,A  = 0
 c217: fire_B,N,N,C,P,5,L  = 0
 c218: fire_B,N,N,C,P,5,N  = 0
 c219: fire_B,N,N,C,D,1,P  = 0
 c220: - fire_B,N,N,C,D,1,P - fire_B,N,N,C,D,1,W - fire_B,N,N,C,D,1,C
       - fire_B,N,N,C,D,1,I - fire_B,N,N,C,D,1,A - fire_B,N,N,C,D,1,L
       - fire_B,N,N,C,D,1,N  = 0
 c221: fire_B,N,N,C,D,1,W  = 0
 c222: fire_B,N,N,C,D,1,C  = 0
 c223: fire_B,N,N,C,D,1,I  = 0
 c224: fire_B,N,N,C,D,1,A  = 0
 c225: fire_B,N,N,C,D,1,L  = 0
 c226: fire_B,N,N,C,D,1,N  = 0
 c227: fire_B,N,N,C,D,2,P  = 0
 c228: - fire_B,N,N,C,D,2,P - fire_B,N,N,C,D,2,W - fire_B,N,N,C,D,2,C
       - fire_B,N,N,C,D,2,I - fire_B,N,N,C,D,2,A - fire_B,N,N,C,D,2,L
       - fire_B,N,N,C,D,2,N  = 0
 c229: fire_B,N,N,C,D,2,W  = 0
 c230: fire_B,N,N,C,D,2,C  = 0
 c231: fire_B,N,N,C,D,2,I  = 0
 c232: fire_B,N,N,C,D,2,A  = 0
 c233: fire_B,N,N,C,D,2,L  = 0
 c234: fire_B,N,N,C,D,2,N  = 0
 c235: fire_B,N,N,C,D,3,P  = 0
 c236: - fire_B,N,N,C,D,3,P - fire_B,N,N,C,D,3,W - fire_B,N,N,C,D,3,C
       - fire_B,N,N,C,D,3,I - fire_B,N,N,C,D,3,A - fire_B,N,N,C,D,3,L
       - fire_B,N,N,C,D,3,N  = 0
 c237: fire_B,N,N,C,D,3,W  = 0
 c238: fire_B,N,N,C,D,3,C  = 0
 c239: fire_B,N,N,C,D,3,I  = 0
 c240: fire_B,N,N,C,D,3,A  = 0
 c241: fire_B,N,N,C,D,3,L  = 0
 c242: fire_B,N,N,C,D,3,N  = 0
 c243: fire_B,N,N,C,D,4,P  = 0
 c244: - fire_B,N,N,C,D,4,P - fire_B,N,N,C,D,4,W - fire_B,N,N,C,D,4,C
       - fire_B,N,N,C,D,4,I - fire_B,N,N,C,D,4,A - fire_B,N,N,C,D,4,L
       - fire_B,N,N,C,D,4,N  = 0
 c245: fire_B,N,N,C,D,4,W  = 0
 c246: fire_B,N,N,C,D,4,C  = 0
 c247: fire_B,N,N,C,D,4,I  = 0
 c248: fire_B,N,N,C,D,4,A  = 0
 c249: fire_B,N,N,C,D,4,L  = 0
 c250: fire_B,N,N,C,D,4,N  = 0
 c251: fire_B,N,N,C,D,5,P  = 0
 c252: - fire_B,N,N,C,D,5,P - fire_B,N,N,C,D,5,W - fire_B,N,N,C,D,5,C
       - fire_B,N,N,C,D,5,I - fire_B,N,N,C,D,5,A - fire_B,N,N,C,D,5,L
       - fire_B,N,N,C,D,5,N  = 0
 c253: fire_B,N,N,C,D,5,W  = 0
 c254: fire_B,N,N,C,D,5,C  = 0
 c255: fire_B,N,N,C,D,5,I  = 0
 c256: fire_B,N,N,C,D,5,A  = 0
 c257: fire_B,N,N,C,D,5,L  = 0
 c258: fire_B,N,N,C,D,5,N  = 0
 c259: fire_B,N,N,C,W,1,P  = 0
 c260: fire_B,N,N,C,W,1,D  = 0
 c261: - fire_B,N,N,C,W,1,P - fire_B,N,N,C,W,1,D - fire_B,N,N,C,W,1,C
       - fire_B,N,N,C,W,1,I - fire_B,N,N,C,W,1,A - fire_B,N,N,C,W,1,L
       - fire_B,N,N,C,W,1,N  = 0
 c262: fire_B,N,N,C,W,1,C  = 0
 c263: fire_B,N,N,C,W,1,I  = 0
 c264: fire_B,N,N,C,W,1,A  = 0
 c265: fire_B,N,N,C,W,1,L  = 0
 c266: fire_B,N,N,C,W,1,N  = 0
 c267: fire_B,N,N,C,W,2,P  = 0
 c268: fire_B,N,N,C,W,2,D  = 0
 c269: - fire_B,N,N,C,W,2,P - fire_B,N,N,C,W,2,D - fire_B,N,N,C,W,2,C
       - fire_B,N,N,C,W,2,I - fire_B,N,N,C,W,2,A - fire_B,N,N,C,W,2,L
       - fire_B,N,N,C,W,2,N  = 0
 c270: fire_B,N,N,C,W,2,C  = 0
 c271: fire_B,N,N,C,W,2,I  = 0
 c272: fire_B,N,N,C,W,2,A  = 0
 c273: fire_B,N,N,C,W,2,L  = 0
 c274: fire_B,N,N,C,W,2,N  = 0
 c275: fire_B,N,N,C,W,3,P  = 0
 c276: fire_B,N,N,C,W,3,D  = 0
 c277: - fire_B,N,N,C,W,3,P - fire_B,N,N,C,W,3,D - fire_B,N,N,C,W,3,C
       - fire_B,N,N,C,W,3,I - fire_B,N,N,C,W,3,A - fire_B,N,N,C,W,3,L
       - fire_B,N,N,C,W,3,N  = 0
 c278: fire_B,N,N,C,W,3,C  = 0
 c279: fire_B,N,N,C,W,3,I  = 0
 c280: fire_B,N,N,C,W,3,A  = 0
 c281: fire_B,N,N,C,W,3,L  = 0
 c282: fire_B,N,N,C,W,3,N  = 0
 c283: fire_B,N,N,C,W,4,P  = 0
 c284: fire_B,N,N,C,W,4,D  = 0
 c285: - fire_B,N,N,C,W,4,P - fire_B,N,N,C,W,4,D - fire_B,N,N,C,W,4,C
       - fire_B,N,N,C,W,4,I - fire_B,N,N,C,W,4,A - fire_B,N,N,C,W,4,L
       - fire_B,N,N,C,W,4,N  = 0
 c286: fire_B,N,N,C,W,4,C  = 0
 c287: fire_B,N,N,C,W,4,I  = 0
 c288: fire_B,N,N,C,W,4,A  = 0
 c289: fire_B,N,N,C,W,4,L  = 0
 c290: fire_B,N,N,C,W,4,N  = 0
 c291: fire_B,N,N,C,W,5,P  = 0
 c292: fire_B,N,N,C,W,5,D  = 0
 c293: - fire_B,N,N,C,W,5,P - fire_B,N,N,C,W,5,D - fire_B,N,N,C,W,5,C
       - fire_B,N,N,C,W,5,I - fire_B,N,N,C,W,5,A - fire_B,N,N,C,W,5,L
       - fire_B,N,N,C,W,5,N  = 0
 c294: fire_B,N,N,C,W,5,C  = 0
 c295: fire_B,N,N,C,W,5,I  = 0
 c296: fire_B,N,N,C,W,5,A  = 0
 c297: fire_B,N,N,C,W,5,L  = 0
 c298: fire_B,N,N,C,W,5,N  = 0
 c299: fire_B,N,N,C,C,1,P  = 0
 c300: fire_B,N,N,C,C,1,D  = 0
 c301: fire_B,N,N,C,C,1,W  = 0
 c302: - fire_B,N,N,C,C,1,P - fire_B,N,N,C,C,1,D - fire_B,N,N,C,C,1,W
       - fire_B,N,N,C,C,1,I - fire_B,N,N,C,C,1,A - fire_B,N,N,C,C,1,L
       - fire_B,N,N,C,C,1,N  = 0
 c303: fire_B,N,N,C,C,1,I  = 0
 c304: fire_B,N,N,C,C,1,A  = 0
 c305: fire_B,N,N,C,C,1,L  = 0
 c306: fire_B,N,N,C,C,1,N  = 0
 c307: fire_B,N,N,C,C,2,P  = 0
 c308: fire_B,N,N,C,C,2,D  = 0
 c309: fire_B,N,N,C,C,2,W  = 0
 c310: - fire_B,N,N,C,C,2,P - fire_B,N,N,C,C,2,D - fire_B,N,N,C,C,2,W
       - fire_B,N,N,C,C,2,I - fire_B,N,N,C,C,2,A - fire_B,N,N,C,C,2,L
       - fire_B,N,N,C,C,2,N  = 0
 c311: fire_B,N,N,C,C,2,I  = 0
 c312: fire_B,N,N,C,C,2,A  = 0
 c313: fire_B,N,N,C,C,2,L  = 0
 c314: fire_B,N,N,C,C,2,N  = 0
 c315: fire_B,N,N,C,C,3,P  = 0
 c316: fire_B,N,N,C,C,3,D  = 0
 c317: fire_B,N,N,C,C,3,W  = 0
 c318: - fire_B,N,N,C,C,3,P - fire_B,N,N,C,C,3,D - fire_B,N,N,C,C,3,W
       - fire_B,N,N,C,C,3,I - fire_B,N,N,C,C,3,A - fire_B,N,N,C,C,3,L
       - fire_B,N,N,C,C,3,N  = 0
 c319: fire_B,N,N,C,C,3,I  = 0
 c320: fire_B,N,N,C,C,3,A  = 0
 c321: fire_B,N,N,C,C,3,L  = 0
 c322: fire_B,N,N,C,C,3,N  = 0
 c323: fire_B,N,N,C,C,4,P  = 0
 c324: fire_B,N,N,C,C,4,D  = 0
 c325: fire_B,N,N,C,C,4,W  = 0
 c326: - fire_B,N,N,C,C,4,P - fire_B,N,N,C,C,4,D - fire_B,N,N,C,C,4,W
       - fire_B,N,N,C,C,4,I - fire_B,N,N,C,C,4,A - fire_B,N,N,C,C,4,L
       - fire_B,N,N,C,C,4,N  = 0
 c327: fire_B,N,N,C,C,4,I  = 0
 c328: fire_B,N,N,C,C,4,A  = 0
 c329: fire_B,N,N,C,C,4,L  = 0
 c330: fire_B,N,N,C,C,4,N  = 0
 c331: fire_B,N,N,C,C,5,P  = 0
 c332: fire_B,N,N,C,C,5,D  = 0
 c333: fire_B,N,N,C,C,5,W  = 0
 c334: - fire_B,N,N,C,C,5,P - fire_B,N,N,C,C,5,D - fire_B,N,N,C,C,5,W
       - fire_B,N,N,C,C,5,I - fire_B,N,N,C,C,5,A - fire_B,N,N,C,C,5,L
       - fire_B,N,N,C,C,5,N  = 0
 c335: fire_B,N,N,C,C,5,I  = 0
 c336: fire_B,N,N,C,C,5,A  = 0
 c337: fire_B,N,N,C,C,5,L  = 0
 c338: fire_B,N,N,C,C,5,N  = 0
 c339: fire_B,N,N,C,I,1,P  = 0
 c340: fire_B,N,N,C,I,1,D  = 0
 c341: fire_B,N,N,C,I,1,W  = 0
 c342: fire_B,N,N,C,I,1,C  = 0
 c343: - fire_B,N,N,C,I,1,P - fire_B,N,N,C,I,1,D - fire_B,N,N,C,I,1,W
       - fire_B,N,N,C,I,1,C - fire_B,N,N,C,I,1,A - fire_B,N,N,C,I,1,L
       - fire_B,N,N,C,I,1,N  = 0
 c344: fire_B,N,N,C,I,1,A  = 0
 c345: fire_B,N,N,C,I,1,L  = 0
 c346: fire_B,N,N,C,I,1,N  = 0
 c347: fire_B,N,N,C,I,2,P  = 0
 c348: fire_B,N,N,C,I,2,D  = 0
 c349: fire_B,N,N,C,I,2,W  = 0
 c350: fire_B,N,N,C,I,2,C  = 0
 c351: - fire_B,N,N,C,I,2,P - fire_B,N,N,C,I,2,D - fire_B,N,N,C,I,2,W
       - fire_B,N,N,C,I,2,C - fire_B,N,N,C,I,2,A - fire_B,N,N,C,I,2,L
       - fire_B,N,N,C,I,2,N  = 0
 c352: fire_B,N,N,C,I,2,A  = 0
 c353: fire_B,N,N,C,I,2,L  = 0
 c354: fire_B,N,N,C,I,2,N  = 0
 c355: fire_B,N,N,C,I,3,P  = 0
 c356: fire_B,N,N,C,I,3,D  = 0
 c357: fire_B,N,N,C,I,3,W  = 0
 c358: fire_B,N,N,C,I,3,C  = 0
 c359: - fire_B,N,N,C,I,3,P - fire_B,N,N,C,I,3,D - fire_B,N,N,C,I,3,W
       - fire_B,N,N,C,I,3,C - fire_B,N,N,C,I,3,A - fire_B,N,N,C,I,3,L
       - fire_B,N,N,C,I,3,N  = 0
 c360: fire_B,N,N,C,I,3,A  = 0
 c361: fire_B,N,N,C,I,3,L  = 0
 c362: fire_B,N,N,C,I,3,N  = 0
 c363: fire_B,N,N,C,I,4,P  = 0
 c364: fire_B,N,N,C,I,4,D  = 0
 c365: fire_B,N,N,C,I,4,W  = 0
 c366: fire_B,N,N,C,I,4,C  = 0
 c367: - fire_B,N,N,C,I,4,P - fire_B,N,N,C,I,4,D - fire_B,N,N,C,I,4,W
       - fire_B,N,N,C,I,4,C - fire_B,N,N,C,I,4,A - fire_B,N,N,C,I,4,L
       - fire_B,N,N,C,I,4,N  = 0
 c368: fire_B,N,N,C,I,4,A  = 0
 c369: fire_B,N,N,C,I,4,L  = 0
 c370: fire_B,N,N,C,I,4,N  = 0
 c371: fire_B,N,N,C,I,5,P  = 0
 c372: fire_B,N,N,C,I,5,D  = 0
 c373: fire_B,N,N,C,I,5,W  = 0
 c374: fire_B,N,N,C,I,5,C  = 0
 c375: - fire_B,N,N,C,I,5,P - fire_B,N,N,C,I,5,D - fire_B,N,N,C,I,5,W
       - fire_B,N,N,C,I,5,C - fire_B,N,N,C,I,5,A - fire_B,N,N,C,I,5,L
       - fire_B,N,N,C,I,5,N  = 0
 c376: fire_B,N,N,C,I,5,A  = 0
 c377: fire_B,N,N,C,I,5,L  = 0
 c378: fire_B,N,N,C,I,5,N  = 0
 c379: fire_B,N,N,C,A,1,P  = 0
 c380: fire_B,N,N,C,A,1,D  = 0
 c381: fire_B,N,N,C,A,1,W  = 0
 c382: fire_B,N,N,C,A,1,C  = 0
 c383: fire_B,N,N,C,A,1,I  = 0
 c384: - fire_B,N,N,C,A,1,P - fire_B,N,N,C,A,1,D - fire_B,N,N,C,A,1,W
       - fire_B,N,N,C,A,1,C - fire_B,N,N,C,A,1,I - fire_B,N,N,C,A,1,L
       - fire_B,N,N,C,A,1,N  = 0
 c385: fire_B,N,N,C,A,1,L  = 0
 c386: fire_B,N,N,C,A,1,N  = 0
 c387: fire_B,N,N,C,A,2,P  = 0
 c388: fire_B,N,N,C,A,2,D  = 0
 c389: fire_B,N,N,C,A,2,W  = 0
 c390: fire_B,N,N,C,A,2,C  = 0
 c391: fire_B,N,N,C,A,2,I  = 0
 c392: - fire_B,N,N,C,A,2,P - fire_B,N,N,C,A,2,D - fire_B,N,N,C,A,2,W
       - fire_B,N,N,C,A,2,C - fire_B,N,N,C,A,2,I - fire_B,N,N,C,A,2,L
       - fire_B,N,N,C,A,2,N  = 0
 c393: fire_B,N,N,C,A,2,L  = 0
 c394: fire_B,N,N,C,A,2,N  = 0
 c395: fire_B,N,N,C,A,3,P  = 0
 c396: fire_B,N,N,C,A,3,D  = 0
 c397: fire_B,N,N,C,A,3,W  = 0
 c398: fire_B,N,N,C,A,3,C  = 0
 c399: fire_B,N,N,C,A,3,I  = 0
 c400: - fire_B,N,N,C,A,3,P - fire_B,N,N,C,A,3,D - fire_B,N,N,C,A,3,W
       - fire_B,N,N,C,A,3,C - fire_B,N,N,C,A,3,I - fire_B,N,N,C,A,3,L
       - fire_B,N,N,C,A,3,N  = 0
 c401: fire_B,N,N,C,A,3,L  = 0
 c402: fire_B,N,N,C,A,3,N  = 0
 c403: fire_B,N,N,C,A,4,P  = 0
 c404: fire_B,N,N,C,A,4,D  = 0
 c405: fire_B,N,N,C,A,4,W  = 0
 c406: fire_B,N,N,C,A,4,C  = 0
 c407: fire_B,N,N,C,A,4,I  = 0
 c408: - fire_B,N,N,C,A,4,P - fire_B,N,N,C,A,4,D - fire_B,N,N,C,A,4,W
       - fire_B,N,N,C,A,4,C - fire_B,N,N,C,A,4,I - fire_B,N,N,C,A,4,L
       - fire_B,N,N,C,A,4,N  = 0
 c409: fire_B,N,N,C,A,4,L  = 0
 c410: fire_B,N,N,C,A,4,N  = 0
 c411: fire_B,N,N,C,A,5,P  = 0
 c412: fire_B,N,N,C,A,5,D  = 0
 c413: fire_B,N,N,C,A,5,W  = 0
 c414: fire_B,N,N,C,A,5,C  = 0
 c415: fire_B,N,N,C,A,5,I  = 0
 c416: - fire_B,N,N,C,A,5,P - fire_B,N,N,C,A,5,D - fire_B,N,N,C,A,5,W
       - fire_B,N,N,C,A,5,C - fire_B,N,N,C,A,5,I - fire_B,N,N,C,A,5,L
       - fire_B,N,N,C,A,5,N  = 0
 c417: fire_B,N,N,C,A,5,L  = 0
 c418: fire_B,N,N,C,A,5,N  = 0
 c419: fire_B,N,N,C,L,1,P  = 0
 c420: fire_B,N,N,C,L,1,D  = 0
 c421: fire_B,N,N,C,L,1,W  = 0
 c422: fire_B,N,N,C,L,1,C  = 0
 c423: fire_B,N,N,C,L,1,I  = 0
 c424: fire_B,N,N,C,L,1,A  = 0
 c425: - fire_B,N,N,C,L,1,P - fire_B,N,N,C,L,1,D - fire_B,N,N,C,L,1,W
       - fire_B,N,N,C,L,1,C - fire_B,N,N,C,L,1,I - fire_B,N,N,C,L,1,A
       - fire_B,N,N,C,L,1,N  = 0
 c426: fire_B,N,N,C,L,1,N  = 0
 c427: fire_B,N,N,C,L,2,P  = 0
 c428: fire_B,N,N,C,L,2,D  = 0
 c429: fire_B,N,N,C,L,2,W  = 0
 c430: fire_B,N,N,C,L,2,C  = 0
 c431: fire_B,N,N,C,L,2,I  = 0
 c432: fire_B,N,N,C,L,2,A  = 0
 c433: - fire_B,N,N,C,L,2,P - fire_B,N,N,C,L,2,D - fire_B,N,N,C,L,2,W
       - fire_B,N,N,C,L,2,C - fire_B,N,N,C,L,2,I - fire_B,N,N,C,L,2,A
       - fire_B,N,N,C,L,2,N  = 0
 c434: fire_B,N,N,C,L,2,N  = 0
 c435: fire_B,N,N,C,L,3,P  = 0
 c436: fire_B,N,N,C,L,3,D  = 0
 c437: fire_B,N,N,C,L,3,W  = 0
 c438: fire_B,N,N,C,L,3,C  = 0
 c439: fire_B,N,N,C,L,3,I  = 0
 c440: fire_B,N,N,C,L,3,A  = 0
 c441: - fire_B,N,N,C,L,3,P - fire_B,N,N,C,L,3,D - fire_B,N,N,C,L,3,W
       - fire_B,N,N,C,L,3,C - fire_B,N,N,C,L,3,I - fire_B,N,N,C,L,3,A
       - fire_B,N,N,C,L,3,N  = 0
 c442: fire_B,N,N,C,L,3,N  = 0
 c443: fire_B,N,N,C,L,4,P  = 0
 c444: fire_B,N,N,C,L,4,D  = 0
 c445: fire_B,N,N,C,L,4,W  = 0
 c446: fire_B,N,N,C,L,4,C  = 0
 c447: fire_B,N,N,C,L,4,I  = 0
 c448: fire_B,N,N,C,L,4,A  = 0
 c449: - fire_B,N,N,C,L,4,P - fire_B,N,N,C,L,4,D - fire_B,N,N,C,L,4,W
       - fire_B,N,N,C,L,4,C - fire_B,N,N,C,L,4,I - fire_B,N,N,C,L,4,A
       - fire_B,N,N,C,L,4,N  = 0
 c450: fire_B,N,N,C,L,4,N  = 0
 c451: fire_B,N,N,C,L,5,P  = 0
 c452: fire_B,N,N,C,L,5,D  = 0
 c453: fire_B,N,N,C,L,5,W  = 0
 c454: fire_B,N,N,C,L,5,C  = 0
 c455: fire_B,N,N,C,L,5,I  = 0
 c456: fire_B,N,N,C,L,5,A  = 0
 c457: - fire_B,N,N,C,L,5,P - fire_B,N,N,C,L,5,D - fire_B,N,N,C,L,5,W
       - fire_B,N,N,C,L,5,C - fire_B,N,N,C,L,5,I - fire_B,N,N,C,L,5,A
       - fire_B,N,N,C,L,5,N  = 0
 c458: fire_B,N,N,C,L,5,N  = 0
 c459: fire_B,N,N,C,N,1,P  = 0
 c460: fire_B,N,N,C,N,1,D  = 0
 c461: fire_B,N,N,C,N,1,W  = 0
 c462: fire_B,N,N,C,N,1,C  = 0
 c463: fire_B,N,N,C,N,1,I  = 0
 c464: fire_B,N,N,C,N,1,A  = 0
 c465: fire_B,N,N,C,N,1,L  = 0
 c466: - fire_B,N,N,C,N,1,P - fire_B,N,N,C,N,1,D - fire_B,N,N,C,N,1,W
       - fire_B,N,N,C,N,1,C - fire_B,N,N,C,N,1,I - fire_B,N,N,C,N,1,A
       - fire_B,N,N,C,N,1,L  = 0
 c467: fire_B,N,N,C,N,2,P  = 0
 c468: fire_B,N,N,C,N,2,D  = 0
 c469: fire_B,N,N,C,N,2,W  = 0
 c470: fire_B,N,N,C,N,2,C  = 0
 c471: fire_B,N,N,C,N,2,I  = 0
 c472: fire_B,N,N,C,N,2,A  = 0
 c473: fire_B,N,N,C,N,2,L  = 0
 c474: - fire_B,N,N,C,N,2,P - fire_B,N,N,C,N,2,D - fire_B,N,N,C,N,2,W
       - fire_B,N,N,C,N,2,C - fire_B,N,N,C,N,2,I - fire_B,N,N,C,N,2,A
       - fire_B,N,N,C,N,2,L  = 0
 c475: fire_B,N,N,C,N,3,P  = 0
 c476: fire_B,N,N,C,N,3,D  = 0
 c477: fire_B,N,N,C,N,3,W  = 0
 c478: fire_B,N,N,C,N,3,C  = 0
 c479: fire_B,N,N,C,N,3,I  = 0
 c480: fire_B,N,N,C,N,3,A  = 0
 c481: fire_B,N,N,C,N,3,L  = 0
 c482: - fire_B,N,N,C,N,3,P - fire_B,N,N,C,N,3,D - fire_B,N,N,C,N,3,W
       - fire_B,N,N,C,N,3,C - fire_B,N,N,C,N,3,I - fire_B,N,N,C,N,3,A
       - fire_B,N,N,C,N,3,L  = 0
 c483: fire_B,N,N,C,N,4,P  = 0
 c484: fire_B,N,N,C,N,4,D  = 0
 c485: fire_B,N,N,C,N,4,W  = 0
 c486: fire_B,N,N,C,N,4,C  = 0
 c487: fire_B,N,N,C,N,4,I  = 0
 c488: fire_B,N,N,C,N,4,A  = 0
 c489: fire_B,N,N,C,N,4,L  = 0
 c490: - fire_B,N,N,C,N,4,P - fire_B,N,N,C,N,4,D - fire_B,N,N,C,N,4,W
       - fire_B,N,N,C,N,4,C - fire_B,N,N,C,N,4,I - fire_B,N,N,C,N,4,A
       - fire_B,N,N,C,N,4,L  = 0
 c491: fire_B,N,N,C,N,5,P  = 0
 c492: fire_B,N,N,C,N,5,D  = 0
 c493: fire_B,N,N,C,N,5,W  = 0
 c494: fire_B,N,N,C,N,5,C  = 0
 c495: fire_B,N,N,C,N,5,I  = 0
 c496: fire_B,N,N,C,N,5,A  = 0
 c497: fire_B,N,N,C,N,5,L  = 0
 c498: - fire_B,N,N,C,N,5,P - fire_B,N,N,C,N,5,D - fire_B,N,N,C,N,5,W
       - fire_B,N,N,C,N,5,C - fire_B,N,N,C,N,5,I - fire_B,N,N,C,N,5,A
       - fire_B,N,N,C,N,5,L  = 0
 c499: - xEA_R_B,N,N,C,P,2,1,P,0,2 - xEA_R_B,N,N,C,P,3,2,P,0,2
       - xEA_R_B,N,N,C,P,4,3,P,0,2 - xEA_R_B,N,N,C,P,5,4,P,0,2
       + fire_B,N,N,C,P,1,P + fire_B,N,N,C,D,1,P + fire_B,N,N,C,W,1,P
       + fire_B,N,N,C,C,1,P + fire_B,N,N,C,I,1,P + fire_B,N,N,C,A,1,P
       + fire_B,N,N,C,L,1,P + fire_B,N,N,C,N,1,P  = 0
 c500: - xEA_R_B,N,N,C,D,2,1,D,0,2 - xEA_R_B,N,N,C,D,3,2,D,0,2
       - xEA_R_B,N,N,C,D,4,3,D,0,2 - xEA_R_B,N,N,C,D,5,4,D,0,2
       + fire_B,N,N,C,P,1,D + fire_B,N,N,C,D,1,D + fire_B,N,N,C,W,1,D
       + fire_B,N,N,C,C,1,D + fire_B,N,N,C,I,1,D + fire_B,N,N,C,A,1,D
       + fire_B,N,N,C,L,1,D + fire_B,N,N,C,N,1,D  = 0
 c501: - xEA_R_B,N,N,C,W,2,1,W,0,2 - xEA_R_B,N,N,C,W,3,2,W,0,2
       - xEA_R_B,N,N,C,W,4,3,W,0,2 - xEA_R_B,N,N,C,W,5,4,W,0,2
       + fire_B,N,N,C,P,1,W + fire_B,N,N,C,D,1,W + fire_B,N,N,C,W,1,W
       + fire_B,N,N,C,C,1,W + fire_B,N,N,C,I,1,W + fire_B,N,N,C,A,1,W
       + fire_B,N,N,C,L,1,W + fire_B,N,N,C,N,1,W  = 0
 c502: - xEA_R_B,N,N,C,C,2,1,C,0,2 - xEA_R_B,N,N,C,C,3,2,C,0,2
       - xEA_R_B,N,N,C,C,4,3,C,0,2 - xEA_R_B,N,N,C,C,5,4,C,0,2
       + fire_B,N,N,C,P,1,C + fire_B,N,N,C,D,1,C + fire_B,N,N,C,W,1,C
       + fire_B,N,N,C,C,1,C + fire_B,N,N,C,I,1,C + fire_B,N,N,C,A,1,C
       + fire_B,N,N,C,L,1,C + fire_B,N,N,C,N,1,C  = 0
 c503: - xEA_R_B,N,N,C,I,2,1,I,0,2 - xEA_R_B,N,N,C,I,3,2,I,0,2
       - xEA_R_B,N,N,C,I,4,3,I,0,2 - xEA_R_B,N,N,C,I,5,4,I,0,2
       + fire_B,N,N,C,P,1,I + fire_B,N,N,C,D,1,I + fire_B,N,N,C,W,1,I
       + fire_B,N,N,C,C,1,I + fire_B,N,N,C,I,1,I + fire_B,N,N,C,A,1,I
       + fire_B,N,N,C,L,1,I + fire_B,N,N,C,N,1,I  = 0
 c504: - xEA_R_B,N,N,C,A,2,1,A,0,2 - xEA_R_B,N,N,C,A,3,2,A,0,2
       - xEA_R_B,N,N,C,A,4,3,A,0,2 - xEA_R_B,N,N,C,A,5,4,A,0,2
       + fire_B,N,N,C,P,1,A + fire_B,N,N,C,D,1,A + fire_B,N,N,C,W,1,A
       + fire_B,N,N,C,C,1,A + fire_B,N,N,C,I,1,A + fire_B,N,N,C,A,1,A
       + fire_B,N,N,C,L,1,A + fire_B,N,N,C,N,1,A  = 0
 c505: - xEA_R_B,N,N,C,L,2,1,L,0,2 - xEA_R_B,N,N,C,L,3,2,L,0,2
       - xEA_R_B,N,N,C,L,4,3,L,0,2 - xEA_R_B,N,N,C,L,5,4,L,0,2
       + fire_B,N,N,C,P,1,L + fire_B,N,N,C,D,1,L + fire_B,N,N,C,W,1,L
       + fire_B,N,N,C,C,1,L + fire_B,N,N,C,I,1,L + fire_B,N,N,C,A,1,L
       + fire_B,N,N,C,L,1,L + fire_B,N,N,C,N,1,L  = 0
 c506: fire_B,N,N,C,P,1,N + fire_B,N,N,C,D,1,N + fire_B,N,N,C,W,1,N
       + fire_B,N,N,C,C,1,N + fire_B,N,N,C,I,1,N + fire_B,N,N,C,A,1,N
       + fire_B,N,N,C,L,1,N + fire_B,N,N,C,N,1,N  = 0
 c507: xEA_R_B,N,N,C,P,2,1,P,0,2 - xEA_R_B,N,N,C,P,3,1,P,0,3
       - xEA_R_B,N,N,C,P,4,2,P,0,3 - xEA_R_B,N,N,C,P,5,3,P,0,3
       + fire_B,N,N,C,P,2,P + fire_B,N,N,C,D,2,P + fire_B,N,N,C,W,2,P
       + fire_B,N,N,C,C,2,P + fire_B,N,N,C,I,2,P + fire_B,N,N,C,A,2,P
       + fire_B,N,N,C,L,2,P + fire_B,N,N,C,N,2,P  = 0
 c508: xEA_R_B,N,N,C,D,2,1,D,0,2 - xEA_R_B,N,N,C,D,3,1,D,0,3
       - xEA_R_B,N,N,C,D,4,2,D,0,3 - xEA_R_B,N,N,C,D,5,3,D,0,3
       + fire_B,N,N,C,P,2,D + fire_B,N,N,C,D,2,D + fire_B,N,N,C,W,2,D
       + fire_B,N,N,C,C,2,D + fire_B,N,N,C,I,2,D + fire_B,N,N,C,A,2,D
       + fire_B,N,N,C,L,2,D + fire_B,N,N,C,N,2,D  = 0
 c509: xEA_R_B,N,N,C,W,2,1,W,0,2 - xEA_R_B,N,N,C,W,3,1,W,0,3
       - xEA_R_B,N,N,C,W,4,2,W,0,3 - xEA_R_B,N,N,C,W,5,3,W,0,3
       + fire_B,N,N,C,P,2,W + fire_B,N,N,C,D,2,W + fire_B,N,N,C,W,2,W
       + fire_B,N,N,C,C,2,W + fire_B,N,N,C,I,2,W + fire_B,N,N,C,A,2,W
       + fire_B,N,N,C,L,2,W + fire_B,N,N,C,N,2,W  = 0
 c510: xEA_R_B,N,N,C,C,2,1,C,0,2 - xEA_R_B,N,N,C,C,3,1,C,0,3
       - xEA_R_B,N,N,C,C,4,2,C,0,3 - xEA_R_B,N,N,C,C,5,3,C,0,3
       + fire_B,N,N,C,P,2,C + fire_B,N,N,C,D,2,C + fire_B,N,N,C,W,2,C
       + fire_B,N,N,C,C,2,C + fire_B,N,N,C,I,2,C + fire_B,N,N,C,A,2,C
       + fire_B,N,N,C,L,2,C + fire_B,N,N,C,N,2,C  = 0
 c511: xEA_R_B,N,N,C,I,2,1,I,0,2 - xEA_R_B,N,N,C,I,3,1,I,0,3
       - xEA_R_B,N,N,C,I,4,2,I,0,3 - xEA_R_B,N,N,C,I,5,3,I,0,3
       + fire_B,N,N,C,P,2,I + fire_B,N,N,C,D,2,I + fire_B,N,N,C,W,2,I
       + fire_B,N,N,C,C,2,I + fire_B,N,N,C,I,2,I + fire_B,N,N,C,A,2,I
       + fire_B,N,N,C,L,2,I + fire_B,N,N,C,N,2,I  = 0
 c512: xEA_R_B,N,N,C,A,2,1,A,0,2 - xEA_R_B,N,N,C,A,3,1,A,0,3
       - xEA_R_B,N,N,C,A,4,2,A,0,3 - xEA_R_B,N,N,C,A,5,3,A,0,3
       + fire_B,N,N,C,P,2,A + fire_B,N,N,C,D,2,A + fire_B,N,N,C,W,2,A
       + fire_B,N,N,C,C,2,A + fire_B,N,N,C,I,2,A + fire_B,N,N,C,A,2,A
       + fire_B,N,N,C,L,2,A + fire_B,N,N,C,N,2,A  = 0
 c513: xEA_R_B,N,N,C,L,2,1,L,0,2 - xEA_R_B,N,N,C,L,3,1,L,0,3
       - xEA_R_B,N,N,C,L,4,2,L,0,3 - xEA_R_B,N,N,C,L,5,3,L,0,3
       + fire_B,N,N,C,P,2,L + fire_B,N,N,C,D,2,L + fire_B,N,N,C,W,2,L
       + fire_B,N,N,C,C,2,L + fire_B,N,N,C,I,2,L + fire_B,N,N,C,A,2,L
       + fire_B,N,N,C,L,2,L + fire_B,N,N,C,N,2,L  = 0
 c514: fire_B,N,N,C,P,2,N + fire_B,N,N,C,D,2,N + fire_B,N,N,C,W,2,N
       + fire_B,N,N,C,C,2,N + fire_B,N,N,C,I,2,N + fire_B,N,N,C,A,2,N
       + fire_B,N,N,C,L,2,N + fire_B,N,N,C,N,2,N  = 0
 c515: xEA_R_B,N,N,C,P,3,1,P,0,3 + xEA_R_B,N,N,C,P,3,2,P,0,3
       - xEA_R_B,N,N,C,P,4,1,P,0,4 - xEA_R_B,N,N,C,P,5,2,P,0,4
       + fire_B,N,N,C,P,3,P + fire_B,N,N,C,D,3,P + fire_B,N,N,C,W,3,P
       + fire_B,N,N,C,C,3,P + fire_B,N,N,C,I,3,P + fire_B,N,N,C,A,3,P
       + fire_B,N,N,C,L,3,P + fire_B,N,N,C,N,3,P  = 0
 c516: xEA_R_B,N,N,C,D,3,1,D,0,3 + xEA_R_B,N,N,C,D,3,2,D,0,3
       - xEA_R_B,N,N,C,D,4,1,D,0,4 - xEA_R_B,N,N,C,D,5,2,D,0,4
       + fire_B,N,N,C,P,3,D + fire_B,N,N,C,D,3,D + fire_B,N,N,C,W,3,D
       + fire_B,N,N,C,C,3,D + fire_B,N,N,C,I,3,D + fire_B,N,N,C,A,3,D
       + fire_B,N,N,C,L,3,D + fire_B,N,N,C,N,3,D  = 0
 c517: xEA_R_B,N,N,C,W,3,1,W,0,3 + xEA_R_B,N,N,C,W,3,2,W,0,3
       - xEA_R_B,N,N,C,W,4,1,W,0,4 - xEA_R_B,N,N,C,W,5,2,W,0,4
       + fire_B,N,N,C,P,3,W + fire_B,N,N,C,D,3,W + fire_B,N,N,C,W,3,W
       + fire_B,N,N,C,C,3,W + fire_B,N,N,C,I,3,W + fire_B,N,N,C,A,3,W
       + fire_B,N,N,C,L,3,W + fire_B,N,N,C,N,3,W  = 0
 c518: xEA_R_B,N,N,C,C,3,1,C,0,3 + xEA_R_B,N,N,C,C,3,2,C,0,3
       - xEA_R_B,N,N,C,C,4,1,C,0,4 - xEA_R_B,N,N,C,C,5,2,C,0,4
       + fire_B,N,N,C,P,3,C + fire_B,N,N,C,D,3,C + fire_B,N,N,C,W,3,C
       + fire_B,N,N,C,C,3,C + fire_B,N,N,C,I,3,C + fire_B,N,N,C,A,3,C
       + fire_B,N,N,C,L,3,C + fire_B,N,N,C,N,3,C  = 0
 c519: xEA_R_B,N,N,C,I,3,1,I,0,3 + xEA_R_B,N,N,C,I,3,2,I,0,3
       - xEA_R_B,N,N,C,I,4,1,I,0,4 - xEA_R_B,N,N,C,I,5,2,I,0,4
       + fire_B,N,N,C,P,3,I + fire_B,N,N,C,D,3,I + fire_B,N,N,C,W,3,I
       + fire_B,N,N,C,C,3,I + fire_B,N,N,C,I,3,I + fire_B,N,N,C,A,3,I
       + fire_B,N,N,C,L,3,I + fire_B,N,N,C,N,3,I  = 0
 c520: xEA_R_B,N,N,C,A,3,1,A,0,3 + xEA_R_B,N,N,C,A,3,2,A,0,3
       - xEA_R_B,N,N,C,A,4,1,A,0,4 - xEA_R_B,N,N,C,A,5,2,A,0,4
       + fire_B,N,N,C,P,3,A + fire_B,N,N,C,D,3,A + fire_B,N,N,C,W,3,A
       + fire_B,N,N,C,C,3,A + fire_B,N,N,C,I,3,A + fire_B,N,N,C,A,3,A
       + fire_B,N,N,C,L,3,A + fire_B,N,N,C,N,3,A  = 0
 c521: xEA_R_B,N,N,C,L,3,1,L,0,3 + xEA_R_B,N,N,C,L,3,2,L,0,3
       - xEA_R_B,N,N,C,L,4,1,L,0,4 - xEA_R_B,N,N,C,L,5,2,L,0,4
       + fire_B,N,N,C,P,3,L + fire_B,N,N,C,D,3,L + fire_B,N,N,C,W,3,L
       + fire_B,N,N,C,C,3,L + fire_B,N,N,C,I,3,L + fire_B,N,N,C,A,3,L
       + fire_B,N,N,C,L,3,L + fire_B,N,N,C,N,3,L  = 0
 c522: fire_B,N,N,C,P,3,N + fire_B,N,N,C,D,3,N + fire_B,N,N,C,W,3,N
       + fire_B,N,N,C,C,3,N + fire_B,N,N,C,I,3,N + fire_B,N,N,C,A,3,N
       + fire_B,N,N,C,L,3,N + fire_B,N,N,C,N,3,N  = 0
 c523: xEA_R_B,N,N,C,P,4,1,P,0,4 + xEA_R_B,N,N,C,P,4,2,P,0,4
       + xEA_R_B,N,N,C,P,4,3,P,0,4 - xEA_R_B,N,N,C,P,5,1,P,0,5
       + fire_B,N,N,C,P,4,P + fire_B,N,N,C,D,4,P + fire_B,N,N,C,W,4,P
       + fire_B,N,N,C,C,4,P + fire_B,N,N,C,I,4,P + fire_B,N,N,C,A,4,P
       + fire_B,N,N,C,L,4,P + fire_B,N,N,C,N,4,P  = 0
 c524: xEA_R_B,N,N,C,D,4,1,D,0,4 + xEA_R_B,N,N,C,D,4,2,D,0,4
       + xEA_R_B,N,N,C,D,4,3,D,0,4 - xEA_R_B,N,N,C,D,5,1,D,0,5
       + fire_B,N,N,C,P,4,D + fire_B,N,N,C,D,4,D + fire_B,N,N,C,W,4,D
       + fire_B,N,N,C,C,4,D + fire_B,N,N,C,I,4,D + fire_B,N,N,C,A,4,D
       + fire_B,N,N,C,L,4,D + fire_B,N,N,C,N,4,D  = 0
 c525: xEA_R_B,N,N,C,W,4,1,W,0,4 + xEA_R_B,N,N,C,W,4,2,W,0,4
       + xEA_R_B,N,N,C,W,4,3,W,0,4 - xEA_R_B,N,N,C,W,5,1,W,0,5
       + fire_B,N,N,C,P,4,W + fire_B,N,N,C,D,4,W + fire_B,N,N,C,W,4,W
       + fire_B,N,N,C,C,4,W + fire_B,N,N,C,I,4,W + fire_B,N,N,C,A,4,W
       + fire_B,N,N,C,L,4,W + fire_B,N,N,C,N,4,W  = 0
 c526: xEA_R_B,N,N,C,C,4,1,C,0,4 + xEA_R_B,N,N,C,C,4,2,C,0,4
       + xEA_R_B,N,N,C,C,4,3,C,0,4 - xEA_R_B,N,N,C,C,5,1,C,0,5
       + fire_B,N,N,C,P,4,C + fire_B,N,N,C,D,4,C + fire_B,N,N,C,W,4,C
       + fire_B,N,N,C,C,4,C + fire_B,N,N,C,I,4,C + fire_B,N,N,C,A,4,C
       + fire_B,N,N,C,L,4,C + fire_B,N,N,C,N,4,C  = 0
 c527: xEA_R_B,N,N,C,I,4,1,I,0,4 + xEA_R_B,N,N,C,I,4,2,I,0,4
       + xEA_R_B,N,N,C,I,4,3,I,0,4 - xEA_R_B,N,N,C,I,5,1,I,0,5
       + fire_B,N,N,C,P,4,I + fire_B,N,N,C,D,4,I + fire_B,N,N,C,W,4,I
       + fire_B,N,N,C,C,4,I + fire_B,N,N,C,I,4,I + fire_B,N,N,C,A,4,I
       + fire_B,N,N,C,L,4,I + fire_B,N,N,C,N,4,I  = 0
 c528: xEA_R_B,N,N,C,A,4,1,A,0,4 + xEA_R_B,N,N,C,A,4,2,A,0,4
       + xEA_R_B,N,N,C,A,4,3,A,0,4 - xEA_R_B,N,N,C,A,5,1,A,0,5
       + fire_B,N,N,C,P,4,A + fire_B,N,N,C,D,4,A + fire_B,N,N,C,W,4,A
       + fire_B,N,N,C,C,4,A + fire_B,N,N,C,I,4,A + fire_B,N,N,C,A,4,A
       + fire_B,N,N,C,L,4,A + fire_B,N,N,C,N,4,A  = 0
 c529: xEA_R_B,N,N,C,L,4,1,L,0,4 + xEA_R_B,N,N,C,L,4,2,L,0,4
       + xEA_R_B,N,N,C,L,4,3,L,0,4 - xEA_R_B,N,N,C,L,5,1,L,0,5
       + fire_B,N,N,C,P,4,L + fire_B,N,N,C,D,4,L + fire_B,N,N,C,W,4,L
       + fire_B,N,N,C,C,4,L + fire_B,N,N,C,I,4,L + fire_B,N,N,C,A,4,L
       + fire_B,N,N,C,L,4,L + fire_B,N,N,C,N,4,L  = 0
 c530: fire_B,N,N,C,P,4,N + fire_B,N,N,C,D,4,N + fire_B,N,N,C,W,4,N
       + fire_B,N,N,C,C,4,N + fire_B,N,N,C,I,4,N + fire_B,N,N,C,A,4,N
       + fire_B,N,N,C,L,4,N + fire_B,N,N,C,N,4,N  = 0
 c531: 0.998 xEA_R_B,N,N,C,P,3,2,P,0,2 - xEA_R_B,N,N,C,P,3,2,P,0,3  = 0
 c532: 0.998 xEA_R_B,N,N,C,P,4,2,P,0,3 - xEA_R_B,N,N,C,P,4,2,P,0,4  = 0
 c533: 0.998 xEA_R_B,N,N,C,P,4,3,P,0,2 - xEA_R_B,N,N,C,P,4,3,P,0,3  = 0
 c534: 0.998 xEA_R_B,N,N,C,P,4,3,P,0,3 - xEA_R_B,N,N,C,P,4,3,P,0,4  = 0
 c535: 0.998 xEA_R_B,N,N,C,P,5,2,P,0,4 - xEA_R_B,N,N,C,P,5,2,P,0,5  = 0
 c536: 0.998 xEA_R_B,N,N,C,P,5,3,P,0,3 - xEA_R_B,N,N,C,P,5,3,P,0,4  = 0
 c537: 0.998 xEA_R_B,N,N,C,P,5,3,P,0,4 - xEA_R_B,N,N,C,P,5,3,P,0,5  = 0
 c538: 0.998 xEA_R_B,N,N,C,P,5,4,P,0,2 - xEA_R_B,N,N,C,P,5,4,P,0,3  = 0
 c539: 0.998 xEA_R_B,N,N,C,P,5,4,P,0,3 - xEA_R_B,N,N,C,P,5,4,P,0,4  = 0
 c540: 0.998 xEA_R_B,N,N,C,P,5,4,P,0,4 - xEA_R_B,N,N,C,P,5,4,P,0,5  = 0
 c541: 0.998 xEA_R_B,N,N,C,D,3,2,D,0,2 - xEA_R_B,N,N,C,D,3,2,D,0,3  = 0
 c542: 0.998 xEA_R_B,N,N,C,D,4,2,D,0,3 - xEA_R_B,N,N,C,D,4,2,D,0,4  = 0
 c543: 0.998 xEA_R_B,N,N,C,D,4,3,D,0,2 - xEA_R_B,N,N,C,D,4,3,D,0,3  = 0
 c544: 0.998 xEA_R_B,N,N,C,D,4,3,D,0,3 - xEA_R_B,N,N,C,D,4,3,D,0,4  = 0
 c545: 0.998 xEA_R_B,N,N,C,D,5,2,D,0,4 - xEA_R_B,N,N,C,D,5,2,D,0,5  = 0
 c546: 0.998 xEA_R_B,N,N,C,D,5,3,D,0,3 - xEA_R_B,N,N,C,D,5,3,D,0,4  = 0
 c547: 0.998 xEA_R_B,N,N,C,D,5,3,D,0,4 - xEA_R_B,N,N,C,D,5,3,D,0,5  = 0
 c548: 0.998 xEA_R_B,N,N,C,D,5,4,D,0,2 - xEA_R_B,N,N,C,D,5,4,D,0,3  = 0
 c549: 0.998 xEA_R_B,N,N,C,D,5,4,D,0,3 - xEA_R_B,N,N,C,D,5,4,D,0,4  = 0
 c550: 0.998 xEA_R_B,N,N,C,D,5,4,D,0,4 - xEA_R_B,N,N,C,D,5,4,D,0,5  = 0
 c551: 0.998 xEA_R_B,N,N,C,W,3,2,W,0,2 - xEA_R_B,N,N,C,W,3,2,W,0,3  = 0
 c552: 0.998 xEA_R_B,N,N,C,W,4,2,W,0,3 - xEA_R_B,N,N,C,W,4,2,W,0,4  = 0
 c553: 0.998 xEA_R_B,N,N,C,W,4,3,W,0,2 - xEA_R_B,N,N,C,W,4,3,W,0,3  = 0
 c554: 0.998 xEA_R_B,N,N,C,W,4,3,W,0,3 - xEA_R_B,N,N,C,W,4,3,W,0,4  = 0
 c555: 0.998 xEA_R_B,N,N,C,W,5,2,W,0,4 - xEA_R_B,N,N,C,W,5,2,W,0,5  = 0
 c556: 0.998 xEA_R_B,N,N,C,W,5,3,W,0,3 - xEA_R_B,N,N,C,W,5,3,W,0,4  = 0
 c557: 0.998 xEA_R_B,N,N,C,W,5,3,W,0,4 - xEA_R_B,N,N,C,W,5,3,W,0,5  = 0
 c558: 0.998 xEA_R_B,N,N,C,W,5,4,W,0,2 - xEA_R_B,N,N,C,W,5,4,W,0,3  = 0
 c559: 0.998 xEA_R_B,N,N,C,W,5,4,W,0,3 - xEA_R_B,N,N,C,W,5,4,W,0,4  = 0
 c560: 0.998 xEA_R_B,N,N,C,W,5,4,W,0,4 - xEA_R_B,N,N,C,W,5,4,W,0,5  = 0
 c561: 0.998 xEA_R_B,N,N,C,C,3,2,C,0,2 - xEA_R_B,N,N,C,C,3,2,C,0,3  = 0
 c562: 0.998 xEA_R_B,N,N,C,C,4,2,C,0,3 - xEA_R_B,N,N,C,C,4,2,C,0,4  = 0
 c563: 0.998 xEA_R_B,N,N,C,C,4,3,C,0,2 - xEA_R_B,N,N,C,C,4,3,C,0,3  = 0
 c564: 0.998 xEA_R_B,N,N,C,C,4,3,C,0,3 - xEA_R_B,N,N,C,C,4,3,C,0,4  = 0
 c565: 0.998 xEA_R_B,N,N,C,C,5,2,C,0,4 - xEA_R_B,N,N,C,C,5,2,C,0,5  = 0
 c566: 0.998 xEA_R_B,N,N,C,C,5,3,C,0,3 - xEA_R_B,N,N,C,C,5,3,C,0,4  = 0
 c567: 0.998 xEA_R_B,N,N,C,C,5,3,C,0,4 - xEA_R_B,N,N,C,C,5,3,C,0,5  = 0
 c568: 0.998 xEA_R_B,N,N,C,C,5,4,C,0,2 - xEA_R_B,N,N,C,C,5,4,C,0,3  = 0
 c569: 0.998 xEA_R_B,N,N,C,C,5,4,C,0,3 - xEA_R_B,N,N,C,C,5,4,C,0,4  = 0
 c570: 0.998 xEA_R_B,N,N,C,C,5,4,C,0,4 - xEA_R_B,N,N,C,C,5,4,C,0,5  = 0
 c571: 0.998 xEA_R_B,N,N,C,I,3,2,I,0,2 - xEA_R_B,N,N,C,I,3,2,I,0,3  = 0
 c572: 0.998 xEA_R_B,N,N,C,I,4,2,I,0,3 - xEA_R_B,N,N,C,I,4,2,I,0,4  = 0
 c573: 0.998 xEA_R_B,N,N,C,I,4,3,I,0,2 - xEA_R_B,N,N,C,I,4,3,I,0,3  = 0
 c574: 0.998 xEA_R_B,N,N,C,I,4,3,I,0,3 - xEA_R_B,N,N,C,I,4,3,I,0,4  = 0
 c575: 0.998 xEA_R_B,N,N,C,I,5,2,I,0,4 - xEA_R_B,N,N,C,I,5,2,I,0,5  = 0
 c576: 0.998 xEA_R_B,N,N,C,I,5,3,I,0,3 - xEA_R_B,N,N,C,I,5,3,I,0,4  = 0
 c577: 0.998 xEA_R_B,N,N,C,I,5,3,I,0,4 - xEA_R_B,N,N,C,I,5,3,I,0,5  = 0
 c578: 0.998 xEA_R_B,N,N,C,I,5,4,I,0,2 - xEA_R_B,N,N,C,I,5,4,I,0,3  = 0
 c579: 0.998 xEA_R_B,N,N,C,I,5,4,I,0,3 - xEA_R_B,N,N,C,I,5,4,I,0,4  = 0
 c580: 0.998 xEA_R_B,N,N,C,I,5,4,I,0,4 - xEA_R_B,N,N,C,I,5,4,I,0,5  = 0
 c581: 0.998 xEA_R_B,N,N,C,A,3,2,A,0,2 - xEA_R_B,N,N,C,A,3,2,A,0,3  = 0
 c582: 0.998 xEA_R_B,N,N,C,A,4,2,A,0,3 - xEA_R_B,N,N,C,A,4,2,A,0,4  = 0
 c583: 0.998 xEA_R_B,N,N,C,A,4,3,A,0,2 - xEA_R_B,N,N,C,A,4,3,A,0,3  = 0
 c584: 0.998 xEA_R_B,N,N,C,A,4,3,A,0,3 - xEA_R_B,N,N,C,A,4,3,A,0,4  = 0
 c585: 0.998 xEA_R_B,N,N,C,A,5,2,A,0,4 - xEA_R_B,N,N,C,A,5,2,A,0,5  = 0
 c586: 0.998 xEA_R_B,N,N,C,A,5,3,A,0,3 - xEA_R_B,N,N,C,A,5,3,A,0,4  = 0
 c587: 0.998 xEA_R_B,N,N,C,A,5,3,A,0,4 - xEA_R_B,N,N,C,A,5,3,A,0,5  = 0
 c588: 0.998 xEA_R_B,N,N,C,A,5,4,A,0,2 - xEA_R_B,N,N,C,A,5,4,A,0,3  = 0
 c589: 0.998 xEA_R_B,N,N,C,A,5,4,A,0,3 - xEA_R_B,N,N,C,A,5,4,A,0,4  = 0
 c590: 0.998 xEA_R_B,N,N,C,A,5,4,A,0,4 - xEA_R_B,N,N,C,A,5,4,A,0,5  = 0
 c591: 0.998 xEA_R_B,N,N,C,L,3,2,L,0,2 - xEA_R_B,N,N,C,L,3,2,L,0,3  = 0
 c592: 0.998 xEA_R_B,N,N,C,L,4,2,L,0,3 - xEA_R_B,N,N,C,L,4,2,L,0,4  = 0
 c593: 0.998 xEA_R_B,N,N,C,L,4,3,L,0,2 - xEA_R_B,N,N,C,L,4,3,L,0,3  = 0
 c594: 0.998 xEA_R_B,N,N,C,L,4,3,L,0,3 - xEA_R_B,N,N,C,L,4,3,L,0,4  = 0
 c595: 0.998 xEA_R_B,N,N,C,L,5,2,L,0,4 - xEA_R_B,N,N,C,L,5,2,L,0,5  = 0
 c596: 0.998 xEA_R_B,N,N,C,L,5,3,L,0,3 - xEA_R_B,N,N,C,L,5,3,L,0,4  = 0
 c597: 0.998 xEA_R_B,N,N,C,L,5,3,L,0,4 - xEA_R_B,N,N,C,L,5,3,L,0,5  = 0
 c598: 0.998 xEA_R_B,N,N,C,L,5,4,L,0,2 - xEA_R_B,N,N,C,L,5,4,L,0,3  = 0
 c599: 0.998 xEA_R_B,N,N,C,L,5,4,L,0,3 - xEA_R_B,N,N,C,L,5,4,L,0,4  = 0
 c600: 0.998 xEA_R_B,N,N,C,L,5,4,L,0,4 - xEA_R_B,N,N,C,L,5,4,L,0,5  = 0
Bounds
      x_B,N,N,C,C,L,2 = 0
      x_B,N,N,C,C,L,3 = 0
      x_B,N,N,C,C,L,4 = 0
      x_B,N,N,C,C,L,5 = 0
      x_B,N,N,C,C,M,2 = 0
      x_B,N,N,C,C,M,3 = 0
      x_B,N,N,C,C,M,4 = 0
      x_B,N,N,C,C,M,5 = 0
      x_B,N,N,C,C,P,2 = 0
      x_B,N,N,C,C,P,3 = 0
      x_B,N,N,C,C,P,4 = 0
      x_B,N,N,C,C,P,5 = 0
      x_B,N,N,C,D,L,2 = 0
      x_B,N,N,C,D,L,3 = 0
      x_B,N,N,C,D,L,4 = 0
      x_B,N,N,C,D,L,5 = 0
      x_B,N,N,C,P,L,2 = 0
      x_B,N,N,C,P,L,3 = 0
      x_B,N,N,C,P,L,4 = 0
      x_B,N,N,C,P,L,5 = 0
      x_B,N,N,C,P,M,2 = 0
      x_B,N,N,C,P,M,3 = 0
      x_B,N,N,C,P,M,4 = 0
      x_B,N,N,C,P,M,5 = 0
End
