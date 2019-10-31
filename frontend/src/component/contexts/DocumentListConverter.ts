export default function(item: any) {
  return {
    id: item.id,
    name: item.name,
    owner: item.owner,
    modifiedAt: new Date(item.modifiedAt),
    size: item.size
  };
}
