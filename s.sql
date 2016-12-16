use betting;
CREATE TABLE IF NOT EXISTS history
(
    pid INT NOT NULL,
    amount DECIMAL(15, 2) NOT NULL,
    numbers INT NOT NULL,
    is_ladi TINYINT(1) NOT NULL,
    ladi VARCHAR(11) NOT NULL,
    PRIMARY KEY(pid)
);

CREATE TABLE IF NOT EXISTS client_form
(
    pid VARCHAR(11) NOT NULL,
    is_utaar BOOLEAN NOT NULL,
	C1	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C2	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C3	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C4	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C5	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C6	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C7	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C8	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C9	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C10	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C11	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C12	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C13	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C14	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C15	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C16	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C17	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C18	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C19	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C20	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C21	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C22	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C23	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C24	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C25	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C26	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C27	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C28	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C29	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C30	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C31	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C32	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C33	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C34	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C35	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C36	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C37	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C38	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C39	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C40	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C41	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C42	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C43	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C44	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C45	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C46	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C47	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C48	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C49	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C50	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C51	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C52	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C53	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C54	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C55	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C56	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C57	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C58	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C59	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C60	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C61	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C62	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C63	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C64	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C65	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C66	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C67	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C68	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C69	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C70	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C71	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C72	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C73	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C74	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C75	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C76	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C77	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C78	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C79	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C80	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C81	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C82	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C83	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C84	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C85	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C86	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C87	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C88	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C89	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C90	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C91	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C92	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C93	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C94	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C95	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C96	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C97	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C98	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C99	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C100	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C101	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C102	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C103	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C104	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C105	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C106	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C107	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C108	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C109	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C110	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C111	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C112	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C113	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C114	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C115	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C116	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C117	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C118	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C119	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
	C120	DECIMAL(15, 2) DEFAULT 0 NOT NULL,
    CONSTRAINT client_form_pk PRIMARY KEY(pid)
);

SELECT (c1+c2+c3+c4+c5+c6+c7+c8+c9+c10+c11+c12+c13+c14+c15+c16+c17+c18+c19+c20+c21+c22+c23+c24+c25+c26+c27+c28+c29+c30+c31+c32+c33+c34+c35+c36+c37+c38+c39+c40+c41+c42+c43+c44+c45+c46+c47+c48+c49+c50+c51+c52+c53+c54+c55+c56+c57+c58+c59+c60+c61+c62+c63+c64+c65+c66+c67+c68+c69+c70+c71+c72+c73+c74+c75+c76+c77+c78+c79+c80+c81+c82+c83+c84+c85+c86+c87+c88+c89+c90+c91+c92+c93+c94+c95+c96+c97+c98+c99+c100+c101+c102+c103+c104+c105+c106+c107+c108+c109+c110+c111+c112+c113+c114+c115+c116+c117+c118+c119+c120) FROM betting.client_form c;
CREATE DATABASE `betting` /*!40100 DEFAULT CHARACTER SET latin1 */;

DROP TABLE IF EXISTS `betting`.`client_form`;
CREATE TABLE  `betting`.`client_form` (
  `pid` int(11) NOT NULL,
  `is_utaar` tinyint(1) NOT NULL,
  `C1` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C2` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C3` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C4` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C5` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C6` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C7` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C8` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C9` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C10` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C11` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C12` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C13` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C14` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C15` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C16` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C17` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C18` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C19` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C20` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C21` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C22` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C23` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C24` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C25` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C26` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C27` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C28` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C29` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C30` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C31` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C32` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C33` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C34` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C35` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C36` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C37` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C38` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C39` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C40` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C41` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C42` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C43` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C44` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C45` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C46` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C47` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C48` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C49` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C50` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C51` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C52` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C53` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C54` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C55` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C56` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C57` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C58` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C59` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C60` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C61` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C62` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C63` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C64` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C65` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C66` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C67` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C68` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C69` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C70` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C71` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C72` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C73` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C74` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C75` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C76` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C77` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C78` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C79` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C80` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C81` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C82` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C83` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C84` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C85` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C86` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C87` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C88` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C89` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C90` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C91` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C92` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C93` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C94` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C95` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C96` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C97` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C98` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C99` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C100` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C101` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C102` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C103` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C104` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C105` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C106` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C107` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C108` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C109` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C110` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C111` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C112` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C113` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C114` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C115` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C116` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C117` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C118` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C119` decimal(15,2) NOT NULL DEFAULT '0.00',
  `C120` decimal(15,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `betting`.`history`;
CREATE TABLE  `betting`.`history` (
  `pid` int(11) NOT NULL,
  `amount` decimal(15,2) NOT NULL,
  `numbers` int(11) NOT NULL,
  `is_ladi` tinyint(1) NOT NULL,
  `ladi` varchar(11) NOT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;






private void getResult(String drawid, Date drawDate, int pid) throws SQLException {
    	Database db = new Database();
    	if(pid==-1){
    		
    	}else{
    		
    		String drawResultQuery="select result_num from draw_table where draw_id="+drawid+"and draw_date="+drawDate+";";
    		ResultSet res = db.getdata(drawResultQuery);
    		res.next();
    		String resultNum = "c"+Integer.toString(res.getInt(0));
    		db.close();
    		
    		String amountResultQuery="select c.amount, c.isladi, c."+resultNum+",p.rate,p.io_rate,p.commission,p.patti,p.bold, from client_form c and profile p where p.pid=c.cid and p.pid="+pid+";";
    		res = db.getdata(amountResultQuery);
    		res.next();
    		
    		
    	}
	}
