package game;

import chess.Home;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

/**
 * A self-contained, keyboard-and-mouse roguelike built from the attached
 * progression, character, floor, monster, boss, and item sheets.
 */
public class DelveGame extends JFrame {
  private static final int WIDTH = 1080;
  private static final int HEIGHT = 740;
  private static final int MAX_LEVEL = 50;
  private static final int MAX_XP = 113680;

  private static final String[] FLOOR_NAMES = {
      "폐허가 된 마을", "지하 묘지", "불타는 광산", "얼어붙은 숲", "고대 신전",
      "독 늪지", "번개 첨탑", "얼어붙은 성", "핏빛 성소", "심연"
  };
  private static final String[] FLOOR_HAZARDS = {
      "무너진 돌길", "어둠 속 매복", "용암 지대", "미끄러운 빙판", "시간의 파동",
      "독 웅덩이", "주기적 낙뢰", "얼음 폭풍", "적을 치유하는 핏물", "뒤틀린 균열"
  };
  private static final String[] BOSS_NAMES = {
      "허수아비 대장", "뼈 수집가", "용광로 골렘", "서리 늑대", "타락한 대사제",
      "독개구리 왕", "뇌조", "서리 드래곤", "피의 군주", "심연의 왕"
  };
  private static final String[] BOSS_WEAKNESSES = {
      "불", "관통", "얼음", "불", "성스러운 힘",
      "불", "얼음", "불", "성스러운 힘", "빛"
  };
  private static final String[][] MONSTERS = {
      {"들쥐", "굶은 농부", "까마귀", "돌팔매꾼", "허수아비 병사", "폭발 호박", "이장"},
      {"해골병", "뼈 궁수", "좀비", "묘지 박쥐", "원혼", "묘지 수호병"},
      {"불씨 슬라임", "용암 두더지", "화염 박쥐", "바위 게", "폭약 통", "화염 도마뱀", "용광로 수문장"},
      {"눈사람 병사", "서리 늑대 새끼", "얼음 요정", "서리 곰 새끼", "얼음 갑옷", "설인 대장"},
      {"석상 수호병", "성직자 환영", "시계 톱니 정령", "빛의 구체", "단죄의 눈", "신전 대수호자"},
      {"독개구리", "늪 거머리", "포자 버섯", "독침 잠자리", "늪지 악어", "늪 수호자"},
      {"정전기 쥐", "번개 새", "전기 해파리", "피뢰침 병사", "구름 정령", "번개 사제"},
      {"얼음 병사", "성벽 궁수", "얼음 가고일", "서리 기사", "얼음 마술사", "성채 사령관"},
      {"피 거머리", "흡혈 박쥐", "피 노예", "피의 사제", "핏빛 하인", "혈족 귀족"},
      {"그림자 병사", "심연 눈알", "공허 촉수", "차원 틈 벌레", "별빛 정령", "시간 잔상", "중력 구체", "심연 사도"}
  };
  private static final double[] FLOOR_MULTIPLIERS = {
      1.0, 1.12, 1.25, 1.38, 1.6, 1.75, 1.9, 2.05, 2.2, 2.6
  };
  private static final Item[] ITEMS = {
      new Item("낡은 숫돌", "공격력 +1", "damage", 1, 0, "common"),
      new Item("가벼운 장갑", "공격 속도 +20%", "speed", 0, 0.2, "common"),
      new Item("매의 눈", "사거리 +20%", "range", 0, 0.2, "common"),
      new Item("붉은 심장 조각", "최대 체력 +1칸, 체력 회복", "heart", 2, 0, "rare"),
      new Item("네잎클로버", "행운 +2, 보상 획득률 증가", "luck", 2, 0, "rare"),
      new Item("독니", "적중 시 독 피해", "poison", 0, 0, "rare"),
      new Item("불씨 조각", "공격에 불 속성 부여", "fire", 0, 0, "rare"),
      new Item("서리 조각", "공격에 얼음 속성 부여", "ice", 0, 0, "rare"),
      new Item("쌍둥이 별", "탄환을 둘로 나눔", "twin", 0, 0, "epic"),
      new Item("유령의 손", "탄환이 적을 관통", "pierce", 0, 0, "epic"),
      new Item("폭죽통", "적중 시 20% 확률로 폭발", "explosion", 0, 0, "epic"),
      new Item("흡혈 송곳니", "적 10마리 처치마다 체력 회복", "vampire", 0, 0, "epic"),
      new Item("불사조 깃털", "이번 원정에서 한 번 부활", "revive", 0, 0, "legendary"),
      new Item("긴 칼날", "검 사거리 +30%", "sword", 0, 0.3, "rare"),
      new Item("속사 시위", "활 충전 시간 -30%", "bow", 0, 0.3, "rare"),
      new Item("확장 탄창", "권총 탄창 +2", "magazine", 2, 0, "rare")
  };

  private static final String SELECT = "select";
  private static final String BATTLE = "battle";
  private static final String REWARD = "reward";
  private static final String FLOOR_BREAK = "floor-break";
  private static final String VICTORY = "victory";
  private static final String DEFEAT = "defeat";

  private final Runnable onExit;
  private final Random random = new Random();
  private final Set<Integer> keysDown = new HashSet<Integer>();
  private final GamePanel panel = new GamePanel();
  private final Timer timer;
  private final Properties progress = new Properties();
  private final File saveFile = new File(System.getProperty("user.home"), ".vencc-delver.properties");

  private boolean archerUnlocked;
  private boolean gunnerUnlocked;
  private int totalXp;
  private int level = 1;
  private int statPoints;
  private double permanentDamage;
  private int permanentHealth;
  private double permanentSpeed;
  private String message = "WASD 이동 · 마우스/Space 공격 · Q 특수 능력";
  private String state = SELECT;
  private Hero hero;
  private int floor;
  private int room;
  private boolean bossRoom;
  private int runXp;
  private int runGold;
  private int kills;
  private int runKills;
  private boolean firstClearBonus;
  private boolean midbossUnlockedThisRun;
  private boolean runFinalized;
  private double playerX = WIDTH / 2.0;
  private double playerY = HEIGHT / 2.0;
  private double attackCooldown;
  private double hurtCooldown;
  private double skillCooldown;
  private double hazardTimer = 4;
  private double bossAttackCooldown = 2;
  private double bossTelegraph;
  private double reloadTimer;
  private double chargeTimer;
  private double speedBoostTimer;
  private double damageBoostTimer;
  private double poisonTimer;
  private int activeEffect;
  private int hazardX;
  private int hazardY;
  private int hazardRadius = 85;
  private int mouseX = WIDTH / 2;
  private int mouseY = HEIGHT / 2;
  private boolean mouseDown;
  private final List<Enemy> enemies = new ArrayList<Enemy>();
  private final List<Shot> shots = new ArrayList<Shot>();
  private Item[] offeredItems;

