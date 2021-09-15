import { Pipe, PipeTransform } from '@angular/core';
import { Product } from './product';


@Pipe({
  name: 'productFilter'
})
export class ProductFilterPipe implements PipeTransform {

  transform(products: Product[], searchProduct: string): Product[] {
    if (!products || !searchProduct){
      return products;
    }

    return products.filter(product =>
       product.name.toLowerCase().indexOf(searchProduct.toLowerCase()) !== -1);
  }

}