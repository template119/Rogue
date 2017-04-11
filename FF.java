import java.io.*;

class FF{
	public static void main (String[] args) throws Exception{
		System.out.println("***** First Fantasy 3 *****");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		Player player;
		Enemy enemy;
		Dungeon dungeon;

		System.out.println("前のゲームをロードしますか？[y/n]");
		if( reader.readLine().equals("y")){
			ObjectInputStream save = new ObjectInputStream(new FileInputStream("ff.save"));
			player =(Player)save.readObject();
			enemy =(Enemy)save.readObject();
			dungeon =(Dungeon)save.readObject();
			save.close();
			System.out.println("ロードしました");
		}

		else{
			player = new Player();
			enemy = null;
			dungeon = new Dungeon(8,15,20,5);
		}
		player.show();
		dungeon.show();

		while(true){
			System.out.println("どうする？[m(移動),f(戦う),p(回復),w(表示),s(セーブ),x(終了)]");
			String input = reader.readLine();

			if( input.equals("m")){
				if(enemy != null){
					System.out.println("戦闘中は移動できません");
					continue;
				}

				System.out.println("どっち？[e(東),w(西),s(南),n(北)]");
				input = reader.readLine();
				int dx,dy;
				if	(input.equals("e")) {dx = +1; dy = 0;}
				else if	(input.equals("w")) {dx = -1; dy = 0;}
				else if	(input.equals("s")) {dx = 0; dy = +1;}
				else if	(input.equals("n")) {dx = 0; dy = -1;}
				else continue;

				Actor actor = dungeon.move(dx,dy);

				if(actor ==null) dungeon.show();
				else if(actor instanceof NoMove){
					actor.show();
					dungeon.move(-dx,-dy);
				}
				else if(actor instanceof Takara){
					dungeon.take();
					System.out.println("宝を手に入れました\nおめでとう");
					return;
				}
				else if(actor instanceof Enemy){
					System.out.println("敵がいました");
					enemy = (Enemy)actor;
					enemy.show();
				}
				else if(actor instanceof Potion){
					System.out.println("アイテムを手に入れました");
					actor.show();
					player.getPotion();
					dungeon.take();
					dungeon.show();
				}


			}

			else if( input.equals("f")){
				if(enemy == null){
					System.out.println("敵がいません");
					continue;
				}
				System.out.println("戦う(a)/石(g)/鋏(c)/紙(p)");
				input = reader.readLine();
				if(input.equals("a")){
					if(player.attack(enemy) == 1){
						enemy = null;
						dungeon.take();
						dungeon.show();
						continue;
					}
					enemy.show();
					if(enemy.attack(player) == 1){
						System.out.println("Game Over");
						return;
					}
					player.show();
				}
				else if(input.equals("g")){
					if(player.gu(enemy) == 1){
						enemy = null;
						dungeon.take();
						dungeon.show();
						continue;
					}
					enemy.show();
					if(enemy.attack(player) == 1){
						System.out.println("Game Over");
						return;
					}
					player.show();
				}
				else if(input.equals("c")){
					if(player.cho(enemy) == 1){
						enemy = null;
						dungeon.take();
						dungeon.show();
						continue;
					}
					enemy.show();
					if(enemy.attack(player) == 1){
						System.out.println("Game Over");
						return;
					}
					player.show();
				}
				else if(input.equals("p")){
					if(player.pa(enemy) == 1){
						enemy = null;
						dungeon.take();
						dungeon.show();
						continue;
					}
					enemy.show();
					if(enemy.attack(player) == 1){
						System.out.println("Game Over");
						return;
					}
					player.show();
				}
			}
			else if( input.equals("p")){
				player.usePotion();
			}
			else if( input.equals("w")){
				dungeon.show();
				player.show();
				if(enemy != null) enemy.show();
			}
			else if( input.equals("s")){
				ObjectOutputStream save =new ObjectOutputStream(new FileOutputStream("ff.save"));
				save.writeObject(player);
				save.writeObject(enemy);
				save.writeObject(dungeon);
				save.close();
				System.out.println("セーブしました");
			}

			else if( input.equals("x")){
				System.out.println("終了");
				return;
			}
		}
	}
}