  public DelveGame(final Runnable onExit) {
    super("심연의 원정");
    this.onExit = onExit;
    loadProgress();
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setResizable(false);
    setContentPane(panel);
    pack();
    setLocationRelativeTo(null);
    addWindowListener(new java.awt.event.WindowAdapter() {
      @Override
      public void windowClosed(java.awt.event.WindowEvent event) {
        if (DelveGame.this.onExit != null) {
          DelveGame.this.onExit.run();
        }
      }
    });
    timer = new Timer(16, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent event) {
        updateGame(0.016);
        panel.repaint();
      }
    });
    timer.start();
    setVisible(true);
    panel.requestFocusInWindow();
  }

  private void loadProgress() {
    if (!saveFile.isFile()) {
      return;
    }
    FileInputStream input = null;
    try {
      input = new FileInputStream(saveFile);
      progress.load(input);
      totalXp = Math.min(MAX_XP, Math.max(0, Integer.parseInt(progress.getProperty("xp", "0"))));
      statPoints = Math.max(0, Integer.parseInt(progress.getProperty("points", "0")));
      permanentDamage = Double.parseDouble(progress.getProperty("damage", "0"));
      permanentHealth = Math.max(0, Integer.parseInt(progress.getProperty("health", "0")));
      permanentSpeed = Double.parseDouble(progress.getProperty("speed", "0"));
      archerUnlocked = Boolean.parseBoolean(progress.getProperty("archer", "false"));
      gunnerUnlocked = Boolean.parseBoolean(progress.getProperty("gunner", "false"));
      level = levelForXp(totalXp);
    } catch (IOException error) {
      System.err.println("Could not load game progress: " + error.getMessage());
    } catch (NumberFormatException error) {
      System.err.println("Could not parse game progress: " + error.getMessage());
    } finally {
      if (input != null) {
        try {
          input.close();
        } catch (IOException error) {
          System.err.println("Could not close progress file: " + error.getMessage());
        }
      }
    }
  }

  private void saveProgress() {
    progress.setProperty("xp", Integer.toString(totalXp));
    progress.setProperty("points", Integer.toString(statPoints));
    progress.setProperty("damage", Double.toString(permanentDamage));
    progress.setProperty("health", Integer.toString(permanentHealth));
    progress.setProperty("speed", Double.toString(permanentSpeed));
    progress.setProperty("archer", Boolean.toString(archerUnlocked));
    progress.setProperty("gunner", Boolean.toString(gunnerUnlocked));
    FileOutputStream output = null;
    try {
      output = new FileOutputStream(saveFile);
      progress.store(output, "Spreadsheet Delver progress");
    } catch (IOException error) {
      System.err.println("Could not save game progress: " + error.getMessage());
      message = "진행 저장 실패: " + error.getMessage();
    } finally {
      if (output != null) {
        try {
          output.close();
        } catch (IOException error) {
          System.err.println("Could not close progress file: " + error.getMessage());
        }
      }
    }
  }

  private int levelForXp(int xp) {
    int result = 1;
    int remaining = xp;
    while (result < MAX_LEVEL) {
      int required = xpForNextLevel(result);
      if (remaining < required) {
        break;
      }
      remaining -= required;
      result++;
    }
    return result;
  }

  private int xpForNextLevel(int currentLevel) {
    return 400 + 80 * (currentLevel - 1);
  }

  private void selectHero(int choice) {
    if (choice == 1 && !archerUnlocked) {
      message = "궁수는 5층 중간 보스를 처치하면 해금됩니다.";
      return;
    }
    if (choice == 2 && !gunnerUnlocked) {
      message = "총잡이는 10층을 처음으로 클리어하면 해금됩니다.";
      return;
    }
    if (choice == 0) {
      hero = new Hero("검사", "기사 · 장검", 5, 1.0, 1.1, 6, 0);
      hero.shield = 2;
      hero.potions = 1;
    } else if (choice == 1) {
      hero = new Hero("궁수", "사냥꾼 · 단궁", 6, 1.2, 1.0, 6, 1);
      hero.keys = 1;
    } else {
      hero = new Hero("총잡이", "방랑자 · 리볼버", 3, 0.333, 0.95, 6, 2);
      hero.bombs = 1;
      hero.magazine = 6;
      hero.maxMagazine = 6;
    }
    hero.hp = hero.maxHp;
    hero.damageBonus = permanentDamage;
    hero.maxHp += permanentHealth;
    hero.hp = hero.maxHp;
    hero.moveSpeed += permanentSpeed;
    floor = 0;
    room = 0;
    runXp = 0;
    runGold = 0;
    runKills = 0;
    firstClearBonus = !Boolean.parseBoolean(progress.getProperty("clear-" + choice, "false"));
    midbossUnlockedThisRun = false;
    runFinalized = false;
    state = BATTLE;
    startRoom(false);
    message = "방을 정리하고 보상을 챙겨 10층 심연까지 내려가세요.";
  }

  private void startRoom(boolean boss) {
    bossRoom = boss;
    playerX = WIDTH / 2.0;
    playerY = HEIGHT * 0.7;
    hazardX = WIDTH / 2 + random.nextInt(260) - 130;
    hazardY = HEIGHT / 2 + random.nextInt(150) - 75;
    hazardTimer = 4.5;
    bossAttackCooldown = 1.8;
    bossTelegraph = 0;
    enemies.clear();
    shots.clear();
    if (boss) {
      enemies.add(makeEnemy(BOSS_NAMES[floor], true, false));
      message = BOSS_NAMES[floor] + " 등장! 약점 속성: " + BOSS_WEAKNESSES[floor];
    } else {
      int count = 3 + random.nextInt(2);
      for (int i = 0; i < count; i++) {
        boolean elite = i == count - 1 && (random.nextDouble() < 0.25 || room == 2);
        enemies.add(makeEnemy(randomMonsterName(), false, elite));
      }
      message = FLOOR_NAMES[floor] + " · 전투 " + (room + 1) + "/2";
    }
  }

  private String randomMonsterName() {
    String[] floorMonsters = MONSTERS[floor];
    return floorMonsters[random.nextInt(floorMonsters.length - 1)];
  }

  private Enemy makeEnemy(String name, boolean boss, boolean elite) {
    double multiplier = FLOOR_MULTIPLIERS[floor];
    double baseHealth = boss ? 42 + floor * 8 : 3.2 + floor * 1.0;
    double health = Math.ceil(baseHealth * multiplier * (elite ? 2.5 : 1));
    double size = boss ? 39 : (elite ? 24 : 18);
    double speed = boss ? 40 + floor * 1.5 : 42 + random.nextInt(15) + floor * 1.2;
    double x = WIDTH / 2.0 + random.nextInt(360) - 180;
    double y = 180 + random.nextInt(260);
    return new Enemy(name, x, y, health, boss, elite, size, speed);
  }

  private void updateGame(double delta) {
    if (!BATTLE.equals(state)) {
      return;
    }
    updatePlayer(delta);
    updateEnemies(delta);
    updateShots(delta);
    updateHazard(delta);
    if (enemies.isEmpty()) {
      if (bossRoom) {
        finishFloor();
      } else {
        openReward();
      }
    }
  }

  private void updatePlayer(double delta) {
    double dx = 0;
    double dy = 0;
    if (keysDown.contains(KeyEvent.VK_W) || keysDown.contains(KeyEvent.VK_UP)) dy--;
    if (keysDown.contains(KeyEvent.VK_S) || keysDown.contains(KeyEvent.VK_DOWN)) dy++;
    if (keysDown.contains(KeyEvent.VK_A) || keysDown.contains(KeyEvent.VK_LEFT)) dx--;
    if (keysDown.contains(KeyEvent.VK_D) || keysDown.contains(KeyEvent.VK_RIGHT)) dx++;
    double magnitude = Math.sqrt(dx * dx + dy * dy);
    if (magnitude > 0) {
      double speed = hero.moveSpeed * 160 * (speedBoostTimer > 0 ? 1.45 : 1);
      playerX += dx / magnitude * speed * delta;
      playerY += dy / magnitude * speed * delta;
    }
    playerX = Math.max(75, Math.min(WIDTH - 75, playerX));
    playerY = Math.max(145, Math.min(HEIGHT - 80, playerY));
    if (hurtCooldown > 0) hurtCooldown -= delta;
    if (skillCooldown > 0) skillCooldown -= delta;
    if (reloadTimer > 0) {
      reloadTimer -= delta;
      if (reloadTimer <= 0) {
        hero.magazine = hero.maxMagazine;
      }
    }
    if (speedBoostTimer > 0) speedBoostTimer -= delta;
    if (damageBoostTimer > 0) damageBoostTimer -= delta;
    if (poisonTimer > 0) {
      poisonTimer -= delta;
      if (poisonTimer <= 0) {
        damageEnemy(enemies.isEmpty() ? null : enemies.get(0), 0.1);
      }
    }
    if (attackCooldown > 0) attackCooldown -= delta;
    boolean attacking = mouseDown || keysDown.contains(KeyEvent.VK_SPACE);
    if (attacking) {
      chargeTimer += delta;
      if (attackCooldown <= 0) {
        attack();
      }
    } else {
      chargeTimer = 0;
    }
  }

  private void attack() {
    Enemy target = nearestEnemy();
    if (target == null) return;
    double range = hero.attackRange * (1 + hero.rangeBonus);
    if (hero.characterId == 0 && hero.hp == hero.maxHp && hero.shield > 0) {
      range *= 2.3;
    }
    if (distance(playerX, playerY, target.x, target.y) > range) {
      attackCooldown = 0.12;
      return;
    }
    if (hero.characterId == 2) {
      if (reloadTimer > 0) return;
      if (hero.magazine <= 0) {
        reloadTimer = 1.5;
        chargeTimer = 0;
        return;
      }
      hero.magazine--;
      spawnFriendlyShot(target, 0.55);
      if (hero.magazine == 0) reloadTimer = 1.5;
      attackCooldown = hero.attackInterval / (1 + hero.attackSpeedBonus);
      chargeTimer = 0;
    } else if (hero.characterId == 1) {
      double interval = hero.attackInterval *
          (1 - Math.min(0.8, hero.bowChargeReduction + hero.chargeKillBonus));
      if (chargeTimer < interval * 0.5) return;
      double charge = Math.min(1.0, chargeTimer / interval);
      spawnFriendlyShot(target, charge < 0.95 ? 0.55 : 1.0);
      attackCooldown = interval * (1 - charge);
      chargeTimer = 0;
      hero.chargeKillBonus = 0;
    } else {
      if (distance(playerX, playerY, target.x, target.y) > range) return;
      spawnFriendlyShot(target, 1.0);
      attackCooldown = hero.attackInterval / (1 + hero.attackSpeedBonus);
      chargeTimer = 0;
    }
  }

  private void spawnFriendlyShot(Enemy target, double scale) {
    double angle = Math.atan2(target.y - playerY, target.x - playerX);
    double baseDamage = hero.damage + hero.damageBonus;
    if (damageBoostTimer > 0) baseDamage *= 1.5;
    if (hero.characterId == 0 && hero.hp == hero.maxHp && hero.shield > 0) baseDamage *= 1.25;
    baseDamage *= scale;
    if (hero.characterId == 1 && scale >= 0.95) heroPierce = true;
    else heroPierce = false;
    String element = hero.element;
    if (hero.characterId == 0 && hero.hp == hero.maxHp && hero.shield > 0) element = "불";
    for (int i = 0; i < (hero.hasTwin ? 2 : 1); i++) {
      double spread = hero.hasTwin ? (i == 0 ? -0.07 : 0.07) : 0;
      double shotAngle = angle + spread;
      if (hero.characterId == 0 && distance(playerX, playerY, target.x, target.y) <= 125) {
        damageEnemy(target, baseDamage);
      } else {
        shots.add(new Shot(playerX, playerY, Math.cos(shotAngle) * 520,
            Math.sin(shotAngle) * 520, baseDamage, true, hero.hasPierce || heroPierce, element));
      }
    }
  }

  private boolean heroPierce;

  private Enemy nearestEnemy() {
    Enemy best = null;
    double bestDistance = Double.MAX_VALUE;
    for (Enemy enemy : enemies) {
      double d = distance(playerX, playerY, enemy.x, enemy.y);
      if (d < bestDistance) {
        bestDistance = d;
        best = enemy;
      }
    }
    return best;
  }

  private void updateEnemies(double delta) {
    List<Enemy> dead = new ArrayList<Enemy>();
    for (Enemy enemy : enemies) {
      double distance = distance(playerX, playerY, enemy.x, enemy.y);
      double angle = Math.atan2(playerY - enemy.y, playerX - enemy.x);
      if (enemy.boss) {
        updateBoss(enemy, delta, angle);
      } else if (distance > enemy.size + 35) {
        double speed = enemy.speed * (enemy.slow > 0 ? 0.6 : 1);
        enemy.x += Math.cos(angle) * speed * delta;
        enemy.y += Math.sin(angle) * speed * delta;
      }
      if (distance < enemy.size + 18 && hurtCooldown <= 0) {
        hurtPlayer(enemy.boss ? 2 : 1);
        hurtCooldown = 0.85;
      }
      enemy.flash = Math.max(0, enemy.flash - delta);
      if (enemy.hp <= 0) dead.add(enemy);
    }
    for (Enemy enemy : dead) {
      enemies.remove(enemy);
      runXp += enemy.boss ? 150 : (enemy.elite ? 20 : 2);
      runGold += enemy.boss ? 15 : (enemy.elite ? 6 : 1 + random.nextInt(2));
      runKills++;
      kills++;
      if (hero.hasVampire && kills % 10 == 0) hero.hp = Math.min(hero.maxHp, hero.hp + 1);
      if (hero.characterId == 1) hero.chargeKillBonus = Math.min(0.3, hero.chargeKillBonus + 0.1);
      if (enemy.boss && floor == 4 && !archerUnlocked) {
        archerUnlocked = true;
        midbossUnlockedThisRun = true;
        message = "5층 중간 보스 격파! 궁수가 해금되었습니다.";
        saveProgress();
      }
    }
  }

  private void updateBoss(Enemy boss, double delta, double angle) {
    double hpRatio = boss.hp / boss.maxHp;
    int phases = floor == 4 || floor == 9 ? 3 : 2;
    boss.phase = hpRatio > 0.66 ? 1 : (phases == 3 && hpRatio > 0.33 ? 2 : phases);
    if (bossTelegraph > 0) {
      bossTelegraph -= delta;
      if (bossTelegraph <= 0) {
        double targetAngle = Math.atan2(playerY - boss.y, playerX - boss.x);
        int spread = boss.phase > 1 ? 7 : 5;
        for (int i = 0; i < spread; i++) {
          double shotAngle = targetAngle + (i - (spread - 1) / 2.0) * 0.24;
          shots.add(new Shot(boss.x, boss.y, Math.cos(shotAngle) * (190 + floor * 8),
              Math.sin(shotAngle) * (190 + floor * 8), 1, false, false, ""));
        }
        bossAttackCooldown = (boss.phase > 1 ? 1.9 : 2.8) - floor * 0.07;
      }
    } else {
      bossAttackCooldown -= delta;
      if (bossAttackCooldown <= 0) bossTelegraph = 0.75;
    }
    if (distance(playerX, playerY, boss.x, boss.y) > 85) {
      boss.x += Math.cos(angle) * boss.speed * delta;
      boss.y += Math.sin(angle) * boss.speed * delta;
    }
  }

  private void updateShots(double delta) {
    List<Shot> expired = new ArrayList<Shot>();
    for (Shot shot : shots) {
      shot.x += shot.dx * delta;
      shot.y += shot.dy * delta;
      shot.life -= delta;
      if (shot.life <= 0 || shot.x < 45 || shot.y < 115 || shot.x > WIDTH - 45 || shot.y > HEIGHT - 40) {
        expired.add(shot);
        continue;
      }
      if (shot.friendly) {
        for (Enemy enemy : enemies) {
          if (distance(shot.x, shot.y, enemy.x, enemy.y) < enemy.size + 7) {
            double damage = shot.damage;
            if (enemy.boss && !shot.element.isEmpty() && shot.element.equals(BOSS_WEAKNESSES[floor])) {
              damage *= 1.5;
            }
            damageEnemy(enemy, damage);
            if (!shot.pierce) expired.add(shot);
            else shot.damage *= 0.8;
            if (hero.hasPoison) enemy.poison = 2.4;
            if (hero.hasFire && !enemy.boss) enemy.burn = 2.0;
            if (hero.hasIce) enemy.slow = 1.5;
            if (hero.hasFire && hero.hasPoison) {
              for (Enemy nearby : enemies) {
                if (nearby != enemy && distance(enemy.x, enemy.y, nearby.x, nearby.y) < 110) {
                  nearby.poison = Math.max(nearby.poison, 1.2);
                  nearby.burn = Math.max(nearby.burn, 1.2);
                }
              }
            }
            break;
          }
        }
      } else if (distance(shot.x, shot.y, playerX, playerY) < 20 && hurtCooldown <= 0) {
        hurtPlayer(1);
        hurtCooldown = 0.7;
        expired.add(shot);
      }
    }
    for (Shot shot : expired) shots.remove(shot);
    for (Enemy enemy : enemies) {
      if (enemy.poison > 0) {
        enemy.poison -= delta;
        enemy.hp -= delta * 0.45;
      }
      if (enemy.burn > 0) {
        enemy.burn -= delta;
        enemy.hp -= delta * 0.6;
      }
      if (enemy.slow > 0) enemy.slow -= delta;
    }
  }

  private void updateHazard(double delta) {
    hazardTimer -= delta;
    if (hazardTimer <= 0) {
      if (distance(playerX, playerY, hazardX, hazardY) < hazardRadius) {
        hurtPlayer(1);
        hurtCooldown = 0.8;
      }
      hazardX = 120 + random.nextInt(WIDTH - 240);
      hazardY = 185 + random.nextInt(HEIGHT - 270);
      hazardTimer = 7.5;
    }
  }

  private void damageEnemy(Enemy enemy, double damage) {
    if (enemy == null || enemy.hp <= 0) return;
    enemy.hp -= damage;
    enemy.flash = 0.15;
    if (hero.hasExplosion && random.nextDouble() < 0.2) {
      for (Enemy nearby : enemies) {
        if (nearby != enemy && distance(enemy.x, enemy.y, nearby.x, nearby.y) < 85) {
          nearby.hp -= damage * (hero.hasIce ? 0.7 : 0.45);
        }
      }
    }
  }

  private void hurtPlayer(int halfHearts) {
    if (hurtCooldown > 0) return;
    int damage = halfHearts;
    int absorbed = Math.min(hero.shield, damage);
    hero.shield -= absorbed;
    damage -= absorbed;
    hero.hp -= damage;
    if (hero.hp <= 0) {
      if (hero.hasRevive) {
        hero.hasRevive = false;
        hero.hp = Math.max(2, hero.maxHp / 2);
        shots.clear();
        message = "불사조 깃털이 발동했습니다!";
      } else {
        finishRun(false);
      }
    }
  }

  private void openReward() {
    state = REWARD;
    offeredItems = chooseItems();
    message = "방을 클리어했습니다. 아이템을 고르세요.";
    panel.requestFocusInWindow();
  }

  private Item[] chooseItems() {
    Item[] result = new Item[3];
    Set<Integer> chosen = new HashSet<Integer>();
    for (int i = 0; i < result.length; i++) {
      int index;
      do {
        index = weightedItemIndex();
      } while (chosen.contains(index));
      chosen.add(index);
      result[i] = ITEMS[index];
    }
    return result;
  }

  private int weightedItemIndex() {
    int roll = random.nextInt(100);
    String rarity = roll < 45 ? "common" :
        (roll < 80 ? "rare" : (roll < 97 ? "epic" : "legendary"));
    int count = 0;
    for (Item item : ITEMS) {
      if (rarity.equals(item.rarity)) count++;
    }
    if (count == 0) return random.nextInt(ITEMS.length);
    int choice = random.nextInt(count);
    for (int i = 0; i < ITEMS.length; i++) {
      if (rarity.equals(ITEMS[i].rarity) && choice-- == 0) return i;
    }
    return random.nextInt(ITEMS.length);
  }

  private void chooseItem(int index) {
    if (offeredItems == null || index < 0 || index >= offeredItems.length) return;
    applyItem(offeredItems[index]);
    offeredItems = null;
    state = BATTLE;
    if (room < 1) {
      room++;
      startRoom(false);
    } else {
      startRoom(true);
    }
  }

  private void applyItem(Item item) {
    if ("damage".equals(item.effect)) hero.damageBonus += item.value;
    else if ("speed".equals(item.effect)) hero.attackSpeedBonus += item.value;
    else if ("range".equals(item.effect)) hero.rangeBonus += item.value;
    else if ("heart".equals(item.effect)) {
      hero.maxHp += (int) item.value;
      hero.hp = Math.min(hero.maxHp, hero.hp + (int) item.value);
    } else if ("luck".equals(item.effect)) hero.luck += (int) item.value;
    else if ("poison".equals(item.effect)) hero.hasPoison = true;
    else if ("fire".equals(item.effect)) {
      hero.hasFire = true;
      hero.element = "불";
    } else if ("ice".equals(item.effect)) {
      hero.hasIce = true;
      hero.element = "얼음";
    } else if ("twin".equals(item.effect)) hero.hasTwin = true;
    else if ("pierce".equals(item.effect)) hero.hasPierce = true;
    else if ("explosion".equals(item.effect)) hero.hasExplosion = true;
    else if ("vampire".equals(item.effect)) hero.hasVampire = true;
    else if ("revive".equals(item.effect)) hero.hasRevive = true;
    else if ("sword".equals(item.effect)) hero.rangeBonus += item.value;
    else if ("bow".equals(item.effect)) hero.bowChargeReduction += item.value;
    else if ("magazine".equals(item.effect)) hero.maxMagazine += (int) item.value;
    message = item.name + " 획득! " + item.description;
    runGold += 3 + hero.luck;
  }

  private void finishFloor() {
    state = FLOOR_BREAK;
    runXp += 100;
    runGold += 8 + floor;
    hero.hp = Math.min(hero.maxHp, hero.hp + 1);
    if (floor == 9) {
      runXp += 2000;
      finishRun(true);
      return;
    }
    floor++;
    room = 0;
    offeredItems = chooseItems();
    message = FLOOR_NAMES[floor - 1] + " 정복! 다음 층으로 내려갈 준비가 되었습니다.";
  }

  private void finishRun(boolean won) {
    if (runFinalized) return;
    runFinalized = true;
    state = won ? VICTORY : DEFEAT;
    int payout = won ? runXp + (firstClearBonus ? 3000 : 0) : (int) Math.floor(runXp * 0.8);
    addXp(payout);
    if (won) {
      progress.setProperty("clear-" + hero.characterId, "true");
      if (hero.characterId == 2) gunnerUnlocked = true;
    }
    if (midbossUnlockedThisRun) archerUnlocked = true;
    runGold += won ? 50 : 0;
    saveProgress();
    message = won ? "심연을 정복했습니다! 원정 XP +" + payout : "원정 종료 · 획득 XP의 80%를 보존했습니다.";
  }

  private void addXp(int amount) {
    int before = level;
    totalXp = Math.min(MAX_XP, totalXp + Math.max(0, amount));
    level = levelForXp(totalXp);
    for (int reached = before + 1; reached <= level; reached++) {
      statPoints++;
      if (reached % 10 == 0) statPoints += 2;
    }
  }

  private void spendStat(int index) {
    if (statPoints <= 0 || hero == null) return;
    statPoints--;
    if (index == 0) {
      permanentDamage += 0.5;
      hero.damageBonus += 0.5;
    }
    else if (index == 1) {
      permanentHealth++;
      hero.maxHp += 1;
      hero.hp = Math.min(hero.maxHp, hero.hp + 1);
    } else {
      permanentSpeed += 0.08;
      hero.moveSpeed += 0.08;
    }
    message = index == 0 ? "공격력 강화" : (index == 1 ? "최대 체력 강화" : "이동 속도 강화");
    saveProgress();
  }

  private void useSkill() {
    if (!BATTLE.equals(state) || skillCooldown > 0) return;
    if (hero.characterId == 0) {
      skillCooldown = 7;
      for (Shot shot : shots) {
        if (!shot.friendly) {
          shot.friendly = true;
          shot.dx = -shot.dx;
          shot.dy = -shot.dy;
        }
      }
      hero.shield = Math.min(2, hero.shield + 1);
      message = "검으로 투사체를 받아치고 방패를 회복했습니다.";
    } else if (hero.characterId == 1) {
      skillCooldown = 9;
      Enemy target = nearestEnemy();
      if (target != null) {
        for (int i = 0; i < 5; i++) {
          damageEnemy(target, hero.damage + hero.damageBonus);
        }
      }
      message = "정조준 연사!";
    } else {
      skillCooldown = 10;
      hero.magazine = hero.maxMagazine;
      reloadTimer = 0;
      damageBoostTimer = 3;
      message = "액티브 재장전! 3초 동안 공격력 증가.";
    }
  }

  private void usePotion() {
    if (!BATTLE.equals(state) || hero.potions <= 0) return;
    hero.potions--;
    hero.hp = Math.min(hero.maxHp, hero.hp + 2);
    message = "응급 붕대 사용 · 체력 회복";
  }

  private void useBomb() {
    if (!BATTLE.equals(state) || hero.bombs <= 0) return;
    hero.bombs--;
    Enemy target = nearestEnemy();
    if (target != null) {
      for (Enemy enemy : enemies) {
        if (distance(target.x, target.y, enemy.x, enemy.y) < 150) {
          damageEnemy(enemy, 10 + hero.damageBonus);
        }
      }
    }
    message = "폭탄을 던졌습니다!";
  }

  private void reload() {
    if (hero == null || hero.characterId != 2 || reloadTimer > 0 || hero.magazine == hero.maxMagazine) return;
    boolean active = hero.magazine == 0;
    reloadTimer = active ? 1.0 : 1.5;
    if (active) damageBoostTimer = 2.0;
    message = active ? "완벽한 재장전! 다음 탄환 강화." : "재장전 중...";
  }

  private void continueFloor() {
    if (state != FLOOR_BREAK) return;
    state = BATTLE;
    offeredItems = null;
    startRoom(false);
  }

  private void rerollItems() {
    if ((REWARD.equals(state) || FLOOR_BREAK.equals(state)) && hero.keys > 0) {
      hero.keys--;
      offeredItems = chooseItems();
      message = "열쇠를 사용해 보물을 다시 굴렸습니다.";
    }
  }

  private void returnToHome() {
    timer.stop();
    dispose();
  }

  private void updateGameScreen(Graphics2D g) {
    drawBackground(g);
    drawFloorMap(g);
    drawHud(g);
    drawHazard(g);
    drawShots(g);
    drawEnemies(g);
    drawHero(g);
    drawFooter(g);
    if (REWARD.equals(state)) drawRewardOverlay(g);
    else if (FLOOR_BREAK.equals(state)) drawFloorOverlay(g);
    else if (VICTORY.equals(state)) drawEndOverlay(g, true);
    else if (DEFEAT.equals(state)) drawEndOverlay(g, false);
  }

  private void drawBackground(Graphics2D g) {
    Color[] colors = {
        new Color(40, 54, 65), new Color(28, 29, 48), new Color(69, 37, 28),
        new Color(30, 58, 75), new Color(65, 53, 38), new Color(28, 62, 52),
        new Color(38, 45, 70), new Color(42, 63, 83), new Color(70, 31, 45),
        new Color(42, 28, 67)
    };
    Color base = colors[Math.max(0, Math.min(9, floor))];
    g.setPaint(new GradientPaint(0, 0, base, 0, HEIGHT, new Color(15, 19, 31)));
    g.fillRect(0, 0, WIDTH, HEIGHT);
    g.setColor(new Color(255, 255, 255, 14));
    for (int x = 70; x < WIDTH; x += 64) g.drawLine(x, 115, x, HEIGHT - 40);
    for (int y = 135; y < HEIGHT - 40; y += 64) g.drawLine(45, y, WIDTH - 45, y);
    g.setColor(new Color(255, 255, 255, 16));
    g.drawRect(45, 115, WIDTH - 90, HEIGHT - 155);
  }

  private void drawFloorMap(Graphics2D g) {
    g.setColor(new Color(7, 11, 19, 190));
    g.fillRoundRect(38, 18, WIDTH - 76, 80, 16, 16);
    g.setColor(Color.WHITE);
    g.setFont(new Font("Dialog", Font.BOLD, 20));
    g.drawString("제" + (floor + 1) + "층  " + FLOOR_NAMES[Math.max(0, Math.min(9, floor))], 58, 47);
    g.setFont(new Font("Dialog", Font.PLAIN, 13));
    g.setColor(new Color(200, 202, 220));
    g.drawString("위험: " + FLOOR_HAZARDS[Math.max(0, Math.min(9, floor))] + "   ·   난이도 ×" +
        FLOOR_MULTIPLIERS[Math.max(0, Math.min(9, floor))], 58, 74);
    int start = WIDTH - 335;
    for (int i = 0; i < 10; i++) {
      int x = start + (i % 5) * 53;
      int y = 30 + (i / 5) * 31;
      g.setColor(i < floor ? new Color(92, 190, 143) :
          (i == floor ? new Color(246, 190, 92) : new Color(100, 107, 124)));
      g.fillRoundRect(x, y, 42, 23, 8, 8);
      g.setColor(new Color(20, 25, 36));
      g.drawString(Integer.toString(i + 1), x + 17, y + 16);
    }
  }

  private void drawHud(Graphics2D g) {
    if (hero == null) return;
    g.setColor(new Color(7, 11, 19, 205));
    g.fillRoundRect(56, 122, 240, 85, 12, 12);
    g.setColor(Color.WHITE);
    g.setFont(new Font("Dialog", Font.BOLD, 15));
    g.drawString(hero.name + " · Lv." + level, 70, 145);
    g.setColor(new Color(240, 105, 116));
    g.drawString("♥ " + formatHealth(hero.hp) + "/" + formatHealth(hero.maxHp) +
        (hero.shield > 0 ? "  ◈ " + formatHealth(hero.shield) : "") +
        (hero.keys > 0 ? "  열쇠 " + hero.keys : ""), 70, 169);
    g.setColor(new Color(236, 195, 110));
    g.drawString("XP " + totalXp + "/" + MAX_XP + "  ·  本次 +" + runXp + "  ·  金币 " + runGold, 70, 192);
    if (bossRoom && !enemies.isEmpty()) {
      Enemy boss = enemies.get(0);
      int barX = WIDTH / 2 - 200;
      g.setColor(new Color(20, 20, 27, 225));
      g.fillRoundRect(barX - 10, 118, 420, 50, 12, 12);
      g.setColor(Color.WHITE);
      centered(g, boss.name + " · 페이즈 " + boss.phase, WIDTH / 2, 137);
      g.setColor(new Color(86, 34, 46));
      g.fillRoundRect(barX, 145, 400, 12, 6, 6);
      g.setColor(new Color(229, 77, 102));
      g.fillRoundRect(barX, 145, (int) (400 * Math.max(0, boss.hp / boss.maxHp)), 12, 6, 6);
    }
  }

  private String formatHealth(int halfHearts) {
    return Integer.toString(Math.max(0, halfHearts / 2)) + (halfHearts % 2 == 0 ? "" : ".5");
  }

  private void drawHazard(Graphics2D g) {
    g.setColor(new Color(232, 65, 84, hazardTimer < 1.3 ? 90 : 28));
    g.fill(new Ellipse2D.Double(hazardX - hazardRadius, hazardY - hazardRadius,
        hazardRadius * 2, hazardRadius * 2));
    g.setColor(new Color(252, 94, 103, 140));
    g.setStroke(new BasicStroke(2));
    g.draw(new Ellipse2D.Double(hazardX - hazardRadius, hazardY - hazardRadius,
        hazardRadius * 2, hazardRadius * 2));
  }

  private void drawShots(Graphics2D g) {
    for (Shot shot : shots) {
      g.setColor(shot.friendly ? new Color(255, 223, 130) : new Color(248, 92, 110));
      g.fill(new Ellipse2D.Double(shot.x - 6, shot.y - 6, 12, 12));
      g.setColor(new Color(255, 255, 255, 130));
      g.fill(new Ellipse2D.Double(shot.x - 2, shot.y - 2, 4, 4));
    }
  }

  private void drawEnemies(Graphics2D g) {
    for (Enemy enemy : enemies) {
      if (enemy.boss && bossTelegraph > 0) {
        g.setColor(new Color(241, 53, 80, 70));
        g.fill(new Ellipse2D.Double(enemy.x - 110, enemy.y - 110, 220, 220));
      }
      Color color = enemy.boss ? new Color(154, 78, 111) :
          (enemy.elite ? new Color(194, 139, 72) : new Color(153, 113, 111));
      if (enemy.flash > 0) color = Color.WHITE;
      g.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 230));
      g.fill(new Ellipse2D.Double(enemy.x - enemy.size, enemy.y - enemy.size,
          enemy.size * 2, enemy.size * 2));
      g.setColor(new Color(255, 232, 207));
      g.draw(new Ellipse2D.Double(enemy.x - enemy.size, enemy.y - enemy.size,
          enemy.size * 2, enemy.size * 2));
      g.setColor(Color.WHITE);
      g.setFont(new Font("Dialog", Font.BOLD, enemy.boss ? 14 : 11));
      centered(g, enemy.name, (int) enemy.x, (int) (enemy.y - enemy.size - 13));
      g.setColor(new Color(30, 23, 30));
      g.fillRoundRect((int) enemy.x - 27, (int) enemy.y + (int) enemy.size + 5, 54, 6, 4, 4);
      g.setColor(enemy.boss ? new Color(240, 87, 108) : new Color(233, 194, 119));
      g.fillRoundRect((int) enemy.x - 27, (int) enemy.y + (int) enemy.size + 5,
          (int) (54 * Math.max(0, enemy.hp / enemy.maxHp)), 6, 4, 4);
    }
  }

  private void drawHero(Graphics2D g) {
    if (hero == null) return;
    Color color = hero.characterId == 0 ? new Color(241, 190, 101) :
        (hero.characterId == 1 ? new Color(118, 205, 167) : new Color(120, 173, 235));
    if (hurtCooldown > 0 && ((int) (hurtCooldown * 20) % 2 == 0)) color = Color.WHITE;
    g.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 65));
    g.fill(new Ellipse2D.Double(playerX - 26, playerY - 26, 52, 52));
    g.setColor(color);
    g.fill(new Ellipse2D.Double(playerX - 16, playerY - 19, 32, 38));
    g.setColor(new Color(25, 30, 42));
    g.fillOval((int) playerX - 8, (int) playerY - 7, 5, 5);
    g.fillOval((int) playerX + 3, (int) playerY - 7, 5, 5);
    if (hero.shield > 0) {
      g.setColor(new Color(121, 205, 245, 160));
      g.setStroke(new BasicStroke(2));
      g.draw(new Ellipse2D.Double(playerX - 25, playerY - 27, 50, 54));
    }
  }

  private void drawFooter(Graphics2D g) {
    g.setColor(new Color(5, 8, 14, 220));
    g.fillRoundRect(46, HEIGHT - 69, WIDTH - 92, 53, 12, 12);
    g.setFont(new Font("Dialog", Font.PLAIN, 13));
    g.setColor(new Color(223, 225, 235));
    g.drawString(message, 62, HEIGHT - 45);
    String controls = "WASD 이동 · Space/클릭 공격 · Q 특수 · R 회복(" + hero.potions +
        ") · F 폭탄(" + hero.bombs + ") · T 열쇠 리롤(" + hero.keys +
        ") · 1/2/3 능력치(" + statPoints + ") · Esc 마을";
    g.setColor(new Color(159, 169, 188));
    g.drawString(controls, 62, HEIGHT - 25);
    if (hero.characterId == 2) {
      g.setColor(new Color(235, 203, 121));
      g.drawString("탄창 " + hero.magazine + "/" + hero.maxMagazine +
          (reloadTimer > 0 ? " · 재장전" : ""), WIDTH - 215, HEIGHT - 45);
    }
    g.setColor(new Color(186, 190, 207));
    g.drawString("방 " + (room + 1) + "/2" + (bossRoom ? " · 보스" : ""), WIDTH - 215, HEIGHT - 24);
  }

  private void drawRewardOverlay(Graphics2D g) {
    drawOverlayShade(g);
    g.setColor(Color.WHITE);
    g.setFont(new Font("Dialog", Font.BOLD, 30));
    centered(g, "보물 상자", WIDTH / 2, 230);
    g.setFont(new Font("Dialog", Font.PLAIN, 15));
    centered(g, "아이템 선택: 1 / 2 / 3 · 열쇠 리롤: T", WIDTH / 2, 263);
    drawCards(g, offeredItems);
  }

  private void drawFloorOverlay(Graphics2D g) {
    drawOverlayShade(g);
    g.setColor(Color.WHITE);
    g.setFont(new Font("Dialog", Font.BOLD, 30));
    centered(g, "층 클리어!", WIDTH / 2, 230);
    g.setFont(new Font("Dialog", Font.PLAIN, 16));
    centered(g, "보물을 고르세요 · 1 / 2 / 3 선택 · T 열쇠 리롤", WIDTH / 2, 264);
    drawCards(g, offeredItems);
    g.setColor(new Color(239, 209, 146));
    centered(g, "보상 XP +100 · 보스 XP +150 · 이번 원정 " + runXp + " XP", WIDTH / 2, 497);
    g.setColor(Color.WHITE);
    centered(g, "아이템 선택 후 다음 층으로", WIDTH / 2, 544);
  }

  private void drawCards(Graphics2D g, Item[] items) {
    if (items == null) return;
    int startX = WIDTH / 2 - 430;
    for (int i = 0; i < items.length; i++) {
      Item item = items[i];
      int x = startX + i * 290;
      int y = 300;
      g.setColor(new Color(23, 30, 45, 245));
      g.fillRoundRect(x, y, 260, 168, 18, 18);
      g.setColor(rarityColor(item.rarity));
      g.setStroke(new BasicStroke(2));
      g.drawRoundRect(x, y, 260, 168, 18, 18);
      g.setFont(new Font("Dialog", Font.BOLD, 12));
      centered(g, item.rarity.toUpperCase(), x + 130, y + 29);
      g.setFont(new Font("Dialog", Font.BOLD, 21));
      centered(g, item.name, x + 130, y + 73);
      g.setColor(new Color(213, 216, 229));
      g.setFont(new Font("Dialog", Font.PLAIN, 14));
      centered(g, item.description, x + 130, y + 106);
      centered(g, "선택 " + (i + 1), x + 130, y + 141);
    }
  }

  private void drawEndOverlay(Graphics2D g, boolean won) {
    drawOverlayShade(g);
    g.setColor(won ? new Color(255, 218, 143) : new Color(241, 126, 142));
    g.setFont(new Font("Dialog", Font.BOLD, 36));
    centered(g, won ? "심연 정복" : "원정 종료", WIDTH / 2, 245);
    g.setColor(Color.WHITE);
    g.setFont(new Font("Dialog", Font.PLAIN, 18));
    centered(g, "획득 XP " + (won ? runXp + (firstClearBonus ? 3000 : 0) : (int) (runXp * 0.8)) +
        " · 처치 " + runKills + " · 골드 " + runGold, WIDTH / 2, 292);
    centered(g, "영구 누적 XP " + totalXp + " / " + MAX_XP + " · 레벨 " + level, WIDTH / 2, 325);
    centered(g, archerUnlocked ? "궁수 해금" : "궁수: 5층 보스 처치 시 해금", WIDTH / 2, 366);
    centered(g, gunnerUnlocked ? "총잡이 해금" : "총잡이: 10층 첫 클리어 시 해금", WIDTH / 2, 396);
    centered(g, "Enter: 다시 원정  ·  Esc: 마을", WIDTH / 2, 455);
  }

  private void drawOverlayShade(Graphics2D g) {
    g.setColor(new Color(5, 7, 14, 205));
    g.fillRect(0, 0, WIDTH, HEIGHT);
  }

  private void drawSelection(Graphics2D g) {
    g.setPaint(new GradientPaint(0, 0, new Color(28, 32, 51), 0, HEIGHT, new Color(8, 12, 22)));
    g.fillRect(0, 0, WIDTH, HEIGHT);
    g.setColor(new Color(238, 198, 127));
    g.setFont(new Font("Dialog", Font.BOLD, 42));
    centered(g, "심연의 원정", WIDTH / 2, 105);
    g.setColor(new Color(194, 201, 218));
    g.setFont(new Font("Dialog", Font.PLAIN, 17));
    centered(g, "폐허에서 심연까지 10개 층을 돌파하고, 장비와 능력을 키우세요.", WIDTH / 2, 145);
    HeroCard[] cards = {
        new HeroCard("검사", "기사 · 장검", "공격 5 · 1초 간격 · 방패 1칸", "시작: 소형 회복 물약", true),
        new HeroCard("궁수", "사냥꾼 · 단궁", "공격 6 · 완충 관통 · 긴 사거리",
            archerUnlocked ? "시작: 열쇠" : "5층 보스 처치 후 해금", archerUnlocked),
        new HeroCard("총잡이", "방랑자 · 리볼버", "공격 3 · 초당 3발 · 6발 탄창",
            gunnerUnlocked ? "시작: 폭탄" : "10층 첫 클리어 후 해금", gunnerUnlocked)
    };
    int[] xs = {110, 400, 690};
    for (int i = 0; i < cards.length; i++) {
      int x = xs[i];
      HeroCard card = cards[i];
      g.setColor(card.unlocked ? new Color(25, 35, 51) : new Color(18, 22, 32));
      g.fillRoundRect(x, 230, 270, 286, 20, 20);
      g.setColor(card.unlocked ? new Color(217, 180, 118) : new Color(81, 89, 107));
      g.setStroke(new BasicStroke(2));
      g.drawRoundRect(x, 230, 270, 286, 20, 20);
      g.setColor(card.unlocked ? new Color(239, 205, 144) : new Color(118, 124, 141));
      g.fillOval(x + 97, 270, 76, 76);
      g.setColor(new Color(30, 36, 51));
      g.setFont(new Font("Dialog", Font.BOLD, 28));
      centered(g, i == 0 ? "검" : (i == 1 ? "활" : "총"), x + 135, 319);
      g.setColor(Color.WHITE);
      g.setFont(new Font("Dialog", Font.BOLD, 23));
      centered(g, card.name, x + 135, 387);
      g.setColor(new Color(198, 204, 219));
      g.setFont(new Font("Dialog", Font.PLAIN, 14));
      centered(g, card.role, x + 135, 415);
      centered(g, card.stats, x + 135, 451);
      g.setColor(card.unlocked ? new Color(232, 198, 136) : new Color(232, 130, 132));
      centered(g, card.unlockText, x + 135, 481);
      if (card.unlocked) {
        g.setColor(new Color(117, 200, 154));
        centered(g, "클릭하여 선택", x + 135, 548);
      }
    }
    g.setColor(new Color(214, 218, 232));
    g.setFont(new Font("Dialog", Font.PLAIN, 14));
    centered(g, "영구 XP " + totalXp + " / " + MAX_XP + "   ·   레벨 " + level +
        "   ·   사용 가능 능력치 " + statPoints, WIDTH / 2, 608);
    centered(g, "조작: WASD 이동 · 마우스/Space 공격 · Q 특수 능력 · 1 공격력 / 2 체력 / 3 이동 속도", WIDTH / 2, 640);
    centered(g, message, WIDTH / 2, 680);
  }

  private Color rarityColor(String rarity) {
    if ("legendary".equals(rarity)) return new Color(244, 173, 78);
    if ("epic".equals(rarity)) return new Color(194, 137, 244);
    if ("rare".equals(rarity)) return new Color(103, 173, 244);
    return new Color(177, 195, 172);
  }

  private void centered(Graphics2D g, String text, int x, int y) {
    FontMetrics metrics = g.getFontMetrics();
    g.drawString(text, x - metrics.stringWidth(text) / 2, y);
  }

  private double distance(double x1, double y1, double x2, double y2) {
    double dx = x2 - x1;
    double dy = y2 - y1;
    return Math.sqrt(dx * dx + dy * dy);
  }

  private class GamePanel extends JPanel implements KeyListener {
    GamePanel() {
      setPreferredSize(new Dimension(WIDTH, HEIGHT));
      setFocusable(true);
      setFocusTraversalKeysEnabled(false);
      addKeyListener(this);
      addMouseListener(new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent event) {
          requestFocusInWindow();
          mouseX = event.getX();
          mouseY = event.getY();
          mouseDown = true;
          handleClick(event.getX(), event.getY());
        }

        @Override
        public void mouseReleased(MouseEvent event) {
          mouseDown = false;
        }
      });
      addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
        @Override
        public void mouseMoved(MouseEvent event) {
          mouseX = event.getX();
          mouseY = event.getY();
        }

        @Override
        public void mouseDragged(MouseEvent event) {
          mouseX = event.getX();
          mouseY = event.getY();
        }
      });
    }

    @Override
    protected void paintComponent(Graphics graphics) {
      super.paintComponent(graphics);
      Graphics2D g = (Graphics2D) graphics.create();
      g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      if (SELECT.equals(state)) drawSelection(g);
      else updateGameScreen(g);
      g.dispose();
    }

    @Override
    public void keyPressed(KeyEvent event) {
      int key = event.getKeyCode();
      if (key == KeyEvent.VK_ESCAPE) {
        returnToHome();
        return;
      }
      if (key == KeyEvent.VK_ENTER && (VICTORY.equals(state) || DEFEAT.equals(state))) {
        state = SELECT;
        message = "새 원정에 도전하세요.";
        return;
      }
      if ((REWARD.equals(state) || FLOOR_BREAK.equals(state)) &&
          key >= KeyEvent.VK_1 && key <= KeyEvent.VK_3) {
        int choice = key - KeyEvent.VK_1;
        if (FLOOR_BREAK.equals(state)) {
          applyItem(offeredItems[choice]);
          offeredItems = null;
          continueFloor();
        } else chooseItem(choice);
        return;
      }
      if ((REWARD.equals(state) || FLOOR_BREAK.equals(state)) && key == KeyEvent.VK_T) {
        rerollItems();
        return;
      }
      if (!BATTLE.equals(state)) return;
      keysDown.add(key);
      if (key == KeyEvent.VK_Q) useSkill();
      else if (key == KeyEvent.VK_R) usePotion();
      else if (key == KeyEvent.VK_F) useBomb();
      else if (key == KeyEvent.VK_1) spendStat(0);
      else if (key == KeyEvent.VK_2) spendStat(1);
      else if (key == KeyEvent.VK_3) spendStat(2);
    }

    @Override
    public void keyReleased(KeyEvent event) {
      keysDown.remove(event.getKeyCode());
    }

    @Override
    public void keyTyped(KeyEvent event) {
    }

    private void handleClick(int x, int y) {
      if (SELECT.equals(state)) {
        if (y >= 210 && y <= 570) {
          if (x >= 110 && x <= 380) selectHero(0);
          else if (x >= 400 && x <= 670) selectHero(1);
          else if (x >= 690 && x <= 960) selectHero(2);
        }
      } else if (REWARD.equals(state) && y >= 285 && y <= 490) {
        int index = (x - (WIDTH / 2 - 430)) / 290;
        chooseItem(index);
      } else if (FLOOR_BREAK.equals(state)) {
        if (y >= 285 && y <= 490) {
          int index = (x - (WIDTH / 2 - 430)) / 290;
          if (index >= 0 && index < offeredItems.length) {
            applyItem(offeredItems[index]);
            offeredItems = null;
            continueFloor();
          }
        } else continueFloor();
      } else if (VICTORY.equals(state) || DEFEAT.equals(state)) {
        if (x > WIDTH / 2) state = SELECT;
      }
    }
  }

  private static class Hero {
    final String name;
    final String role;
    final int characterId;
    final double damage;
    final double attackInterval;
    double moveSpeed;
    final double attackRange;
    int maxHp;
    int hp;
    int shield;
    int potions;
    int bombs;
    int keys;
    int magazine;
    int maxMagazine;
    int luck;
    double damageBonus;
    double attackSpeedBonus;
    double rangeBonus;
    double bowChargeReduction;
    double chargeKillBonus;
    boolean hasTwin;
    boolean hasPierce;
    boolean hasPoison;
    boolean hasFire;
    boolean hasIce;
    boolean hasExplosion;
    boolean hasVampire;
    boolean hasRevive;
    String element = "";

    Hero(String name, String role, double damage, double attackInterval,
         double moveSpeed, int maxHp, int id) {
      this.name = name;
      this.role = role;
      this.damage = damage;
      this.attackInterval = attackInterval;
      this.moveSpeed = moveSpeed;
      this.maxHp = maxHp;
      this.characterId = id;
      this.attackRange = id == 0 ? 125 : (id == 1 ? 360 : 245);
    }
  }

  private static class Enemy {
    final String name;
    final double maxHp;
    final boolean boss;
    final boolean elite;
    final double size;
    final double speed;
    double x;
    double y;
    double hp;
    double flash;
    double poison;
    double burn;
    double slow;
    int phase = 1;

    Enemy(String name, double x, double y, double hp, boolean boss,
          boolean elite, double size, double speed) {
      this.name = name;
      this.x = x;
      this.y = y;
      this.hp = hp;
      this.maxHp = hp;
      this.boss = boss;
      this.elite = elite;
      this.size = size;
      this.speed = speed;
    }
  }

  private static class Shot {
    double x;
    double y;
    double dx;
    double dy;
    double damage;
    double life = 3;
    boolean friendly;
    final boolean pierce;
    final String element;

    Shot(double x, double y, double dx, double dy, double damage,
         boolean friendly, boolean pierce, String element) {
      this.x = x;
      this.y = y;
      this.dx = dx;
      this.dy = dy;
      this.damage = damage;
      this.friendly = friendly;
      this.pierce = pierce;
      this.element = element;
    }
  }

  private static class Item {
    final String name;
    final String description;
    final String effect;
    final double value;
    final double secondary;
    final String rarity;

    Item(String name, String description, String effect, double value,
         double secondary, String rarity) {
      this.name = name;
      this.description = description;
      this.effect = effect;
      this.value = value;
      this.secondary = secondary;
      this.rarity = rarity;
    }
  }

  private static class HeroCard {
    final String name;
    final String role;
    final String stats;
    final String unlockText;
    final boolean unlocked;

    HeroCard(String name, String role, String stats, String unlockText, boolean unlocked) {
      this.name = name;
      this.role = role;
      this.stats = stats;
      this.unlockText = unlockText;
      this.unlocked = unlocked;
    }
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        new DelveGame(new Runnable() {
          @Override
          public void run() {
            new Home();
          }
        });
      }
    });
  }
}
