<?php
session_start();

$products = [
    1 => [
        'name' => 'Curleen Shampoo',
        'price' => 70000,
        'image' => 'structures/imgs/shampoo.png',
        'description' => 'Ingredients: Aloe Vera Gel, Castile Soap, Honey, Essential Oil, Apple Cider Vinegar, Vitamin E',
        'benefits' => [
            'Gentle cleansing without stripping natural oils',
            'Promotes hair growth and reduces hair fall',
            'Balances scalp pH for healthier hair',
            'Adds shine and softness to hair',
            'Suitable for all hair types, including curly and color-treated hair'
        ]
    ],
    2 => [
        'name' => 'Curleen Conditioner',
        'price' => 85000,
        'image' => 'structures/imgs/conditioner.png',
        'description' => 'Ingredients: Coconut Oil, Coconut Milk, Honey, Essential Oil, Vitamin E',
        'benefits' => [
            'Deep hydration for dry and damaged hair',
            'Reduces frizz and improves manageability',
            'Strengthens hair strands to prevent breakage',
            'Enhances natural curl pattern',
            'Leaves hair silky smooth without weighing it down'
        ]
    ],
    3 => [
        'name' => 'Curleen Hair Oil',
        'price' => 53000,
        'image' => 'structures/imgs/hair oil.png',
        'description' => 'Ingredients: Candlenut Oil, Argan Oil, Coconut Oil, Rosemary Oil, Tea Tree Oil',
        'benefits' => [
            'Nourishes hair from root to tip',
            'Promotes scalp health and reduces dandruff',
            'Protects hair from heat damage and environmental stressors',
            'Adds lustrous shine without greasiness',
            'Can be used as a pre-shampoo treatment or styling aid'
        ]
    ]
];

$product_id = isset($_GET['id']) ? (int)$_GET['id'] : 0;
$product = isset($products[$product_id]) ? $products[$product_id] : null;

if (!$product) {
    die('Product not found');
}

if ($_SERVER['REQUEST_METHOD'] == 'POST' && isset($_POST['add_to_cart'])) {
    $product_id = $_POST['product_id'];
    $quantity = $_POST['quantity'];
    
    
    if (!isset($_SESSION['cart'])) {
        $_SESSION['cart'] = [];
    }
    
    $_SESSION['cart'][$product_id] = [
        'name' => $product['name'],
        'price' => $product['price'],
        'quantity' => $quantity,
        'image' => $product['image']
    ];

}
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><?php echo htmlspecialchars($product['name']); ?> - Description</title>
    <link href="https://fonts.googleapis.com/css2?family=Spectral:wght@400;700&display=swap" rel="stylesheet">
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" />
    <link rel="stylesheet" href="structures/css/styles.css"/>
</head>
<body>

    <nav class="navbar navbar-expand-lg bg-body-light" >
        <div class="container-fluid">
        <img src="structures/imgs/logo.png" width="80" height="80"/>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse nav-buttons" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
    
            <li class="nav-item">
                <a class="nav-link" href="index.html">Home</a>
            </li>
    
            <li class="nav-item">
                <a class="nav-link" href="aboutus.html">About Us</a>
            </li>
    
            <li class="nav-item">
                <a class="nav-link" href="shop.php">Shop</a>
            </li>
    
            <li class="nav-item cart-icon">
                <a class="nav-link" href="cart.php">
                <i class="fas fa-shopping-cart"></i>
                </a>
            </li>
    
            </ul>
        </div>
        </div>
    </nav>

    <div class="container mt-5">
        <div class="row">
            <div class="col-md-6">
                <img src="<?php echo htmlspecialchars($product['image']); ?>" alt="<?php echo htmlspecialchars($product['name']); ?>" class="img-fluid product-image-description">
            </div>
            <div class="col-md-6">
                <h1><?php echo htmlspecialchars($product['name']); ?></h1>
                <h2 id="totalPrice">Rp.<?php echo number_format($product['price'], 0, ',', '.'); ?></h2>
                <p><?php echo nl2br(htmlspecialchars($product['description'])); ?></p>
                
                <h2>Product Benefits:</h2>
                <ul>
                    <?php foreach ($product['benefits'] as $benefit): ?>
                        <li><?php echo htmlspecialchars($benefit); ?></li>
                    <?php endforeach; ?>
                </ul>
                
                <form action="" method="post">
                    <div class="form-group">
                        <label for="quantity">Quantity:</label>
                        <input type="number" id="quantity" name="quantity" value="1" min="1" class="form-control" style="width: 100px;">
                    </div>
                    <input type="hidden" name="product_id" value="<?php echo $product_id; ?>">
                    <button style="background-color: #98B77E; outline: none" type="submit" name="add_to_cart" class="btn btn-primary mt-3">Add to Cart</button>
                </form>
            </div>
        </div>
    </div>

    <footer class="footer">
        <div class="container">
            <p>&copy; 2024 Curleen. All rights reserved.</p>
            <p>Find Us on</p>
            <div class="social-icons">
                <a href="https://facebook.com" class="me-3">
                    <img src="structures/imgs/facebook.png" alt="Facebook" style="width: 30px; height: 30px;">
                </a>
                <a href="https://instagram.com" class="me-3">
                    <img src="structures/imgs/instagram.png" alt="Instagram" style="width: 30px; height: 30px;">
                </a>
                <a href="https://wa.me/6288213966458" class="me-3">
                    <img src="structures/imgs/whatsapp.png" alt="Whatsapp" style="width: 30px; height: 30px;">
                </a>
            </div>
        </div>
    </footer>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            const quantityInput = document.getElementById('quantity');
            const totalPriceElement = document.getElementById('totalPrice');
            const basePrice = <?php echo $product['price']; ?>;

            function updateTotalPrice() {
                const quantity = parseInt(quantityInput.value);
                const totalPrice = basePrice * quantity;
                totalPriceElement.textContent = 'Rp.' + totalPrice.toLocaleString('id-ID');
            }

            quantityInput.addEventListener('input', updateTotalPrice);
            updateTotalPrice(); 
        });

        document.addEventListener('DOMContentLoaded', function() {
            const homeLink = document.querySelector('a[href="index.php"]');
            homeLink.addEventListener('click', function(e) {
                e.preventDefault();
                window.location.href = 'index.php';
            });
        });
    </script>
</body>
</html>
