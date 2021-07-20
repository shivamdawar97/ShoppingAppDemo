# Shopping App Demo

This is a demo android application using latest android jetpack libraries following MVVM clean architecture.

* This application follow the single Activity architecture.

## Libraries Used
* Retrofit 
* Navigation Component
* ViewModel and LiveDara
* Glide
* Coroutines

## Application flow
MainActivity has a viewModel class, MainActivityViewModel which holds all the data required for all view purpose,
as our application follow single activity architecture, it has four fragments i.e., 
ProductsFragment: Which shows all of the product on the screen.
ProductDetailFragment: Which shows details of the product.
CartFragment: Show our Shopping cart.
CheckoutFragment: Show our order summary.
  