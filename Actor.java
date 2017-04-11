import java.io.*;

abstract class Actor implements Serializable{
	abstract void show();
}

class Player extends Actor{
	String name;
	int hp,max_hp,strength, numPotion,pType,gold,numMeteo;
	static int type;

	Player() throws Exception{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("プレイヤーの名前？");
		name = reader.readLine();
		hp = max_hp = 100;
		strength = 100;
		numPotion = 0;
		type = 0;
		pType = 1;
		gold =1000;
		numMeteo = 0;
	}

	void show(){
		System.out.println(name +"[現HP=" + hp +",最大HP" + max_hp +",攻撃力=" + strength +",薬=" + numPotion +"]");
	}

	int attack(Enemy enemy) throws Exception{
		System.out.println(name +"の物理攻撃!!");
		Thread.sleep(1000);
		int hit = (int)(strength * Math.random());
		enemy.hp = enemy.hp - hit;
		if(enemy.hp >0) return 0;
		System.out.println(enemy.name +"は死にました");
		return 1;
	}
	int gu(Enemy enemy) throws Exception{
		System.out.println(name +"は石を投げた!!");
		Thread.sleep(1000);
		if(enemy.type==1)	pType=1;
		else if(enemy.type==2)	pType=2;
		else if(enemy.type==3)	pType=1/2;
		int hit = (int)(strength * Math.random() * pType);
		enemy.hp = enemy.hp - hit;
		if(enemy.hp >0) return 0;
		System.out.println(enemy.name +"は死にました");
		return 1;
	}
	int cho(Enemy enemy) throws Exception{
		System.out.println(name +"は鋏で切り刻んだ!!");
		Thread.sleep(1000);
		if(enemy.type==1)	pType=1/2;
		else if(enemy.type==2)	pType=1;
		else if(enemy.type==3)	pType=2;
		int hit = (int)(strength * Math.random() * pType);
		enemy.hp = enemy.hp - hit;
		if(enemy.hp >0) return 0;
		System.out.println(enemy.name +"は死にました");
		return 1;
	}
	int pa(Enemy enemy) throws Exception{
		System.out.println(name +"は紙で包んだ!!");
		Thread.sleep(1000);
		if(enemy.type==1)	pType=2;
		else if(enemy.type==2)	pType=1/2;
		else if(enemy.type==3)	pType=1;
		int hit = (int)(strength * Math.random() * pType);
		enemy.hp = enemy.hp - hit;
		if(enemy.hp >0) return 0;
		System.out.println(enemy.name +"は死にました");
		return 1;
	}
	void getPotion(){
		numPotion = numPotion + 1;
	}

	void usePotion(){
		if(numPotion == 0){
			System.out.println("回復薬を持っていません");
			return;
		}
		hp = max_hp;
		numPotion -= 1;
		System.out.println("HPが回復しました");
	}
	void shop(){
		if(gold<2000){
			System.out.println("お金が足りない");
			return;
		}
		gold -= 2000;
		numMeteo += 1;
		System.out.println("メテオを覚えた");
	}
	void useMeteo(){
		if(numMeteo ==0){
			System.out.println("まだメテオを覚えてない");
			return;
		}
		numEnemy = 0;
		hp = 1;
		System.out.println("フハハ･･･敵が消え去った");
	}
}

class Enemy extends Actor{
	String name;
	int hp,max_hp,strength;
	static int type;

	Enemy() {
		name = "敵";
		hp = max_hp = (int)(150 * Math.random()) + 1;
		strength = (int)(100 * Math.random()) + 1;
		type = 0;
	}

	void show(){
		System.out.println(name +"[現HP=" + hp +",最大HP" + max_hp +",攻撃力=" + strength +"]");
	}

	int attack(Player player) throws Exception{
		System.out.println(name +"の攻撃!!");
		Thread.sleep(1000);
		int hit = (int)(strength * Math.random());
		player.hp = player.hp - hit;
		if(player.hp >0) return 0;
		System.out.println(player.name +"は死にました");
		return 1;
	}
}

class Gu extends Enemy{
	Gu(){
		name = "石";
		type = 1;
	}
}
class Cho extends Enemy{
	Cho(){
		name = "鋏";
		type = 2;
	}
}
class Pa extends Enemy{
	Pa(){
		name = "紙";
		type = 3;
	}
}

class Takara extends Actor{
	Takara(){}
	void show() {System.out.println("宝");}
}

class NoMove extends Actor{
	NoMove(){}
	void show() {System.out.println("ここには移動できません");}
}

class  Potion extends Actor{
	Potion(){}
	void show(){System.out.println("薬[HPを最大まで回復させる]");}
}
