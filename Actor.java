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
		System.out.println("�v���C���[�̖��O�H");
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
		System.out.println(name +"[��HP=" + hp +",�ő�HP" + max_hp +",�U����=" + strength +",��=" + numPotion +"]");
	}

	int attack(Enemy enemy) throws Exception{
		System.out.println(name +"�̕����U��!!");
		Thread.sleep(1000);
		int hit = (int)(strength * Math.random());
		enemy.hp = enemy.hp - hit;
		if(enemy.hp >0) return 0;
		System.out.println(enemy.name +"�͎��ɂ܂���");
		return 1;
	}
	int gu(Enemy enemy) throws Exception{
		System.out.println(name +"�͐΂𓊂���!!");
		Thread.sleep(1000);
		if(enemy.type==1)	pType=1;
		else if(enemy.type==2)	pType=2;
		else if(enemy.type==3)	pType=1/2;
		int hit = (int)(strength * Math.random() * pType);
		enemy.hp = enemy.hp - hit;
		if(enemy.hp >0) return 0;
		System.out.println(enemy.name +"�͎��ɂ܂���");
		return 1;
	}
	int cho(Enemy enemy) throws Exception{
		System.out.println(name +"�����Ő؂荏��!!");
		Thread.sleep(1000);
		if(enemy.type==1)	pType=1/2;
		else if(enemy.type==2)	pType=1;
		else if(enemy.type==3)	pType=2;
		int hit = (int)(strength * Math.random() * pType);
		enemy.hp = enemy.hp - hit;
		if(enemy.hp >0) return 0;
		System.out.println(enemy.name +"�͎��ɂ܂���");
		return 1;
	}
	int pa(Enemy enemy) throws Exception{
		System.out.println(name +"�͎��ŕ��!!");
		Thread.sleep(1000);
		if(enemy.type==1)	pType=2;
		else if(enemy.type==2)	pType=1/2;
		else if(enemy.type==3)	pType=1;
		int hit = (int)(strength * Math.random() * pType);
		enemy.hp = enemy.hp - hit;
		if(enemy.hp >0) return 0;
		System.out.println(enemy.name +"�͎��ɂ܂���");
		return 1;
	}
	void getPotion(){
		numPotion = numPotion + 1;
	}

	void usePotion(){
		if(numPotion == 0){
			System.out.println("�񕜖�������Ă��܂���");
			return;
		}
		hp = max_hp;
		numPotion -= 1;
		System.out.println("HP���񕜂��܂���");
	}
	void shop(){
		if(gold<2000){
			System.out.println("����������Ȃ�");
			return;
		}
		gold -= 2000;
		numMeteo += 1;
		System.out.println("���e�I���o����");
	}
	void useMeteo(){
		if(numMeteo ==0){
			System.out.println("�܂����e�I���o���ĂȂ�");
			return;
		}
		numEnemy = 0;
		hp = 1;
		System.out.println("�t�n�n����G������������");
	}
}

class Enemy extends Actor{
	String name;
	int hp,max_hp,strength;
	static int type;

	Enemy() {
		name = "�G";
		hp = max_hp = (int)(150 * Math.random()) + 1;
		strength = (int)(100 * Math.random()) + 1;
		type = 0;
	}

	void show(){
		System.out.println(name +"[��HP=" + hp +",�ő�HP" + max_hp +",�U����=" + strength +"]");
	}

	int attack(Player player) throws Exception{
		System.out.println(name +"�̍U��!!");
		Thread.sleep(1000);
		int hit = (int)(strength * Math.random());
		player.hp = player.hp - hit;
		if(player.hp >0) return 0;
		System.out.println(player.name +"�͎��ɂ܂���");
		return 1;
	}
}

class Gu extends Enemy{
	Gu(){
		name = "��";
		type = 1;
	}
}
class Cho extends Enemy{
	Cho(){
		name = "��";
		type = 2;
	}
}
class Pa extends Enemy{
	Pa(){
		name = "��";
		type = 3;
	}
}

class Takara extends Actor{
	Takara(){}
	void show() {System.out.println("��");}
}

class NoMove extends Actor{
	NoMove(){}
	void show() {System.out.println("�����ɂ͈ړ��ł��܂���");}
}

class  Potion extends Actor{
	Potion(){}
	void show(){System.out.println("��[HP���ő�܂ŉ񕜂�����]");}
}
