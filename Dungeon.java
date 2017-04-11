import java.io.*;

class Dungeon implements Serializable{
	Actor[][] map;
	int mapSize, numNoMove, numEnemy, numPotion;
	int pX, pY;

	Dungeon(int mapSize, int numNoMove, int numEnemy, int numPotion){
		int x,y;

	if((1 + 1 + numNoMove + numEnemy + numPotion) > mapSize * mapSize){
		System.out.println("エラー：actorが多すぎます");
		System.exit(1);
	}
	this.mapSize = mapSize;	this.numNoMove = numNoMove;
	this.numEnemy = numEnemy; this.numPotion = numPotion;
	map = new Actor[mapSize][mapSize];

	x=(int)(mapSize * Math.random());
	y=(int)(mapSize * Math.random());
	map[x][y] = new Takara();

	for(int n=0;n<numNoMove;n++){
		do{
			x=(int)(mapSize * Math.random());
			y=(int)(mapSize * Math.random());
		}while(map[x][y] != null);
		map[x][y] = new NoMove();
	}

	for(int n=0;n<numEnemy;n++){
		do{
			x=(int)(mapSize * Math.random());
			y=(int)(mapSize * Math.random());
		}while(map[x][y] != null);
		if(n%4 == 0) map[x][y] = new Gu();
		if(n%4 == 1) map[x][y] = new Cho();
		if(n%4 == 2) map[x][y] = new Pa();
		else map[x][y] = new Enemy();
	}

	for(int n=0; n<numPotion;n++){
		do{
		x=(int)(mapSize * Math.random());
		y=(int)(mapSize * Math.random());
		}while(map[x][y] != null);
		map[x][y] = new Potion();
	}

	do{
		x=(int)(mapSize * Math.random());
		y=(int)(mapSize * Math.random());
		}while(map[x][y] != null);
		pX=x; pY=y;
	}

	void show(){
		System.out.println("ダンジョン[敵=" + numEnemy +",薬=" +numPotion+ "]");
		System.out.print('+');
		for(int x=0;x<mapSize;x++) System.out.print('-');
		System.out.println('+');
		for(int y=0;y<mapSize;y++){
			System.out.print('l');
			for(int x=0;x<mapSize;x++){
				char s = '.';
				if(x==pX && y==pY) s='H';	//ヒーロー
				else if(map[x][y] instanceof Takara) s='T';
				else if(map[x][y] instanceof NoMove) s='X';
				else if(map[x][y] instanceof Gu) s='G';
				else if(map[x][y] instanceof Cho) s='C';
				else if(map[x][y] instanceof Pa) s='P';
				else if(map[x][y] instanceof Enemy) s='e';
				else if(map[x][y] instanceof Potion) s='y';	//薬草
				System.out.print(s);
			}
			System.out.println('l');
		}
		System.out.print('+');
		for(int x=0;x<mapSize;x++) System.out.print('-');
		System.out.println('+');
	}

	Actor move(int dx,int dy){
		pX +=dx;
		pY +=dy;
		if(pX<0 || pX>=mapSize || pY<0 || pY >=mapSize)
			return new NoMove();
		return map[pX][pY];
	}

	void take(){
		if(map[pX][pY] instanceof Enemy) numEnemy -= 1;
		if(map[pX][pY] instanceof Potion) numPotion -= 1;
		map[pX][pY] = null;
		return;
	}
}